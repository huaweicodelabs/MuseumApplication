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
package com.hms.codelabs.museum.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import com.huawei.hms.kit.awareness.barrier.BarrierStatus

/**
 * Creates an IntentService.  Invoked by the subclass's constructor.
 */
class AwarenessServiceManager : IntentService("AwarenessService") {
    override fun onHandleIntent(intent: Intent?) {
        //TODO: Check Barrier Status and Send Notification

    }

    /**
     * Create notification channel for Awareness Barriers
     */
    override fun onCreate() {
        //TODO: Create Notification Channel
        super.onCreate()

    }
}