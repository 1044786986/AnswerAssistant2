package com.example.ljh.answerassistant.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;

/**
 * Created by ljh on 2018/2/24.
 */

public class TransformationManager {
    /**
     * Bitmap转byte[]
     * @param bitmap
     * @return
     */
    public static byte[] BitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * dp转px
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param sp
     * @param context
     * @return
     */
    public static int sp2px(int sp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param px
     * @return
     */
    public static int px2dp(float px,Context context){
        float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int)(px * 160 / scale + 0.5f);
    }
}
