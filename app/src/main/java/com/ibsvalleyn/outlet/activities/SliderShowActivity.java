package com.ibsvalleyn.outlet.activities;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.AccordionTransformer;

import com.ibsvalleyn.outlet.R;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SliderShowActivity extends AppCompatActivity {
    private static final String TAG = "AAQQDDSFFSF";

    private KProgressHUD kProgressHUD;
    private ImageButton backBtn;
    private SliderLayout slider;
    List<String> images = new ArrayList<>();
    private ArrayList<String> temp;

    public SliderShowActivity() {
        // Required empty public constructor
    }

    private ImageView sliderIamge;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slider_show_activity);
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

        sliderIamge = findViewById(R.id.sliderIamge);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        slider = findViewById(R.id.slider);
        if (myList.size() == 1) {
            slider.setVisibility(View.GONE);
            sliderIamge.setVisibility(View.VISIBLE);
            //String aUrl = myList.get(0).replace("http", "https");

            Picasso.with(this).load(myList.get(0)).into(sliderIamge);
        } else {
            slider.setVisibility(View.VISIBLE);
            sliderIamge.setVisibility(View.GONE);
            slider.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            slider.setDuration(3000);
            slider.setPagerTransformer(false, new AccordionTransformer());

            slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);

            for (int i = 0; i < myList.size(); i++) {
//
//

            //    String aUrl = myList.get(i).replace("http", "https");

                DefaultSliderView view2 = new DefaultSliderView(this);
                view2.image(myList.get(i))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                slider.addSlider(view2);


            }
        }


    }
}