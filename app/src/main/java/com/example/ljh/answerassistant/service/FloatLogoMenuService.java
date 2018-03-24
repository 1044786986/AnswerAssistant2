package com.example.ljh.answerassistant.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by ljh on 2018/2/26.
 */

public class FloatLogoMenuService extends Service {
    public LocalBinder localBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public FloatLogoMenuService getService() {
            return FloatLogoMenuService.this;
        }
}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("aaa","----------onBind()");
        return localBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i("aaa","----------unbindService()");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("aaa","----------onStartCommand()");
        startForegroundService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaa","----------onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        Log.i("aaa","----------onDestroy()");
    }


}
