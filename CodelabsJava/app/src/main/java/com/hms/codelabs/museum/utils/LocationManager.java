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
package com.hms.codelabs.museum.utils;

import android.content.Context;

import com.hms.codelabs.museum.listeners.LocationResultListener;
import com.huawei.hms.location.FusedLocationProviderClient;

import java.io.UnsupportedEncodingException;

public class LocationManager {

    final Context mContext;

    public LocationManager(Context context) {
        this.mContext = context;
    }

    /**
     * Get Last Location
     * @param locationResultListener
     */
    public void getCurrentLocation(LocationResultListener locationResultListener){

        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(mContext);

        fusedLocationProviderClient.getLastLocation()
                // Define callback for success in obtaining the last known location.
                .addOnSuccessListener(location -> {
                    if (location == null)
                        locationResultListener.onFailure(new Exception("Location result is null"));
                    else {
                        try {
                            locationResultListener.onResult(location);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    // Define logic for processing the Location object upon success.
                    // ...
                })
                // Define callback for failure in obtaining the last known location.
                .addOnFailureListener(locationResultListener::onFailure);
    }
}
