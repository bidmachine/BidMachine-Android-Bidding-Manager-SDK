package io.bidmachine.mediation.adapter.admob

import com.google.android.gms.ads.MobileAds
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import io.bidmachine.mediation.core.network.NetworkAdapter
import io.bidmachine.mediation.core.network.NetworkInitializeListener

class AdMobNetworkAdapter : NetworkAdapter<AdMobNetworkAdUnit>(KEY,
                                                               BuildConfig.ADAPTER_NETWORK_VERSION_NAME,
                                                               BuildConfig.ADAPTER_VERSION_NAME) {

    companion object {
        const val KEY = "AdMob"
    }

    override fun initializeNetwork(contextProvider: ContextProvider, listener: NetworkInitializeListener) {
        Utils.onUiThread {
            MobileAds.initialize(contextProvider.context) {
                listener.onInitialized()
            }
        }
    }

    override fun createBannerAdObject(networkAdUnit: AdMobNetworkAdUnit): BannerAdObject =
        AdMobBannerAdObject(networkAdUnit)

    override fun createInterstitialAdObject(networkAdUnit: AdMobNetworkAdUnit): InterstitialAdObject =
        AdMobInterstitialAdObject(networkAdUnit)

    override fun createRewardedAdObject(networkAdUnit: AdMobNetworkAdUnit): RewardedAdObject =
        AdMobRewardedAdObject(networkAdUnit)

}