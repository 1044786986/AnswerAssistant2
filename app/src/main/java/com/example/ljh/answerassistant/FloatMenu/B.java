package com.example.ljh.answerassistant.FloatMenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ljh.answerassistant.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ljh on 2018/2/27.
 */

public class B extends AppCompatActivity{
    private ImageView imageView;

    private CircleImageView tv1,tv2,tv3,tv4,tv5;

    private List<CircleImageView> tvList;
    private boolean isOpen = false;
    private int FloatMenuX;
    private int FloatMenuY;
    private int screenHeight;
    private int r;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        tvList = new ArrayList<>();
        initView();
    }

    private void openMenu(){
        isOpen = true;
        int sum = tvList.size();
        double angle = 180 / (sum - 1);
        double angle2;
        double x;
        double y;
        Log.i("aaa","sum = " + sum);
        for (int i=0;i<tvList.size();i++){
            AnimatorSet animatorSet = new AnimatorSet();
             angle2 = angle * i - 90;
             x = -dp2px(r) * Math.cos(angle2 * Math.PI / 180);
             y = -dp2px(r) * Math.sin(angle2 * Math.PI / 180);

            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(tvList.get(i),"translationX",FloatMenuX,(float)x),
                    ObjectAnimator.ofFloat(tvList.get(i),"translationY",FloatMenuY,(float)y)
//                    ObjectAnimator.ofFloat(tvList.get(i),"alpha",0,1).setDuration(0)
            );
//            animatorSet.setInterpolator(new BounceInterpolator());    //动画结束时，反弹几下
            animatorSet.setDuration(200);
            animatorSet.start();
        }
    }

    private void closeMenu(){
        isOpen = false;
        for (int i=0;i<tvList.size();i++){
            AnimatorSet animatorSet = new AnimatorSet();
            double angle = 45 * i - 90;
            double x = -dp2px(r) * Math.cos(angle * Math.PI / 180);
            double y = -dp2px(r) * Math.sin(angle * Math.PI / 180);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(tvList.get(i),"translationX",(float)x,FloatMenuX),
                    ObjectAnimator.ofFloat(tvList.get(i),"translationY",(float)y,FloatMenuY)
//                    ObjectAnimator.ofFloat(tvList.get(i),"alpha",1,0).setDuration(0)
            );
//            animatorSet.setInterpolator(new BounceInterpolator());
            animatorSet.setDuration(200);
            animatorSet.start();
        }
    }

    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }

    private void initView(){
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);

        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        tvList.add(tv5);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    closeMenu();
                    Log.i("aaa","关闭菜单");
                }else {
                    openMenu();
                    Log.i("aaa","打开菜单");
                }
            }
        });
        setFloatMenuXY();
        getScreenHeight();
    }

    private void setFloatMenuXY(){
        FloatMenuX = (int) imageView.getX();
        FloatMenuY = (int) imageView.getY();
    }

    private void getScreenHeight(){
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        r = (int)screenHeight / 24;
        Log.i("aaa","X = " + displayMetrics.widthPixels);
        Log.i("aaa","Y = " + displayMetrics.heightPixels);
    }
}
