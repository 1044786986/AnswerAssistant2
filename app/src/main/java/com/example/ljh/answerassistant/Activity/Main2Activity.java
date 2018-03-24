package com.example.ljh.answerassistant.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.ljh.answerassistant.FloatMenu.FloatMenu;
import com.example.ljh.answerassistant.FloatMenu.FloatMenuItem;
import com.example.ljh.answerassistant.R;
import com.example.ljh.answerassistant.service.FloatLogoMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljh on 2018/3/5.
 */

public class Main2Activity extends AppCompatActivity{
    private List<FloatMenuItem> itemList = new ArrayList<>();
    private Bitmap mLogoBitmap;
    private FloatMenu mFloatMenu;

    private FloatLogoMenuService mFloatLogoMenuService;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView(){
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mLogoBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
                Bitmap openLogo = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
                itemList.add(new FloatMenuItem("123",0x00EAEAEA,bitmap));
                itemList.add(new FloatMenuItem("123",0x00EAEAEA,bitmap));
                itemList.add(new FloatMenuItem("123",0x00EAEAEA,bitmap));
                itemList.add(new FloatMenuItem("123",0x00EAEAEA,bitmap));
                itemList.add(new FloatMenuItem("123",0x00EAEAEA,bitmap));

                FloatLogoMenuService.LocalBinder localBinder = (FloatLogoMenuService.LocalBinder) service;
                mFloatLogoMenuService = localBinder.getService();

                mFloatMenu = new FloatMenu.Builder()
                        .setFloatMenuItem(itemList)
                        .setFloatMenuLogo(mLogoBitmap)
                        .setFloatMenuOpenLogo(openLogo)
                        .setContext(mFloatLogoMenuService.getApplicationContext())
                        .setRotate(true)
                        .setTimer(true)
                        .setOnMenuClickListener(new FloatMenu.OnMenuClickListener() {
                            @Override
                            public void onClick(int position, View view) {
                                Toast.makeText(mFloatLogoMenuService.getApplicationContext(),"点击了 " + position,Toast.LENGTH_LONG).show();
                            }
                        });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent(Main2Activity.this,FloatLogoMenuService.class);
        bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
    }
}
