package com.seunghoon.generator.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.room.Room
import com.seunghoon.core.network.RequestHandler
import com.seunghoon.core.network.gptClient
import com.seunghoon.generator.SignielDatabase
import com.seunghoon.generator.dao.PayDao
import com.seunghoon.generator.entity.GptRequest
import com.seunghoon.generator.entity.GptResponse
import com.seunghoon.generator.entity.Pay
import com.seunghoon.generator.entity.PayCategory
import com.seunghoon.generator.entity.PayType
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class PaymentNotificationListenerService : NotificationListenerService() {

    companion object {
        private val PAYMENT_APP_PACKAGES = listOf(
            "com.kakaobank.channel",
        )
    }


    private val database: SignielDatabase by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            SignielDatabase::class.java,
            "pay-database"
        ).fallbackToDestructiveMigration().build()
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

                val amount = notificationText.split(" ")[1].removeSuffix("원").replace(",", "")

                val use =
                    if (notificationText.split(" ")[0] == PayType.DEPOSIT.value) notificationContent.split(
                        "→"
                    )[0].trim()
                    else notificationContent.split("→")[1].trim()

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
                            category = "sijfisje",
                        )
                    )
                }

                /*CoroutineScope(Dispatchers.IO).launch {
                    runCatching {
                        RequestHandler<GptResponse>().request {
                            gptClient.post {
                                url("/prompt")
                                setBody(
                                    GptRequest(
                                        prompt = use
                                    )
                                )
                            }.body<GptResponse>()
                        }
                    }.onSuccess {

                    }.onFailure {
                        Log.d("TEST2", it.toString())
                    }
                }*/
            }
        }
    }
}
