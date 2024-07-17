package com.seunghoon.generator.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.room.Room
import com.seunghoon.generator.SignielDatabase
import com.seunghoon.generator.dao.PayDao
import com.seunghoon.generator.entity.Pay
import com.seunghoon.generator.entity.PayType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class PaymentNotificationListenerService : NotificationListenerService() {

    companion object {
        private val PAYMENT_APP_PACKAGES = listOf(
            "viva.republica.toss",
            "com.kakaobank.channel",
        )
    }


    private val database: SignielDatabase by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            SignielDatabase::class.java,
            "pay-database"
        ).build()
    }

    private val payDao: PayDao by lazy {
        database.getPayDao()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val packageName = sbn.packageName
        if (PAYMENT_APP_PACKAGES.contains(packageName)) {
            val extras = sbn.notification.extras
            val notificationText = extras.getString(Notification.EXTRA_TITLE)
            val notificationContent = extras.getString(Notification.EXTRA_TEXT)

            if (notificationText != null && notificationContent != null) {
                val type =
                    if (notificationText.split(" ")[0] == PayType.DEPOSIT.value) PayType.DEPOSIT
                    else PayType.WITHDRAWAL

                Log.d("TEST", notificationText)
                Log.d("TEST", notificationContent)

                val amount = notificationText.split(" ")[1].removeSuffix("원").replace(",", "")

                val use =
                    if (notificationText.split(" ")[0] == PayType.DEPOSIT.value) notificationContent.split(
                        "→"
                    )[0].trim()
                    else notificationContent.split("→")[1].trim()

                Log.d("TEST", type.value)
                Log.d("TEST", amount)
                Log.d("TEST", use)

                val current = LocalDateTime.now()

                CoroutineScope(Dispatchers.IO).launch {
                    payDao.savePay(
                        Pay(
                            payType = type,
                            amount = amount.toInt(),
                            use = use,
                            year = current.year.toString(),
                            month = current.monthValue.toString(),
                            day = current.dayOfMonth.toString(),
                        )
                    )
                }
            }
        }
    }
}
