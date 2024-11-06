package me.yihtseu.messenger.core.push

import android.annotation.SuppressLint
import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.LocusId
import android.graphics.drawable.Icon
import androidx.core.content.LocusIdCompat
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import me.yihtseu.messenger.data.room.RoomDao
import me.yihtseu.messenger.view.BubbleActivity
import me.yihtseu.messenger.view.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val roomDao: RoomDao
) {
    fun createDefaultChannel() {
        val channel = NotificationChannel(
            DEFAULT_MESSAGE_CHANNEL, "Messages", NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    fun createChannel(id: String, shortcutId: String, name: String, description: String) {
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT)
        channel.setAllowBubbles(true)
        channel.setConversationId(DEFAULT_MESSAGE_CHANNEL, shortcutId)
        channel.description = description
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    suspend fun createNotification(from: String, title: String, body: String) {
        val room = roomDao.queryById(from) ?: return
        createChannel(room.notificationId, room.shortcutId, room.name, room.nickname)
        val target = Intent(context, BubbleActivity::class.java)
        val shortcut = ShortcutInfoCompat.Builder(context, room.shortcutId)
            .setLocusId(LocusIdCompat.toLocusIdCompat(LocusId(room.id)))
            .setActivity(ComponentName(context, MainActivity::class.java))
            .setIcon(IconCompat.createWithContentUri(room.icon))
            .setShortLabel(room.nickname)
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
        val bubbleIntent = PendingIntent.getActivity(context, 0, target, PendingIntent.FLAG_IMMUTABLE)
        val bubbleMeta = Notification.BubbleMetadata.Builder(room.shortcutId)
            .setDesiredHeight(600)
            .setIntent(bubbleIntent)
            .setAutoExpandBubble(true)
            .setSuppressNotification(true)
            .build()
        val person = Person.Builder()
            .setName(room.nickname)
            .setIcon(Icon.createWithAdaptiveBitmapContentUri(room.icon))
            .setImportant(true)
            .build()
        val notification = Notification.Builder(context, room.notificationId)
            .setContentIntent(bubbleIntent)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(Icon.createWithAdaptiveBitmapContentUri(room.icon))
            .setBubbleMetadata(bubbleMeta)
            .addPerson(person)
            .build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }

    companion object {
        const val DEFAULT_MESSAGE_CHANNEL = "default_message_channel"
    }
}