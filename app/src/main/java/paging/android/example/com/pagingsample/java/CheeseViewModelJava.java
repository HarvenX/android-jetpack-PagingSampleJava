package paging.android.example.com.pagingsample.java;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by xiehuawen(Harven) on 2020/4/1.
 */
public class CheeseViewModelJava {


    private final CheeseDaoJava dao;
    private final LiveData<PagedList<CheeseJava>> allCheeses;

    public CheeseViewModelJava(Application app) {
        dao = CheeseDbJava.getInstance(app).CheeseJavaDao();

        PagedList.Config config = new PagedList.Config.Builder().
                setPageSize(40).
                setEnablePlaceholders(true).
                setMaxSize(200).
                build();//PagedListConfigKt.Config(1,0,true,0,200);
        allCheeses = new LivePagedListBuilder<>(dao.allCheesesByName(),config).build();//.toLiveData(dao.allCheesesByName(),config,null,null, ArchTaskExecutor.getIOThreadExecutor() );
    }

    public LiveData<PagedList<CheeseJava>> getAllCheeses() {
        return allCheeses;
    }

    public void insert(CharSequence text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(new CheeseJava(0,text.toString()));
            }
        }).start();
    }

    public void remove(CheeseJava cheese){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(cheese);
            }
        }).start();
    }
}
