package paging.android.example.com.pagingsample.java;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by xiehuawen(Harven) on 2020/4/2.
 */
@Entity
public class CheeseJava {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public CheeseJava(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(@NonNull CheeseJava cheeseJava){
        return cheeseJava.hashCode() == hashCode();
    }
}
