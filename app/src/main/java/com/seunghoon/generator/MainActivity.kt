package com.seunghoon.generator

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.room.Room
import com.seunghoon.designsystem.ui.theme.ProjectGeneratorTheme
import com.seunghoon.generator.navigation.ProjectGeneratorApp
import com.seunghoon.generator.service.PaymentNotificationListenerService

class MainActivity : ComponentActivity() {

    private lateinit var database: SignielDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database =
            Room.databaseBuilder(applicationContext, SignielDatabase::class.java, "pay-database")
                .fallbackToDestructiveMigration()
                .build()
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            if (!isNotificationPermissionGranted()) {
                val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                startActivity(intent)
            }
            ProjectGeneratorTheme {
                ProjectGeneratorApp()
            }
        }
    }

    private fun isNotificationPermissionGranted(): Boolean {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return notificationManager.isNotificationListenerAccessGranted(
            ComponentName(
                application,
                PaymentNotificationListenerService::class.java
            )
        )
    }
}
