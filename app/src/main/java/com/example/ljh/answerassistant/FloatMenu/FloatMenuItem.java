package com.example.ljh.answerassistant.FloatMenu;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by ljh on 2018/2/28.
 */

public class FloatMenuItem implements Serializable{
    private String title;
    private int titleColor;
    private Bitmap logo;

    public FloatMenuItem(String title,int titleColor,Bitmap logo){
        this.title = title;
        this.titleColor = titleColor;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }
}
