package com.shadowandslip;

import android.view.View;

/**
 * 姓名：mengc
 * 日期：2018/8/13
 * 功能：
 */

public class ScaleTransformer implements GalleryLayoutManager.ItemTransformer {

    private static final float MIN_SCALE = 0.65f;
    private static final float MAX_ROTATION = 20;
    private static final float TRANSLATION = 0.01f;
    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View view, float position) {
        final float scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
        final float rotation = MAX_ROTATION * Math.abs(position);
        if (position <= 0f) {
            view.setTranslationX(view.getWidth() * -position * TRANSLATION);
            view.setPivotY(0.5f * view.getHeight());
            view.setPivotX(0.5f * view.getWidth());
            view.setScaleX(scale);
            view.setScaleY(scale);
            view.setRotationY(rotation);
        } else if (position <= 1f) {
            view.setTranslationX(view.getWidth() * -position * TRANSLATION);
            view.setPivotY(0.5f * view.getHeight());
            view.setPivotX(0.5f * view.getWidth());
            view.setScaleX(scale);
            view.setScaleY(scale);
            view.setRotationY(-rotation);
        }
    }
}
