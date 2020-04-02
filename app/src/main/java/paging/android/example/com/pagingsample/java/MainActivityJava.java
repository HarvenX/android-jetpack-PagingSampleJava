package paging.android.example.com.pagingsample.java;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import paging.android.example.com.pagingsample.Cheese;
import paging.android.example.com.pagingsample.CheeseAdapter;
import paging.android.example.com.pagingsample.CheeseViewHolder;
import paging.android.example.com.pagingsample.CheeseViewModel;
import paging.android.example.com.pagingsample.R;
import paging.android.example.com.pagingsample.databinding.ActivityMainBinding;

/**
 * Created by xiehuawen(Harven) on 2020/4/1.
 */
public class MainActivityJava extends AppCompatActivity {
    private CheeseViewModelJava viewModel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new CheeseViewModelJava(getApplication());
        mainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main);
        CheeseAdapter adapter = new CheeseAdapter();
        mainBinding.cheeseList.setAdapter(adapter);
        viewModel.getAllCheeses().observe(this, new Observer<PagedList<Cheese>>() {
            @Override
            public void onChanged(PagedList<Cheese> cheeses) {
                adapter.submitList(cheeses);
            }
        });
        initAddButtonListener();
        initSwipeToDelete();
    }

    private void initSwipeToDelete(){
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(viewHolder instanceof CheeseViewHolder){
                    CheeseViewHolder cheeseViewHolder = (CheeseViewHolder) viewHolder;
                    if(cheeseViewHolder.getCheese() != null){
                        viewModel.remove(cheeseViewHolder.getCheese());
                    }
                }
            }
        }).attachToRecyclerView(mainBinding.cheeseList);
    }


    private void addCheese() {
        String newCheese = mainBinding.inputText.getText().toString().trim();
        if (!TextUtils.isEmpty(newCheese)) {
            viewModel.insert(newCheese);
            mainBinding.inputText.setText("");
        }
    }

    private void initAddButtonListener(){
        mainBinding.addButton.setOnClickListener(view -> {
            addCheese();
        });
        mainBinding.inputText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_DONE){
                addCheese();
                return true;
            }
            return false;
        });
        mainBinding.inputText.setOnKeyListener((view, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese();
                return true;
            }
            return false; // event that isn't DOWN or ENTER occurred - ignore
        });
    }


}
