package me.yihtseu.messenger.core.push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirebasePushService : FirebaseMessagingService() {

    @Inject
    lateinit var helper: ConversationHelper

    override fun onCreate() {
        helper.createDefaultChannel()
    }

    override fun onNewToken(token: String) {
        Log.d("FirebaseMessageService", "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                helper.createNotification(
                    message.from!!,
                    message.notification?.title!!,
                    message.notification?.body!!
                )
            }
        } catch (e: Exception) {
            Log.e("FirebaseMessageService", "onMessageReceived: ${e.message}", e.cause)
        }
    }
}