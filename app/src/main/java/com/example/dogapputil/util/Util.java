package com.example.dogapputil.util;

import android.content.Context;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dogapputil.R;

public class Util {

    public static void showImage(ImageView imageView, String url, CircularProgressDrawable circularProgressDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(circularProgressDrawable)
                .placeholder(R.drawable.placeholder)
                .error(R.mipmap.ic_launcher);


        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);
    }


    public static CircularProgressDrawable getCircularProgressDrawable (Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }
}
