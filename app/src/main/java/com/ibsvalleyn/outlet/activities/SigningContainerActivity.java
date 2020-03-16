package com.ibsvalleyn.outlet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ibsvalleyn.outlet.R;

public class SigningContainerActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn, signUp;
    private int positionProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signing_container_activity);
        signIn = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);
        Intent intent = getIntent();
        positionProfile = intent.getIntExtra("positionProfile", 0);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == signIn) {
            Intent intent = new Intent(SigningContainerActivity.this, SigningActivity.class);
            intent.putExtra("signing", 1);
            startActivity(intent);
        }
        if (view == signUp) {
            Intent intent = new Intent(SigningContainerActivity.this, SigningActivity.class);
            intent.putExtra("signing", 2);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (positionProfile == 5) {
            startActivity(new Intent(SigningContainerActivity.this, MainActivity.class));
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
