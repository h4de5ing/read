package com.code19.read.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Create by h4de5ing 2016/5/20 020
 */
public class PicassoUtils {

    public static void loadImageWithSize(Context context, String path, int width, int height, ImageView imageView) {

        Picasso.with(context).load(path).resize(width, height).centerCrop().into(imageView);
    }

    public static void loadImageWithHolder(Context context, String path, int resID, ImageView imageView) {
        Picasso.with(context).load(path).fit().placeholder(resID).into(imageView);
    }

    public static void loadImageWithCrop(Context context, String path, ImageView imageView) {
        Picasso.with(context).load(path).transform(new CropSquareTransformation()).into(imageView);
    }

    /**
     * 实现对图片的自定义裁剪
     */
    public static class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result!=null){
                source.recycle();;
            }
            return result;
        }


        @Override
        public String key() {
            return "square()";
        }
    }
}
