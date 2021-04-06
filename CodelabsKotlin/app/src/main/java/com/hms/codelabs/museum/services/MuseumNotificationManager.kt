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

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hms.codelabs.museum.R

object MuseumNotificationManager {
    const val CHANNEL_ID = "museumCodelabs"

    /**
     * Creation of the notification
     * Sends notification
     */
    fun sendNotification(context: Context, content: String?, label: String?) {
        val notificationLayout = RemoteViews(context.packageName, R.layout.view_notification)
        notificationLayout.setTextViewText(R.id.notification_content, content)
        val notificationLayoutExpand = RemoteViews(context.packageName, R.layout.view_notification_expand)
        notificationLayoutExpand.setTextViewText(R.id.notification_content, content)
        notificationLayoutExpand.setTextViewText(R.id.notification_museum_name, label)
        notificationLayoutExpand.setTextViewText(R.id.notification_museum_description, "It is a nice day to visit this museum!")
        createNotificationChannel(context)
        val extra = Bundle()
        extra.putString("MuseumName", label)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpand)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setAutoCancel(true)
        NotificationManagerCompat.from(context).notify(0, builder.build())
    }

    /**
     * Creates Notification Channel
     */
    private fun createNotificationChannel(context: Context) {
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(NotificationManager::class.java)
                    ?: return
            val name: CharSequence = "Museum Info"
            val description = "Preferential information of surrounding museums"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel.description = description
            manager.createNotificationChannel(channel)
        }
    }
}