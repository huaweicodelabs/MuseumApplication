/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hms.codelabs.museum;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hms.codelabs.museum.listeners.LocationResultListener;
import com.hms.codelabs.museum.listeners.MuseumSearchResultListener;
import com.hms.codelabs.museum.models.Constant;
import com.hms.codelabs.museum.models.Exhibit;
import com.hms.codelabs.museum.utils.GuideUtils;
import com.hms.codelabs.museum.utils.LocationManager;
import com.hms.codelabs.museum.utils.SearchUtils;
import com.hms.codelabs.museum.utils.TTSUtils;
import com.huawei.hms.site.api.model.Site;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressLint("StaticFieldLeak")
public class SharedViewModel extends AndroidViewModel {

    private final TTSUtils ttsUtils;
    private final Context mContext;

    public final SearchUtils searchUtils;
    public final GuideUtils guideUtils;

    public final MutableLiveData<Integer> page = new MutableLiveData<>(1);
    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public final MutableLiveData<ArrayList<Site>> museumList = new MutableLiveData<>(new ArrayList<>());

    public final MutableLiveData<String> currentMuseumName = new MutableLiveData<>("");
    public final MutableLiveData<Exhibit> currentExhibit = new MutableLiveData<>(null);
    public final MutableLiveData<Integer> searchRange = new MutableLiveData<>(Constant.DEFAULT_SEARCH_RANGE);


    /**
     * Image binding to ImageView
     * @param view
     * @param currentExhibit
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("app:image")
    public static void setImage(ImageView view, Exhibit currentExhibit){
        if(currentExhibit == null)
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.no_image));
        else
            view.setImageDrawable(view.getContext().getDrawable(currentExhibit.getExhibitImage()));
    }


    public SharedViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
        ttsUtils = new TTSUtils(mContext.getString(R.string.api_key));
        searchUtils = new SearchUtils( mContext , this);
        guideUtils = new GuideUtils(this);
    }
    /**
     * Text to Speech Start Reading
     */
    public void startTTS(View view) {
        if (currentExhibit.getValue() != null)
            ttsUtils.startTTS(currentExhibit.getValue().getExhibitDescription());
    }

    /**
     * Text to Speech Stop Reading
     */
    public void stopTTS(View view) {
        ttsUtils.stopTTS();
    }



    /**
     * Search Nearby Museums
     * Site Kit - Nearby Search works with pagination logic
     * This function checks for every page of the result(maximum 20 pages)
     * Results of the every page are retrieved async
     * Therefore if the result count 20, search will be assumed finished
     */
    public void searchNearbyMuseums(){
        LocationManager locationManager = new LocationManager(mContext);
        locationManager.getCurrentLocation(new LocationResultListener() {
            @Override
            public void onResult(Location result) throws UnsupportedEncodingException {
                searchUtils.searchMuseums(result, searchRange.getValue(), page.getValue(), new MuseumSearchResultListener() {
                    @Override
                    public void onResult(List<Site> result) {
                        Objects.requireNonNull(museumList.getValue()).addAll(result);
                        museumList.getValue().sort((site1, site2) -> (int) (site1.getDistance() - site2.getDistance()));
                        museumList.postValue(museumList.getValue());
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("Museum Search", "Error: " + errorMessage);
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("Location Result", "Error:" + e.getMessage());
            }
        });
    }
}
