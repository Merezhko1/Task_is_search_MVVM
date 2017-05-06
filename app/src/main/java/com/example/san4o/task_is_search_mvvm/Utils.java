package com.example.san4o.task_is_search_mvvm;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by san4o on 12.03.17.
 */


public class Utils {
    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }
}