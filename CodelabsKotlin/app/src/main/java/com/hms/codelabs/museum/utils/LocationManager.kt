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
package com.hms.codelabs.museum.utils

import android.content.Context
import android.location.Location
import com.hms.codelabs.museum.listeners.ResultListener
import com.huawei.hms.location.FusedLocationProviderClient
import java.io.UnsupportedEncodingException

class LocationManager(private val mContext: Context) {
    /**
     * Get Last Location
     * @param resultListener
     */
    fun getCurrentLocation(resultListener: ResultListener) {
        //TODO : Get last known location
        val fusedLocationProviderClient = FusedLocationProviderClient(mContext)
        fusedLocationProviderClient.lastLocation // Define callback for success in obtaining the last known location.
                .addOnSuccessListener { location: Location? ->
                    if (location == null)
                        resultListener.onFailure(Exception("Location result is null"))
                    else {
                        try {
                            resultListener.onResult(location)
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }
                    }
                } // Define callback for failure in obtaining the last known location.
                .addOnFailureListener { e: Exception? -> resultListener.onFailure(e) }
    }
}