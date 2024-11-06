package me.yihtseu.messenger.core.telecom

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.telecom.CallsManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallService : Service() {

    private val callsManager = CallsManager(this)

    override fun onCreate() {
        val capabilities = CallsManager.CAPABILITY_BASELINE or
                CallsManager.CAPABILITY_SUPPORTS_CALL_STREAMING or
                CallsManager.CAPABILITY_SUPPORTS_VIDEO_CALLING
        callsManager.registerAppWithTelecom(capabilities)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        TODO()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO()
    }
}