package com.example.ljh.answerassistant.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ljh.answerassistant.R;

/**
 * Created by ljh on 2018/3/19.
 */

public class Text extends AppCompatActivity{
    LinearLayout linearLayout;
    WindowManager windowManager;
    DisplayMetrics displayMetrics;
    WindowManager.LayoutParams layoutParams;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        linearLayout = findViewById(R.id.linearLayout);
        final ImageView imageView = new ImageView(this);
        final ImageView imageView1 = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        imageView1.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round));        imageView.setMaxWidth(80);
        imageView.setMaxHeight(80);
        imageView1.setMaxHeight(80);
//        linearLayout.addView(imageView);
//        linearLayout.addView(imageView,layoutParams);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(ObjectAnimator.ofFloat(imageView,"translationY",imageView.getY(),100),
                        ObjectAnimator.ofFloat(imageView1,"translationY",imageView1.getY(),100));
                animatorSet.setDuration(5000);
                animatorSet.start();
            }
        });
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams = new WindowManager.LayoutParams();
        layoutParams.token = linearLayout.getWindowToken();
        layoutParams.flags = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        layoutParams.alpha = 1;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(imageView1,layoutParams);
    }
}
