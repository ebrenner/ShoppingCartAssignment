package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class ShopOrShopItemResponse implements Parcelable {
    public int status;
    public ShopItem shopItem;
    public Shop shop;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeParcelable(shopItem, flags);
        dest.writeParcelable(shop, flags);
    }

    public static final Parcelable.Creator<ShopOrShopItemResponse> CREATOR
            = new Parcelable.Creator<ShopOrShopItemResponse>() {
        public ShopOrShopItemResponse createFromParcel(Parcel in) {
            return new ShopOrShopItemResponse(in);
        }

        public ShopOrShopItemResponse[] newArray(int size) {
            return new ShopOrShopItemResponse[size];
        }
    };

    private ShopOrShopItemResponse(Parcel in) {
        status = in.readInt();
        shopItem = in.readParcelable(ShopItem.class.getClassLoader());
        shop = in.readParcelable(Shop.class.getClassLoader());
    }
}
