package com.motoappcleancodemvc.presentation.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.motoappcleancodemvc.R
import com.motoappcleancodemvc.presentation.route_generator.route_generator_presentation.activities.RouteGeneratorActivity

class NotificationHelper(context: Context) {
    private val CHANNEL_ID = context.getString(R.string.channel_id)
    private val NOTIFICATION_TITLE = context.getString(R.string.notification_title)
    private val NOTIFICATION_CONTENT_TEXT = context.getString(R.string.notification_content)
    private val NOTIFICATION_CHANNEL_NAME = context.getString(R.string.notification_channel_name)
    private val NOTIFICATION_CHANNEL_DESCRIPTION = context.getString(R.string.notification_channel_description)
    private val mMapActivityIntent = Intent(context, RouteGeneratorActivity::class.java)
    private val mPendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, mMapActivityIntent, 0)
    private val mContext = context

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = NOTIFICATION_CHANNEL_NAME
            val descriptionText = NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText

            val notificationManager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    fun createNotification(): Notification? {
        createNotificationChannel()
        return NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_sports_motorsports_24)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(NOTIFICATION_CONTENT_TEXT)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(mPendingIntent)
            .setAutoCancel(true)
            .build()
    }


}