package com.example.ljh.answerassistant.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ljh.answerassistant.R;
import com.example.ljh.answerassistant.service.FloatLogoMenuService;
import com.yw.game.floatmenu.FloatItem;
import com.yw.game.floatmenu.FloatLogoMenu;
import com.yw.game.floatmenu.FloatMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button,btOpen,btClose;
    private ImageView imageView;
    private FloatLogoMenu floatLogoMenu;

    private FloatLogoMenuService logoService;
    private List<FloatItem> list;
    private ServiceConnection serviceConnection;

    private final int blue = 0xff2D82FF;   //蓝色
    private final int transparent = 0x00FFFFFF; //透明

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemList();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btOpen:
                break;
            case R.id.btClose:
                break;
        }
    }

    private void addItemList(){
        list = new ArrayList<>();
        list.add(new FloatItem("矩形",blue,
                transparent,BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)));
        list.add(new FloatItem("手绘",blue,transparent,
                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round)));
    }

    private void initView(){
        imageView = findViewById(R.id.imageView);
        btOpen = findViewById(R.id.btOpen);
        btClose = findViewById(R.id.btClose);
//        logoService = new FloatLogoMenuService();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("aaa","onServiceConnected");
                FloatLogoMenuService.LocalBinder localBinder = (FloatLogoMenuService.LocalBinder) service;
                logoService = localBinder.getService();

                floatLogoMenu = new FloatLogoMenu.Builder()
                .withContext(logoService.getApplication())
                .logo(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                .drawCicleMenuBg(false)
                .backMenuColor(transparent)
                .defaultLocation(FloatLogoMenu.RIGHT)
                .setFloatItems(list)
                .drawRedPointNum(false)
                .showWithListener(new FloatMenuView.OnMenuClickListener() {
                    @Override
                    public void onItemClick(int position, String title) {
                        switch (position){
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }

                    @Override
                    public void dismiss() {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("aaa","onServiceDisconnected");
            }
        };
        /**
         * 绑定服务
         */
        Intent intent = new Intent(MainActivity.this,FloatLogoMenuService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

}
