package com.york.yorkeyeprotect;

import android.app.Application;

import com.york.library.EyeProtectLifecycleCallbacks;

/**
 * @author : York
 * @time : 2023/5/25
 * @Description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new EyeProtectLifecycleCallbacks());
    }
}
