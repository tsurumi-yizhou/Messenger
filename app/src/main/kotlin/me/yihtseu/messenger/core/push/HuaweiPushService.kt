package me.yihtseu.messenger.core.push

import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HuaweiPushService : HmsMessageService() {

    @Inject
    lateinit var helper: ConversationHelper

    override fun onCreate() {
        helper.createDefaultChannel()
    }

    override fun onNewToken(token: String) {
        Log.d("HuaweiMessageService", "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                helper.createNotification(
                    message.from,
                    message.notification.title,
                    message.notification.body
                )
            }
        } catch (e: Exception) {
            Log.e("HuaweiMessageService", "onMessageReceived: ${e.message}", e.cause)
        }
    }
}