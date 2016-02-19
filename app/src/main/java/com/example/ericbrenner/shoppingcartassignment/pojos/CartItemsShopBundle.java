package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class CartItemsShopBundle implements Parcelable {
    public String shopId;
    public ArrayList<CartItem> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shopId);
        dest.writeList(items);
    }

    public static final Parcelable.Creator<CartItemsShopBundle> CREATOR
            = new Parcelable.Creator<CartItemsShopBundle>() {
        public CartItemsShopBundle createFromParcel(Parcel in) {
            return new CartItemsShopBundle(in);
        }

        public CartItemsShopBundle[] newArray(int size) {
            return new CartItemsShopBundle[size];
        }
    };

    private CartItemsShopBundle(Parcel in) {
        shopId = in.readString();
        items = new ArrayList<>();
        in.readTypedList(items, CartItem.CREATOR);
    }
}
