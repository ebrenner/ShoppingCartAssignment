package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class ShopItem implements Parcelable {
    public String itemId;
    public String merchantId;
    public String shopId;
    public String name;
    public ArrayList<ShopItemImage> images;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(merchantId);
        dest.writeString(shopId);
        dest.writeString(name);
        ShopItemImage[] imageArray = images.toArray(new ShopItemImage[images.size()]);
        dest.writeParcelableArray(imageArray, flags);
    }

    public static final Parcelable.Creator<ShopItem> CREATOR
            = new Parcelable.Creator<ShopItem>() {
        public ShopItem createFromParcel(Parcel in) {
            return new ShopItem(in);
        }

        public ShopItem[] newArray(int size) {
            return new ShopItem[size];
        }
    };

    private ShopItem(Parcel in) {
        itemId = in.readString();
        merchantId = in.readString();
        shopId = in.readString();
        name = in.readString();
        Parcelable[] imageArray = in.readParcelableArray(ShopItemImage.class.getClassLoader());
        images = new ArrayList<>();
        for (Parcelable p : imageArray) {
            images.add((ShopItemImage)p);
        }
    }
}
