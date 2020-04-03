package paging.android.example.com.pagingsample.java;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import paging.android.example.com.pagingsample.CheeseViewHolder;

/**
 * Created by xiehuawen(Harven) on 2020/4/2.
 */
public class CheeseAdapterJava extends PagedListAdapter<CheeseJava, CheeseViewHolderJava>{

    public CheeseAdapterJava(){
        super(new DiffUtil.ItemCallback<CheeseJava>() {
            @Override
            public boolean areItemsTheSame(@NonNull CheeseJava oldItem, @NonNull CheeseJava newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull CheeseJava oldItem, @NonNull CheeseJava newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public CheeseViewHolderJava onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheeseViewHolderJava(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CheeseViewHolderJava holder, int position) {
        CheeseJava cheeseJava = getItem(position);
        if(cheeseJava != null){
            holder.bindTo(cheeseJava);
        }
    }

}
