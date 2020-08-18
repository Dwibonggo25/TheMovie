package com.example.jetpack.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.jetpack.CHANNEL_ID_GENERAL
import com.example.jetpack.CHANNEL_ID_PRIORITY
import com.example.jetpack.MainActivity
import com.example.jetpack.R

object NotificationHelper {

    fun displayOnGeneralChannel(context: Context, title: String, body: String, paramsMap: Map<String, String>) {

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        for (i in paramsMap.keys) {
            val value = paramsMap[i]
            intent.putExtra(i, value)
          //  Timber.v("Key : $i. Value : $value")
        }

        val pendingIntent =
            PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID_GENERAL)
            .setSmallIcon(R.drawable.ic_chat)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(body)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent) //Set the intent that will fire when the user taps the notification
            .setAutoCancel(true) //automatically removes the notification when the user taps it.
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) //Android uses a some pre-defined system-wide categories to determine whether to disturb the user with a given notification when the user has enabled Do Not Disturb mode.
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, builder.build())

    }

    fun displayOnPriorityChannel(context: Context, title: String, body: String) {

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent =
            PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID_PRIORITY)
            .setSmallIcon(R.drawable.ic_chat)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(body)
            )
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) //Set the intent that will fire when the user taps the notification
            .setAutoCancel(true) //automatically removes the notification when the user taps it.
            .setCategory(NotificationCompat.CATEGORY_REMINDER) //Android uses a some pre-defined system-wide categories to determine whether to disturb the user with a given notification when the user has enabled Do Not Disturb mode.
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE) //To control the level of detail visible in the notification from the lock screen
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2, builder.build())
    }
}