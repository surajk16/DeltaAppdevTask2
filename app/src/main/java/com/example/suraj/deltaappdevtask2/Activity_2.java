package com.example.suraj.deltaappdevtask2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.out;

public class Activity_2 extends AppCompatActivity {

    int count = 0, flag=-1;
    File images;
    Uri uri,outputUri;
    ImageView iv;
    boolean imgset=false,captionset=false;
    Bitmap mybmp;
    EditText caption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_2);

            iv = (ImageView) findViewById(R.id.image);
            caption = (EditText) findViewById(R.id.caption);
            Bundle extras = getIntent().getExtras();
            if (extras != null)
                count = extras.getInt("count");


    }

    public void addImage(View v) {
        RadioButton r1 = (RadioButton) findViewById(R.id.camerarb);
        RadioButton r2 = (RadioButton) findViewById(R.id.galleryrb);

        if (r1.isChecked()) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String name = "picture_testing" + String.valueOf(count) + ".jpg";
            images = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), name);
            i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
            i.putExtra("return-data", true);
            startActivityForResult(i, 0);
        } else if (r2.isChecked()) {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:

                    if (data != null) {
                        mybmp = (Bitmap) data.getExtras().get("data");
                        uri = data.getData();
                        imgset=true;
                        mybmp = Bitmap.createScaledBitmap(mybmp, 300, 300, true);
                        iv.setImageBitmap(mybmp);

                        flag = 0;
                       // Toast.makeText(this, "Saved at" + images.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        imgset = true;
                    } else
                        Toast.makeText(this, "Not Saved", Toast.LENGTH_LONG).show();

                    break;

                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

                    break;
            }


        } else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            uri = data.getData();
            //iv.setImageURI(imageUri);
            try {
                mybmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imgset = false;
                Intent CropIntent = new Intent("com.android.camera.action.CROP");

                CropIntent.setDataAndType(uri, "image/*");

                CropIntent.putExtra("crop", "true");
                CropIntent.putExtra("outputX", 300);
                CropIntent.putExtra("outputY", 300);
                CropIntent.putExtra("aspectX", 1);
                CropIntent.putExtra("aspectY", 1);
                CropIntent.putExtra("scaleUpIfNeeded", true);
                CropIntent.putExtra("return-data", true);

                startActivityForResult(CropIntent, 3);

                if (imgset==false) iv.setImageBitmap(mybmp);

                imgset = true;
                flag = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == 3) {

                    if (data!=null)
                 {

                    Bundle bundle = data.getExtras();

                    mybmp = bundle.getParcelable("data");
                    mybmp = Bitmap.createScaledBitmap(mybmp, 300, 300, true);
                    iv.setImageBitmap(mybmp);
                    imgset=true;

                }
            }
        }

    public void goto1 (View v)
    {
        if (caption.getText().toString().length()==0) captionset = false;
        else captionset = true;

        if (imgset==false || captionset==false)
            Toast.makeText(this,"Image or Caption not present",Toast.LENGTH_LONG).show();

        else
        {
            count++;
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("bitmap",mybmp);
           // else if (flag==1) i.putExtra("uri",uri);
            i.putExtra("count",count);
            i.putExtra("flag",flag);
            i.putExtra("caption",caption.getText().toString());

            startActivity(i);
        }
    }





}
