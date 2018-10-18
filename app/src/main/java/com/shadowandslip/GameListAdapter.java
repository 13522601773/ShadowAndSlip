package com.shadowandslip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 姓名：mengc
 * 日期：2018/8/2
 * 功能：游戏列表适配器
 */

public class GameListAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    public GameListAdapter(List<TestBean> gameListEntities) {
        super(R.layout.item_gallery, gameListEntities);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        ImageView ivShadow = (ImageView) helper.getView(R.id.iv_shadow);
        ImageView iv = (ImageView) helper.getView(R.id.iv);
        int i = item.getI();
        Bitmap bm=null;
        switch (i){
            case 0:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.aa);
                break;
            case 1:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.bb);
                break;
            case 2:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.cc);
                break;
            case 3:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.aa);
                break;
            case 4:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.cc);
                break;
            case 5:
                bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.bb);
                break;
        }
        iv.setImageBitmap(bm);
        Bitmap bitmap = ImageUtils.zoomBitmap(bm, 500, 500);
        ivShadow.setImageBitmap(compress(ImageUtils.getReverseBitmapById2(bitmap)));
    }


    public static Bitmap compress(Bitmap bitmap) {

        Matrix matrix = new Matrix();

        matrix.setScale(1f, 0.3f);

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),

                bitmap.getHeight(), matrix, true);

        return bitmap;
    }
}
