package paging.android.example.com.pagingsample.java;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import paging.android.example.com.pagingsample.Cheese;
import paging.android.example.com.pagingsample.CheeseDao;

/**
 * Created by xiehuawen(Harven) on 2020/4/1.
 */
public class CheeseViewModelJava {


    private final CheeseDaoJava dao;
    private final LiveData<PagedList<Cheese>> allCheeses;

    public CheeseViewModelJava(Application app) {
        dao = CheeseDbJava.getInstance(app).cheeseDao();

        PagedList.Config config = new PagedList.Config.Builder().
                setPageSize(5).
                setEnablePlaceholders(true).
                setMaxSize(200).
                build();//PagedListConfigKt.Config(1,0,true,0,200);
        allCheeses = new LivePagedListBuilder<>(dao.allCheesesByName(),config).build();//.toLiveData(dao.allCheesesByName(),config,null,null, ArchTaskExecutor.getIOThreadExecutor() );
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
