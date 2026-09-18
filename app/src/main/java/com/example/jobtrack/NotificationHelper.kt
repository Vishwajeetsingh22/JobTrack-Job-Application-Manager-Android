package com.example.jobtrack

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHelper {

    private const val CHANNEL_ID =
        "jobtrack_status_channel"

    private const val CHANNEL_NAME =
        "Job Application Updates"

    fun createChannel(context: Context) {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )

            channel.description =
                "Notifications when job application status changes"

            val manager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }

    fun showStatusNotification(
        context: Context,
        companyName: String,
        status: String
    ) {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU
        ) {

            if (
                context.checkSelfPermission(
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
        }

        val notification =
            NotificationCompat.Builder(
                context,
                CHANNEL_ID
            )
                .setSmallIcon(
                    R.drawable.ic_notification
                )
                .setContentTitle(
                    "Job Application Updated"
                )
                .setContentText(
                    "$companyName application is now $status"
                )
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )
                .setAutoCancel(true)
                .build()

        try {
            NotificationManagerCompat
                .from(context)
                .notify(
                    System.currentTimeMillis().toInt(),
                    notification
                )
        } catch (e: SecurityException) {
            // Permission missing
        }
    }
}