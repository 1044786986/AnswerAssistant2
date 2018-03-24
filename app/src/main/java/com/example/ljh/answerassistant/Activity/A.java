package com.example.ljh.answerassistant.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.aip.ocr.AipOcr;
import com.example.ljh.answerassistant.R;
import com.example.ljh.answerassistant.manager.AipOrcManager;
import com.example.ljh.answerassistant.manager.CompressManager;
import com.example.ljh.answerassistant.manager.ThreadPoolManager;
import com.example.ljh.answerassistant.manager.TransformationManager;

import org.json.JSONObject;

/**
 * Created by ljh on 2018/2/24.
 */

public class A extends AppCompatActivity{
    AipOcr aipOcr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aipOcr = AipOrcManager.getAipOcr();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ThreadPoolManager.getThreadPoolManager().execute(new Runnable() {
            @Override
            public void run() {
                Log.i("aaa","开始运行");
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
                Bitmap bitmap1 = CompressManager.test(bitmap,bitmap.getWidth(),bitmap.getHeight());
                if(bitmap != null || !bitmap.isRecycled()){
                    bitmap.recycle();
                }
                byte[] bytes = TransformationManager.BitmapToByte(bitmap1);

                if(bitmap1 != null || !bitmap1.isRecycled()){
                    bitmap1.recycle();
                }
                JSONObject jsonObject = aipOcr.basicGeneral(bytes,null);
                Log.i("aaa","jsonObject = " + jsonObject);
                Log.i("aaa","结束运行");
            }
        });
    }
}
