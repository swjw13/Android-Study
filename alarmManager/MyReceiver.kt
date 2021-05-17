package com.jw.alarmmanagettest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        var builder: NotificationCompat.Builder? = null

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel("default") == null) {
                manager.createNotificationChannel(
                    NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT
                    ))
            }
            builder = NotificationCompat.Builder(context.applicationContext, "default")
        } else {
            builder = NotificationCompat.Builder(context.applicationContext)
        }
        val ine = Intent(context.applicationContext, MainActivity::class.java)

        builder.setContentIntent(
            PendingIntent.getActivity(
                context.applicationContext,
                0,
                ine,
                0
            )
        )
        builder.setContentTitle("Text")
        builder.setContentText("2분 마다 반복")
        builder.setSmallIcon(android.R.drawable.ic_menu_view)
        builder.setAutoCancel(true)
        val noti = builder.build()

        manager.notify(1, noti)
    }
}
