package paging.android.example.com.pagingsample.java;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.LivePagedListKt;
import androidx.paging.PagedList;
import androidx.paging.PagedListConfigKt;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import paging.android.example.com.pagingsample.Cheese;
import paging.android.example.com.pagingsample.CheeseDao;
import paging.android.example.com.pagingsample.CheeseDb;
import paging.android.example.com.pagingsample.ExecutorsKt;

/**
 * Created by xiehuawen(Harven) on 2020/4/1.
 */
public class CheeseViewModelJava {


    private final CheeseDao dao;
    private final LiveData<PagedList<Cheese>> allCheeses;

    public CheeseViewModelJava(Application app) {
        dao = CheeseDb.Companion.get(app).cheeseDao();

        PagedList.Config config = new PagedList.Config.Builder().
                setPageSize(5).
                setEnablePlaceholders(true).
                setMaxSize(200).
                build();//PagedListConfigKt.Config(1,0,true,0,200);
        allCheeses = new LivePagedListBuilder<>(dao.allCheesesByName(),config).build();//.toLiveData(dao.allCheesesByName(),config,null,null, ArchTaskExecutor.getIOThreadExecutor() );
    }

    public CheeseDao getDao() {
        return dao;
    }

    public LiveData<PagedList<Cheese>> getAllCheeses() {
        return allCheeses;
    }

    public void insert(CharSequence text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(new Cheese(0,text.toString()));
            }
        }).start();
    }

    public void remove(Cheese cheese){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(cheese);
            }
        }).start();
    }
}
