package com.example.ericbrenner.shoppingcartassignment.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ericbrenner on 2/12/16.
 */
public class ResourcesResponse implements Parcelable {
    public int status;
    public ArrayList<Resource> resources;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        Resource[] resArray = resources.toArray(new Resource[resources.size()]);
        dest.writeParcelableArray(resArray, flags);
    }

    public static final Parcelable.Creator<ResourcesResponse> CREATOR
            = new Parcelable.Creator<ResourcesResponse>() {
        public ResourcesResponse createFromParcel(Parcel in) {
            return new ResourcesResponse(in);
        }

        public ResourcesResponse[] newArray(int size) {
            return new ResourcesResponse[size];
        }
    };

    private ResourcesResponse(Parcel in) {
        status = in.readInt();
        Parcelable[] resArray = in.readParcelableArray(Resource.class.getClassLoader());
        resources = new ArrayList<>();
        for (Parcelable p : resArray) {
            resources.add((Resource)p);
        }
    }
}
