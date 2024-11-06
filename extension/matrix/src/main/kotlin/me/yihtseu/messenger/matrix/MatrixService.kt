package me.yihtseu.messenger.matrix

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import me.yihtseu.messenger.matrix.room.RoomNameProvider
import org.matrix.android.sdk.api.MatrixConfiguration

@AndroidEntryPoint
class MatrixService : Service() {
    private val configuration = MatrixConfiguration(
        roomDisplayNameFallbackProvider = RoomNameProvider(this)
    )

    override fun onBind(intent: Intent?): IBinder {
        return Binder()
    }
}