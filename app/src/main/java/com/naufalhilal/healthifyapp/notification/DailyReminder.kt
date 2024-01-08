package com.naufalhilal.healthifyapp.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.Calendar

class DailyReminder : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        executeThread {

        }
    }

    fun setDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                ID_REPEATING,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val breakFast = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val launch = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 13)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val dinner = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        if (System.currentTimeMillis() > breakFast.timeInMillis) {
            breakFast.add(Calendar.DAY_OF_YEAR, 1)
        } else if (System.currentTimeMillis() > launch.timeInMillis) {
            launch.add(Calendar.DAY_OF_YEAR, 1)
        } else if (System.currentTimeMillis() > dinner.timeInMillis) {
            dinner.add(Calendar.DAY_OF_YEAR, 1)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            breakFast.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            launch.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            dinner.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

}