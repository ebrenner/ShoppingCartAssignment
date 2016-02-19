package com.example.ericbrenner.shoppingcartassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.ericbrenner.shoppingcartassignment.Constants;
import com.example.ericbrenner.shoppingcartassignment.R;
import com.example.ericbrenner.shoppingcartassignment.activities.ItemActivity;
import com.example.ericbrenner.shoppingcartassignment.adapters.CartItemsAdapter;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartItem;
import com.example.ericbrenner.shoppingcartassignment.pojos.CartResponse;
import com.example.ericbrenner.shoppingcartassignment.pojos.ResourcesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {

    private static final String CART_RESPONSE_FILE_NAME = "cart_data.json";
    private static final String RESOURCES_RESPONSE_FILE_NAME = "item_data.json";

    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpCartItemsAdapter();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private String getResponse(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setUpCartItemsAdapter() {
        String cartResponseString = getResponse(CART_RESPONSE_FILE_NAME);
        CartResponse cartResponse = gson.fromJson(cartResponseString, CartResponse.class);

        String resourcesResponseString = getResponse(RESOURCES_RESPONSE_FILE_NAME);
        ResourcesResponse resourcesResponse = gson.fromJson(resourcesResponseString, ResourcesResponse.class);

        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandable_list_view);
        expandableListView.setAdapter(new CartItemsAdapter(this, cartResponse, resourcesResponse));
        expandableListView.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        CartItemsAdapter cartItemsAdapter = (CartItemsAdapter)parent.getExpandableListAdapter();
        CartItem cartItem = cartItemsAdapter.getChild(groupPosition, childPosition);
        ResourcesResponse resourcesResponse = cartItemsAdapter.getmResourcesResponse();
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(Constants.KEY_CART_ITEM, cartItem);
        intent.putExtra(Constants.KEY_RESOURCES_RESPONSE, resourcesResponse);
        startActivity(intent);
        return false;
    }
}
