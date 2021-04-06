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
package com.hms.codelabs.museum.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.hms.codelabs.museum.R;

public class MuseumNotificationManager {

    static final String CHANNEL_ID = "museumCodelabs";

    /**
     * Creation of the notification
     * Sends notification
     */
    public static void sendNotification(Context context, String content, String label) {
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.view_notification);
        notificationLayout.setTextViewText(R.id.notification_content, content);
        RemoteViews notificationLayoutExpand = new RemoteViews(context.getPackageName(), R.layout.view_notification_expand);
        notificationLayoutExpand.setTextViewText(R.id.notification_content, content);
        notificationLayoutExpand.setTextViewText(R.id.notification_museum_name, label);
        notificationLayoutExpand.setTextViewText(R.id.notification_museum_description, "It is a nice day to visit this museum!");

        MuseumNotificationManager.createNotificationChannel(context);
        Bundle extra = new Bundle();
        extra.putString("MuseumName", label);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpand)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setAutoCancel(true);
        NotificationManagerCompat.from(context).notify(0, builder.build());
    }

    /**
     * Creates Notification Channel
     */
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if(manager==null)
                return;

            CharSequence name =  "Museum Info";
            String description = "Preferential information of surrounding museums";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setDescription(description);
            manager.createNotificationChannel(channel);
        }
    }
}
