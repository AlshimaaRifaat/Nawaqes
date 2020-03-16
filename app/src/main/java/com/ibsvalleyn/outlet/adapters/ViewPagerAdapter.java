package com.ibsvalleyn.outlet.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.ibsvalleyn.outlet.R;
import com.ibsvalleyn.outlet.activities.Product_details;
import com.ibsvalleyn.outlet.activities.SliderShowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<String> images;
    private LayoutInflater inflater;
    private Product_details context;

    public ViewPagerAdapter(Product_details context, List<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View view1 = inflater.inflate(R.layout.item_slider_image, view, false);
        ImageView myImage = view1.findViewById(R.id.image);
        ArrayList<String> myList = new ArrayList<String>();
        myList.addAll(images);
        myImage.setOnClickListener(view2 -> {

            Intent intent = new Intent(context, SliderShowActivity.class);
            intent.putExtra("mylist", myList);
            context.startActivity(intent);

        });
       // String aUrl = images.get(position).replace("http", "https");

        Picasso.with(context).load(images.get(position)).into(myImage);
        view.addView(view1, 0);
        return view1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
