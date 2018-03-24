package com.example.ljh.answerassistant.FloatMenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by ljh on 2018/3/5.
 */

public class LogoView extends View{
    public static final int OPEN_LOGO = 0;  //打开悬浮球
    public static final int CLOSE_LOGO = 1; //关闭悬浮球
    public static final int ROTATION_LOGO = 2;//旋转悬浮球
    public static final int HIDE_LOGO = 3;  //隐藏悬浮球

    private Bitmap mLogoBitmap;             //菜单logo
    private Bitmap mOpenLogoBitmap;         //打开菜单后的Logo
    private int mFloatMenuBg = 0x00FFFFFF;  //菜单默认背景色
    protected int r;                        //logo半径
    private int mRotation_time = 200;       //旋转速度
    private float mScale;                   //压缩率

    private Paint mCirclePaint;
    private Matrix mMatrix;
    private AnimatorSet mAnimatorSet;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case OPEN_LOGO:
                    initCircleBitmap(mOpenLogoBitmap);
                    invalidate();
                    break;
                case CLOSE_LOGO:
                    initCircleBitmap(mLogoBitmap);
                    invalidate();
                    break;
                case ROTATION_LOGO:
                    mAnimatorSet.start();
                    break;
                case HIDE_LOGO:
//                    mCirclePaint = new Paint();
//                    Paint paint = new Paint();
//                    paint.setColor(Color.BLUE);
//                    paint.setStrokeWidth(60);
//                    Bitmap bitmap = Bitmap.createBitmap(r/2,r, Bitmap.Config.RGB_565);
//                    Canvas canvas = new Canvas(bitmap);
//                    canvas.drawRect(10,10,20,20,paint);
//                    canvas.drawCircle(50,50,50,paint);
//                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),mMatrix,paint);

                    break;
            }
        }
    };

    public LogoView(Context context) {
        super(context);
        init(context);
    }

    public LogoView(Context context,Bitmap bitmap){
        super(context);
        mLogoBitmap = bitmap;
        init(context);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(r * 2,r * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(r,r,r,mCirclePaint);
    }

    protected void translation(){
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(this,"translationX",0,360));
        mAnimatorSet.setDuration(mRotation_time);
        mAnimatorSet.start();
    }

    /**
     * 初始化旋转动画
     */
    private void initRotation(){
        mRotation_time = FloatMenu.mRotate_time;
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(this,"rotation",0,360));
        mAnimatorSet.setDuration(mRotation_time);
    }

    /**
     * 开始旋转动画
     */
    protected void startRotation(){
        mHandler.sendEmptyMessage(ROTATION_LOGO);
}

    /**
     * 打开悬浮球的logo
     */
    protected void drawOpenLogo(){
        mHandler.sendEmptyMessage(OPEN_LOGO);
    }

    /**
     * 关闭悬浮球的logo
     */
    protected void drawCloseLogo(){
        mHandler.sendEmptyMessage(CLOSE_LOGO);
    }

    protected void drawHideLogo(){
        Log.i("aaa","drawHideLogo");
        mHandler.sendEmptyMessage(HIDE_LOGO);
    }

    /**
     * 将方形图片变为圆形
     */
    private void initCircleBitmap(Bitmap bitmap){
        mMatrix = new Matrix();
        mCirclePaint = new Paint();

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mScale = (r * 2.0f) / Math.min(bitmap.getWidth(),bitmap.getHeight());
        mMatrix.setScale(mScale,mScale);
        bitmapShader.setLocalMatrix(mMatrix);
        mCirclePaint.setShader(bitmapShader);
        mCirclePaint.setAntiAlias(true);
    }

    private void init(Context context){
        r = FloatMenu.mScreenHeight / 24;
        initRotation();
        if(mLogoBitmap != null) {
            initCircleBitmap(mLogoBitmap);
        }
    }

    public void setLogoBitmap(Bitmap logoBitmap){
        this.mLogoBitmap = logoBitmap;
    }

    public void setOpenLogoBitmap(Bitmap openLogoBitmap){
        this.mOpenLogoBitmap = openLogoBitmap;
    }

    public void setFloatMenuBg(int FloatMenuBg){
        this.mFloatMenuBg = FloatMenuBg;
    }

}
