package com.example.ericbrenner.shoppingcartassignment.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ericbrenner.shoppingcartassignment.Constants;
import com.example.ericbrenner.shoppingcartassignment.fragments.ImagePageFragment;
import com.example.ericbrenner.shoppingcartassignment.R;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartItem;
import com.example.ericbrenner.shoppingcartassignment.pojos.Resource;
import com.example.ericbrenner.shoppingcartassignment.pojos.ResourcesResponse;
import com.example.ericbrenner.shoppingcartassignment.pojos.ShopItem;
import com.example.ericbrenner.shoppingcartassignment.pojos.ShopItemImage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ericbrenner on 2/14/16.
 */
public class ItemActivity extends AppCompatActivity {

    CartItem mCartItem;
    ResourcesResponse mResourcesResponse;
    HashMap<String, ShopItem> mShopItemLookUp = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Bundle bundle = getIntent().getExtras();
        mCartItem = bundle.getParcelable(Constants.KEY_CART_ITEM);
        mResourcesResponse = bundle.getParcelable(Constants.KEY_RESOURCES_RESPONSE);

        createShopItemLookUp();
        setTextContent();

        setUpImagePager();
    }

    private void createShopItemLookUp() {
        for (Resource resource : mResourcesResponse.resources) {
            if (resource.response.shopItem != null) {
                mShopItemLookUp.put(resource.response.shopItem.itemId, resource.response.shopItem);
            }
        }
    }

    private void setTextContent() {
        TextView name = (TextView)findViewById(R.id.expanded_item_name);
        name.setText(mShopItemLookUp.get(mCartItem.itemId).name);
        TextView quantity = (TextView)findViewById(R.id.expanded_item_quantity);
        quantity.setText(Integer.toString(mCartItem.quantities));
        TextView price = (TextView)findViewById(R.id.expanded_item_price);
        price.setText(mCartItem.price + " " + mCartItem.currency);
    }

    private void setUpImagePager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(), mShopItemLookUp.get(mCartItem.itemId).images);
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<ShopItemImage> mImages;

        public ImagePagerAdapter(FragmentManager fragmentManager, ArrayList<ShopItemImage> images) {
            super(fragmentManager);
            mImages = images;
        }

        @Override
        public Fragment getItem(int position) {
            ImagePageFragment fragment = new ImagePageFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.KEY_SHOP_ITEM_IMAGE, mImages.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }
    }
}
