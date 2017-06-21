package com.example.suraj.deltaappdevtask2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    int count=0,flag;
    Bitmap mybmp;
    String caption;
    Uri uri;
    private ListView clv;
    private customadapter adapter;
    public  static final int RequestPermissionCode  = 1 ;
    private static ArrayList<ListModel> mListModel = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            count = extras.getInt("count");
            caption = extras.getString("caption");
            flag = extras.getInt("flag");
        }

        Intent intent = getIntent();
        mybmp = intent.getParcelableExtra("bitmap");
        uri = intent.getParcelableExtra("uri");

        EnableRuntimePermission();



        setContentView(R.layout.activity_main);

        clv = (ListView) findViewById(R.id.listview);

        if (count != 0) {

            mListModel.add(new ListModel(mybmp, caption,uri,flag));
            adapter = new customadapter(getApplicationContext(), mListModel);
            clv.setAdapter(adapter);

        }

        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListModel m = (ListModel) clv.getItemAtPosition(position);
                Bitmap b = m.getListimg();
                Intent i = new Intent(getApplicationContext(), Activity_3.class);
                i.putExtra("bitmap",b);
                startActivity(i);

            }
        });

    }

    public void goto2 (View v)
    {
        Intent i = new Intent(this, Activity_2.class);
        i.putExtra("count",count);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.CAMERA))
        {

           // Toast.makeText(MainActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                   // Toast.makeText(MainActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this,"Your application cannot access camera.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}


