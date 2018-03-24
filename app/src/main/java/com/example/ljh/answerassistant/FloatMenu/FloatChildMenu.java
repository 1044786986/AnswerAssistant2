package com.example.ljh.answerassistant.FloatMenu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljh on 2018/2/28.
 */

public class FloatChildMenu extends View{
    private List<FloatMenuItem> itemList = new ArrayList<>();
    private int screenWidth;
    private int screenHeight;


    public FloatChildMenu(Context context) {
        super(context);
    }

    public FloatChildMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatChildMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setItemList(List<FloatMenuItem> list){
        this.itemList = list;
    }

    public void setScreenWidth(int screenWidth){
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight){
        this.screenHeight = screenHeight;
    }
}
