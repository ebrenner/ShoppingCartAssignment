package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class Resource implements Parcelable {
    public String name;
    public ShopOrShopItemResponse response;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(response, flags);
    }

    public static final Parcelable.Creator<Resource> CREATOR
            = new Parcelable.Creator<Resource>() {
        public Resource createFromParcel(Parcel in) {
            return new Resource(in);
        }

        public Resource[] newArray(int size) {
            return new Resource[size];
        }
    };

    private Resource(Parcel in) {
        name = in.readString();
        response = in.readParcelable(ShopOrShopItemResponse.class.getClassLoader());
    }
}
