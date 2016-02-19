package com.example.ericbrenner.shoppingcartassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ericbrenner.shoppingcartassignment.R;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartItem;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartItemsShopBundle;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartResponse;
import com.example.ericbrenner.shoppingcartassignment.pojos.Resource;
import com.example.ericbrenner.shoppingcartassignment.pojos.ResourcesResponse;
import com.example.ericbrenner.shoppingcartassignment.pojos.Shop;
import com.example.ericbrenner.shoppingcartassignment.pojos.ShopItem;
import com.example.ericbrenner.shoppingcartassignment.pojos.ShopOrShopItemResponse;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by ericbrenner on 2/13/16.
 */
public class CartItemsAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private CartResponse mCartResponse;
    private ResourcesResponse mResourcesResponse;

    private HashMap<String, Shop> mShops = new HashMap<>();
    private HashMap<String, ShopItem> mShopItems = new HashMap<>();

    public CartItemsAdapter(Context context, CartResponse cartResponse, ResourcesResponse resourcesResponse) {
        mContext = context;
        mCartResponse = cartResponse;
        mResourcesResponse = resourcesResponse;

        for (Resource resource : mResourcesResponse.resources) {
            ShopOrShopItemResponse response = resource.response;
            if (response.shop != null) {
                mShops.put(response.shop.shopId, response.shop);
            }
            if (response.shopItem != null) {
                mShopItems.put(response.shopItem.itemId, response.shopItem);
            }
        }
    }

    @Override
    public int getGroupCount() {
        return mCartResponse.shopCarts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartResponse.shopCarts.get(groupPosition).items.size();
    }

    @Override
    public CartItemsShopBundle getGroup(int groupPosition) {
        return mCartResponse.shopCarts.get(groupPosition);
    }

    @Override
    public CartItem getChild(int groupPosition, int childPosition) {
        return mCartResponse.shopCarts.get(groupPosition).items.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CartItemsShopBundle itemsBundle = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.shop_group, null);
        }

        Shop shop = mShops.get(itemsBundle.shopId);
        TextView shopName = (TextView) convertView.findViewById(R.id.shop_group_name);
        shopName.setText(shop.name.value);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CartItem cartItem = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.cart_item, null);
        }

        ShopItem shopItem = mShopItems.get(cartItem.itemId);
        TextView itemName = (TextView) convertView.findViewById(R.id.cart_item_name);
        itemName.setText(shopItem.name);
        TextView itemQuantity = (TextView)convertView.findViewById(R.id.cart_item_quantity);
        itemQuantity.setText(Integer.toString(cartItem.quantities));
        TextView itemPrice = (TextView)convertView.findViewById(R.id.cart_item_price);
        itemPrice.setText(cartItem.price + " " + cartItem.currency);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public ResourcesResponse getmResourcesResponse() {
        return mResourcesResponse;
    }
}
