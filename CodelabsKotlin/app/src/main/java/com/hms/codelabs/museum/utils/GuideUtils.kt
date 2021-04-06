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

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.hms.codelabs.museum.SharedViewModel
import com.hms.codelabs.museum.models.Constant
import com.hms.codelabs.museum.models.Exhibit
import com.huawei.hms.common.ApiException
import com.huawei.hms.nearby.Nearby
import com.huawei.hms.nearby.StatusCode
import com.huawei.hms.nearby.discovery.Distance
import com.huawei.hms.nearby.message.*

class GuideUtils(val viewModel: SharedViewModel) {
    private var messageEngine: MessageEngine? = null
    private var mMessageHandler: MessageHandler? = null

    /**
     * Start Scanning Beacons nearby
     * Message engine is defined and registered here
     * 3 function of the message handler are used
     * OnFound OnDistanceChanged OnLost
     */
    fun startScanning(activity: Activity) {

        //TODO : Set Message Engine and Set Message Handler


        //TODO: Set some properties and registration task of the message engine

    }

    /**
     * Do on lost function. It removes the beacon and its artifact's information
     */
    private fun doOnLost(message: Message?) {
        //TODO: Remove unnecessary exhibit information when the beacon signal is lost

    }

    /**
     * Disable searching nearby beacons
     */
    fun ungetMessageEngine() {
        if (messageEngine != null && mMessageHandler != null) {
            Log.i("Beacon", "unget")
            messageEngine!!.unget(mMessageHandler)
        }
    }

    /**
     * doOnFound downloads the beacon's artifact's information
     */
    private fun doOnFound(message: Message?) {
        // TODO: Get Exhibit Information when a beacon discovered

    }

    /**
     * Download Artifact Information
     */
    private fun downloadArtifact(messageContent: String) {
        val exhibit = Constant.exhibitInfo[messageContent.toInt()]
        downloadedExhibits[messageContent.toInt()] = exhibit
    }

    /**
     * doOnDistanceChanged, calculates every artifact's distance to the user. If the user close enough to closest beacon, UI updates and shows the related artifact info
     */
    private fun doOnDistanceChanged(message: Message?, distance: Distance) {
        //TODO : Handle On Distance Changed Message

    }

    /**
     * extension of doOnDistanceChanged
     */
    private fun operateOnDistanceChanged(messageContent: String, distance: Distance) {
        //TODO : Find the closest exhibit, compare it to threshold and update UI

    }

    /**
     * Updates Virtual Guide UI
     */
    private fun updateUI(closestInfo: Exhibit?) {
        closestInfo?.let {
            viewModel.currentExhibit.postValue(it)
            viewModel.currentMuseumName.postValue(it.museumName)
        }

    }

    /**
     * find the artifact in the downloaded artifacts
     */
    private fun findExhibitInformation(id: Int): Exhibit? {
        return downloadedExhibits[id]
    }

    /**
     * find the closes artifact in the list
     */
    private fun findClosest(): Map.Entry<Int, Distance>? {
        //TODO : Find closest Exhibit

    }

    companion object {
        /**
         * Downloaded artifact List
         * artifact distances to the user list
         */
        val downloadedExhibits = HashMap<Int, Exhibit>()
        val exhibitDistances = HashMap<Int, Distance>()
    }
}