package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class CartItem implements Parcelable {
    public String merchantId;
    public String itemId;
    public String itemVariantId;
    public String price;
    public String currency;
    public int quantities;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merchantId);
        dest.writeString(itemId);
        dest.writeString(itemVariantId);
        dest.writeString(price);
        dest.writeString(currency);
        dest.writeInt(quantities);
    }

    public static final Parcelable.Creator<CartItem> CREATOR
            = new Parcelable.Creator<CartItem>() {
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    private CartItem(Parcel in) {
        merchantId = in.readString();
        itemId = in.readString();
        itemVariantId = in.readString();
        price = in.readString();
        currency = in.readString();
        quantities = in.readInt();
    }
}
