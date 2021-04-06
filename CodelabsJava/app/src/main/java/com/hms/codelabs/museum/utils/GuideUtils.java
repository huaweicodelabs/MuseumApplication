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

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.hms.codelabs.museum.SharedViewModel;
import com.hms.codelabs.museum.models.Constant;
import com.hms.codelabs.museum.models.Exhibit;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.nearby.Nearby;
import com.huawei.hms.nearby.StatusCode;
import com.huawei.hms.nearby.discovery.Distance;
import com.huawei.hms.nearby.message.GetOption;
import com.huawei.hms.nearby.message.Message;
import com.huawei.hms.nearby.message.MessageEngine;
import com.huawei.hms.nearby.message.MessageHandler;
import com.huawei.hms.nearby.message.MessagePicker;
import com.huawei.hms.nearby.message.Policy;
import com.huawei.hms.nearby.message.StatusCallback;

import java.util.HashMap;
import java.util.Map;

public class GuideUtils {

    final SharedViewModel viewModel;

    private MessageEngine messageEngine;
    private MessageHandler mMessageHandler;

    /**
     * Downloaded artifact List
     * artifact distances to the user list
     */
    public static final HashMap<Integer, Exhibit> downloadedExhibits = new HashMap<>();
    public static final HashMap<Integer, Distance> exhibitDistances = new HashMap<>();

    public GuideUtils(SharedViewModel viewModel) {
        this.viewModel = viewModel;
    }


    /**
     * Start Scanning Beacons nearby
     * Message engine is defined and registered here
     * 3 function of the message handler are used
     * OnFound OnDistanceChanged OnLost
     */
    public void startScanning(Activity activity) {
        //TODO : Set Message Engine and Set Message Handler


        //TODO: Set some properties and registration task of the message engine

    }

    /**
     * Do on lost function. It removes the beacon and its artifact's information
     */
    private void doOnLost(Message message) {
        //TODO: Remove unnecessary exhibit information when the beacon signal is lost

    }

    /**
     * Disable searching nearby beacons
     */
    public void ungetMessageEngine() {
        if (messageEngine != null && mMessageHandler != null) {
            Log.i("Beacon", "unget");
            messageEngine.unget(mMessageHandler);
        }
    }

    /**
     * doOnFound downloads the beacon's artifact's information
     */
    private void doOnFound(Message message) {
        // TODO: Get Exhibit Information when a beacon discovered

    }

    /**
     * Download Artifact Information
     */
    private void downloadArtifact(String messageContent) {
        Exhibit exhibit = Constant.exhibitInfo.get(Integer.parseInt(messageContent));
        downloadedExhibits.put(Integer.parseInt(messageContent),exhibit);
    }

    /**
     * doOnDistanceChanged, calculates every artifact's distance to the user. If the user close enough to closest beacon, UI updates and shows the related artifact info
     */
    private void doOnDistanceChanged(Message message, Distance distance) {
        //TODO : Handle On Distance Changed Message

    }

    /**
     * extension of doOnDistanceChanged
     */
    private void operateOnDistanceChanged(String messageContent, Distance distance) {
        //TODO : Find the closest exhibit, compare it to threshold and update UI

    }

    /**
     * Updates Virtual Guide UI
     */
    private void updateUI(Exhibit closestInfo) {
        if (closestInfo != null) {
            viewModel.currentExhibit.postValue(closestInfo);
            viewModel.currentMuseumName.postValue(closestInfo.getMuseumName());
        }
    }


    /**
     * find the artifact in the downloaded artifacts
     */
    private Exhibit findExhibitInformation(int id) {
        return downloadedExhibits.get(id);
    }

    /**
     * find the closes artifact in the list
     */
    private Map.Entry<Integer, Distance> findClosest() {
        //TODO : Find closest Exhibit

    }

}
