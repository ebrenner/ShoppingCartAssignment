package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class CartResponse implements Parcelable {
    public int status;
    public String message;
    public String shopperId;
    public ArrayList<CartItemsShopBundle> shopCarts;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(message);
        dest.writeString(shopperId);
        dest.writeList(shopCarts);
    }

    public static final Parcelable.Creator<CartResponse> CREATOR
            = new Parcelable.Creator<CartResponse>() {
        public CartResponse createFromParcel(Parcel in) {
            return new CartResponse(in);
        }

        public CartResponse[] newArray(int size) {
            return new CartResponse[size];
        }
    };

    private CartResponse(Parcel in) {
        status = in.readInt();
        message = in.readString();
        shopperId = in.readString();
        shopCarts = new ArrayList<>();
        in.readTypedList(shopCarts, CartItemsShopBundle.CREATOR);
    }
}
