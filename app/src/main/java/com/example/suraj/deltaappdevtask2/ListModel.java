package com.example.suraj.deltaappdevtask2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by suraj on 17-06-2017.
 */

public class ListModel {

    private Bitmap Listimg ;
    private  String Listcaption ="";
    private Uri uri;
    private int flag;

    public ListModel(Bitmap listimg, String listcaption, Uri uri, int flag) {
        Listimg = listimg;
        Listcaption = listcaption;
        this.uri = uri;
        this.flag=flag;
    }

    /*********** Set Methods ******************/

    public void setListimg(Bitmap listimg) {
        Listimg = listimg;
    }

    public void setListcaption(String listcaption) {
        Listcaption = listcaption;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    /*********** Get Methods ****************/

    public Bitmap getListimg() {
        return Listimg;
    }

    public String getListcaption() {
        return Listcaption;
    }

    public Uri getUri() {
        return uri;
    }

    public int getFlag() {
        return flag;
    }
}

