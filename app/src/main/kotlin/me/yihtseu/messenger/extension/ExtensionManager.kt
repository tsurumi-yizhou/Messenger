package me.yihtseu.messenger.extension

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.huawei.hms.api.HuaweiApiAvailability
import com.huawei.hms.feature.install.FeatureInstallManagerFactory
import com.huawei.hms.feature.model.FeatureInstallRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExtensionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gms = GoogleApiAvailability.getInstance()
    private val hms = HuaweiApiAvailability.getInstance()
    private val splitInstallManager = SplitInstallManagerFactory.create(context)
    private val featureInstallManager = FeatureInstallManagerFactory.create(context)

    fun installExtension(splitName: String) {
        if (gms.isGooglePlayServicesAvailable(context) == 0) {
            val request = SplitInstallRequest.newBuilder()
                .addModule(splitName)
                .build()
            splitInstallManager.startInstall(request)
        }

        if (hms.isHuaweiMobileServicesAvailable(context) == 0) {
            val request = FeatureInstallRequest.newBuilder()
                .addModule(splitName)
                .build()
            featureInstallManager.installFeature(request)
        }
    }

    fun deleteExtension(splitName: String) {
        if (gms.isGooglePlayServicesAvailable(context) == 0) {
            splitInstallManager.deferredUninstall(listOf(splitName))
        }

        if (hms.isHuaweiMobileServicesAvailable(context) == 0) {
            featureInstallManager.delayedUninstallFeature(listOf(splitName))
        }
    }
}