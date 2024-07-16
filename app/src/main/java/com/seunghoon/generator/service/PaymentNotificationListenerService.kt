package com.seunghoon.generator.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class PaymentNotificationListenerService : NotificationListenerService() {

    companion object {
        private val PAYMENT_APP_PACKAGES = listOf(
            "viva.republica.toss",
            "com.kakaobank.channel",
        )
    }
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val packageName = sbn.packageName
        if(PAYMENT_APP_PACKAGES.contains(packageName)) {
            val extras = sbn.notification.extras
            val notificationText = extras.getString(Notification.EXTRA_TITLE)
            Log.d("TEST",notificationText.toString())
        }
    }
}