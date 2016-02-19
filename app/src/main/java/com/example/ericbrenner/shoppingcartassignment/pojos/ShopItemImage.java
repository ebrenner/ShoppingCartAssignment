package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class ShopItemImage implements Parcelable {
    public String location;
    public String alt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(alt);
    }

    public static final Parcelable.Creator<ShopItemImage> CREATOR
            = new Parcelable.Creator<ShopItemImage>() {
        public ShopItemImage createFromParcel(Parcel in) {
            return new ShopItemImage(in);
        }

        public ShopItemImage[] newArray(int size) {
            return new ShopItemImage[size];
        }
    };

    private ShopItemImage(Parcel in) {
        location = in.readString();
        alt = in.readString();
    }
}
