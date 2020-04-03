package paging.android.example.com.pagingsample.java;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import paging.android.example.com.pagingsample.Cheese;

/**
 * Created by xiehuawen(Harven) on 2020/4/2.
 */
@Dao
public interface CheeseDaoJava {

    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @Query("SELECT * FROM CheeseJava ORDER BY name COLLATE NOCASE ASC")
    DataSource.Factory<Integer, CheeseJava> allCheesesByName();

    @Insert
    void insert( List<CheeseJava> cheeses);

    @Insert
    void insert(CheeseJava cheese);

    @Delete
    void delete(CheeseJava cheese);

}
