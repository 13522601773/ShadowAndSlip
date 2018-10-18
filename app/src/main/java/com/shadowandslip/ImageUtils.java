package com.shadowandslip;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * 图片相关工具类
 */
public final class ImageUtils {


    /**
     * @param bitmap 对象
     * @param w      要缩放的宽度
     * @param h      要缩放的高度
     * @return newBmp 新 Bitmap对象
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }


    public static Bitmap getReverseBitmapById2(Bitmap mBitmap) {
        // get the source bitmap
        //1. 倒立图
        Matrix matrix = new Matrix();

        matrix.preScale(1, -1);

        int w = mBitmap.getWidth();

        int h = mBitmap.getHeight();

        Bitmap reflectionImg = Bitmap.createBitmap(mBitmap, 0, 0, w, h, matrix, false);

        Paint paint = new Paint();

        Canvas canvas = new Canvas(reflectionImg);
        canvas.drawBitmap(reflectionImg, 0, 0, paint); //绘制倒立图
        return getAlphaBitmap(reflectionImg);
    }

    private static Bitmap getAlphaBitmap(Bitmap sourceImg) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];

        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg

                .getWidth(), sourceImg.getHeight());// 获得图片的ARGB值

        //number的范围为0-100,0为全透明，100为不透明
        float number = 100;

        //透明度数值
        float alpha = number * 255 / 100;

        //图片渐变的范围（只设置图片一半范围由上到下渐变，上面不渐变，即接近边缘的那一半）
        float range = sourceImg.getHeight();

        //透明度渐变梯度，每次随着Y坐标改变的量，因为最终在边缘处要变为0
        float pos = (number * 1.0f) / range;

        //循环开始的下标，设置从什么时候开始改变
        int start = sourceImg.getWidth() * (sourceImg.getHeight() - (int) range);

        for (int i = start; i < argb.length; i++) {
            //同一行alpha数值不改变，因为是随着Y坐标从上到下改变的
            if (i % sourceImg.getWidth() == 0) {
                number = number - pos;
                alpha = number * 255 / 100;
            }
            argb[i] = ((int) alpha << 24) | (argb[i] & 0x00FFFFFF);
        }

        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg

                .getHeight(), Bitmap.Config.ARGB_8888);
        return sourceImg;
    }
}