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

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.hms.codelabs.museum.R;
import com.hms.codelabs.museum.SharedViewModel;
import com.hms.codelabs.museum.listeners.MuseumSearchResultListener;
import com.hms.codelabs.museum.services.AwarenessServiceManager;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import com.huawei.hms.kit.awareness.barrier.LocationBarrier;
import com.huawei.hms.kit.awareness.barrier.TimeBarrier;
import com.huawei.hms.site.api.SearchResultListener;
import com.huawei.hms.site.api.SearchService;
import com.huawei.hms.site.api.SearchServiceFactory;
import com.huawei.hms.site.api.model.Coordinate;
import com.huawei.hms.site.api.model.LocationType;
import com.huawei.hms.site.api.model.NearbySearchRequest;
import com.huawei.hms.site.api.model.NearbySearchResponse;
import com.huawei.hms.site.api.model.SearchStatus;
import com.huawei.hms.site.api.model.Site;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class SearchUtils {
    final Context context;
    final String TAG = "Search Utils";
    final SharedViewModel viewModel;

    public SearchUtils(Context context, SharedViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    /**
     * Search Nearby Museums
     * Site Kit - Nearby Search works with pagination logic
     * This function checks for every page of the result(maximum 20 pages)
     * Results of the every page are retrieved async
     * Therefore if the result count 20, search will be assumed finished
     */
    public void searchMuseums(Location location, int radius, int pageIndex, MuseumSearchResultListener museumSearchResultListener) throws UnsupportedEncodingException {
        // TODO : Set nearby search properties


        // TODO : Set a result listener which handles the results

        // TODO : Start Search Service

    }

    /**
     * For every museum nearby, Awareness barriers are added in order to notify the user when he is close
     */
    public void addBarrierToAwarenessKit(Site site, double radius, long duration) {
        //TODO : Add Location Barrier to the Site Location, combine it with time barrier

    }

    /**
     * Update the barriers
     */
    private void updateBarrier(String label, AwarenessBarrier barrier, PendingIntent pendingIntent) {
        //TODO : Update Barrier

    }
}
