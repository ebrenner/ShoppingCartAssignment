package com.example.ericbrenner.shoppingcartassignment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ericbrenner.shoppingcartassignment.Constants;
import com.example.ericbrenner.shoppingcartassignment.R;
import com.example.ericbrenner.shoppingcartassignment.pojos.ShopItemImage;

/**
 * Created by ericbrenner on 2/15/16.
 */
public class ImagePageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_image_page, container, false);

        Bundle bundle = getArguments();
        ShopItemImage shopItemImage = bundle.getParcelable(Constants.KEY_SHOP_ITEM_IMAGE);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.image_page);
        Glide.with(getContext()).load(shopItemImage.location).into(imageView);
        return rootView;
    }
}
