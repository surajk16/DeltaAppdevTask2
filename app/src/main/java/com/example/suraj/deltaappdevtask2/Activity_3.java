package com.example.suraj.deltaappdevtask2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity_3 extends AppCompatActivity {

    Bitmap mybmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Intent intent = getIntent();
        mybmp = intent.getParcelableExtra("bitmap");
        ImageView iv = (ImageView) findViewById(R.id.a3iv);
        iv.setImageBitmap(mybmp);
    }
}
