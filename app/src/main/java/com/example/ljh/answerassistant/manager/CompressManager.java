package com.example.ljh.answerassistant.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 * Created by ljh on 2018/2/24.
 */

public class CompressManager {
    final static int maxSize = 400000;

    public static Bitmap getBitmap(String imgPath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;     //是否只加载图片的宽和高
        options.inPurgeable = true;             //系统中内存不足时，允许被回收
        options.inInputShareable = true;
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath);

    }

    public static Bitmap compress(String imgPath,float pixelW,float pixelH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);

        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;
        //想要缩放的目标尺寸
//        float hh = pixelH;
//        float ww = pixelW;
        int size = 1;
        if(w > h && w > pixelW){    //如果宽度大的话根据宽度固定大小缩放
            size = (int)(options.outWidth / pixelW);
        }else if(w < h && h > pixelH){  //如果高度高的话根据宽度固定大小缩放
            size = (int)(options.outWidth / pixelH);
        }
        if(size <= 0){
            size = 1;
        }
        options.inSampleSize = size;
        bitmap = BitmapFactory.decodeFile(imgPath,options);
        return bitmap;
    }

    public static Bitmap compress(Bitmap bitmap,float pixelW,float pixelH){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        if(byteArrayOutputStream.toByteArray().length / 1024 > 1024){   ///判断如果图片大于1M,进行压缩避免在生成图片
//            byteArrayOutputStream.reset();
//            bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
//        }
        int option = 100;
        while(byteArrayOutputStream.toByteArray().length/1024 > maxSize){
            byteArrayOutputStream.reset();
            option-=10;
            bitmap.compress(Bitmap.CompressFormat.JPEG,option,byteArrayOutputStream);
            Log.i("aaa","yes = " + option);
        }

//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        Bitmap bitmap1 = BitmapFactory.decodeStream(byteArrayInputStream,null,options);
        options.inJustDecodeBounds = false;
        int weight = options.outWidth;
        int hight = options.outHeight;
        int size = 1;
        if(weight > hight && weight > pixelW){
            size = (int)(weight/pixelW);
        }else if(weight < hight && hight > pixelH){
            size = (int)(hight/pixelH);
        }
        if(size <= 0){
            size = 1;
        }
        options.inSampleSize = size;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap bitmap1 = BitmapFactory.decodeStream(byteArrayInputStream,null,options);
        return bitmap1;
    }

    public static Bitmap test(Bitmap bitmap,float pixelW,float pixelH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return BitmapFactory.decodeStream(byteArrayInputStream,null,options);
    }

    public static Bitmap test2(Bitmap bitmap,float pixelW,float pixelH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
//        BitmapFactory.decodeFile(imagePath, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        int height = options.outHeight;
        int width= options.outWidth;
        int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
        int minLen = Math.min(height, width); // 原图的最小边长
        if(minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
            float ratio = (float)minLen / 100.0f; // 计算像素压缩比例
            inSampleSize = (int)ratio;
        }
        options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
        options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
        Bitmap bm = BitmapFactory.decodeStream(byteArrayInputStream,null,options); // 解码文件
        Log.w("TAG", "size: " + bm.getByteCount() + " width: " + bm.getWidth() + " heigth:" + bm.getHeight());
        return bm;
    }
}
