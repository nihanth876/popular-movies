package com.example.nihanth_2.myapplication.DatabaseUtils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutorMovie {

    private static final Object LOCK = new Object();
    private static AppExecutorMovie appExecutor;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;
    private Handler mHandler = new Handler();
    static Looper looper;

    private AppExecutorMovie(Executor diskIO, Executor mainThread, Executor networkIO) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static AppExecutorMovie getAppExecutor() {
        if (appExecutor == null) {
            synchronized (LOCK) {
                appExecutor = new AppExecutorMovie(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3), new MainThreadExecutor());
            }
        }
        return appExecutor;
    }

    public Executor getDiskIO(){return diskIO;}
    public Executor getMainThread(){return mainThread;}
    public Executor getNetworkIO(){return networkIO;}

    private static class MainThreadExecutor implements Executor{

        Handler handler = new Handler(Looper.getMainLooper());


        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);

        }
    }
}
