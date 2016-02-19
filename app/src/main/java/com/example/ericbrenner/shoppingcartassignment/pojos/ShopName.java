package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ericbrenner on 2/13/16.
 */
public class ShopName implements Parcelable {
    public String value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
    }

    public static final Parcelable.Creator<ShopName> CREATOR
            = new Parcelable.Creator<ShopName>() {
        public ShopName createFromParcel(Parcel in) {
            return new ShopName(in);
        }

        public ShopName[] newArray(int size) {
            return new ShopName[size];
        }
    };

    private ShopName(Parcel in) {
        value = in.readString();
    }
}
