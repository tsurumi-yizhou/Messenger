package me.yihtseu.messenger.core.hilt

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.huawei.hms.feature.dynamicinstall.FeatureCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        FeatureCompat.install(base)
        SplitCompat.install(this)
    }
}