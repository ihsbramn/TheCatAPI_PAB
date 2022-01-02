package com.rab.thecatapi_pab.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class fav implements Parcelable {

    private int id;
    private String image_id;

    public fav(int id, String image_id) {
        this.id = id;
        this.image_id = image_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image_id);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.image_id = source.readString();
    }

    protected fav(Parcel in) {
        this.id = in.readInt();
        this.image_id = in.readString();
    }

    public static final Creator<fav> CREATOR = new Creator<fav>() {
        @Override
        public fav createFromParcel(Parcel source) {
            return new fav(source);
        }

        @Override
        public fav[] newArray(int size) {
            return new fav[size];
        }
    };
}
