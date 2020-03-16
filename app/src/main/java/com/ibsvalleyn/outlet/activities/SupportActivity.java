package com.ibsvalleyn.outlet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ibsvalleyn.outlet.R;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ic_back=findViewById(R.id.ic_back);
        ic_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==ic_back)
            finish();
    }
}
