package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ericbrenner on 2/13/16.
 */
public class Shop implements Parcelable {
    public String shopId;
    public ShopName name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shopId);
        dest.writeParcelable(name, flags);
    }

    public static final Parcelable.Creator<Shop> CREATOR
            = new Parcelable.Creator<Shop>() {
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    private Shop(Parcel in) {
        shopId = in.readString();
        name = in.readParcelable(ShopName.class.getClassLoader());
    }
}
