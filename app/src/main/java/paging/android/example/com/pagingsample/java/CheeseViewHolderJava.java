package paging.android.example.com.pagingsample.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import paging.android.example.com.pagingsample.R;

/**
 * Created by xiehuawen(Harven) on 2020/4/2.
 */
public class CheeseViewHolderJava extends RecyclerView.ViewHolder{

    CheeseJava cheeseJava;
    private TextView nameView;

    public CheeseViewHolderJava(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.cheese_item, parent, false));
        nameView = itemView.findViewById(R.id.name);
    }

    public void bindTo(CheeseJava cheeseJava){
        this.cheeseJava = cheeseJava;
        nameView.setText(cheeseJava.name);
    }

    public CheeseJava getCheeseJava() {
        return cheeseJava;
    }
}
