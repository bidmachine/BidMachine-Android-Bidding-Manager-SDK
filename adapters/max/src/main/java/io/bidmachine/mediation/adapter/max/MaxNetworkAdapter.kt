package io.bidmachine.mediation.adapter.max

import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import io.bidmachine.mediation.core.network.NetworkAdapter
import io.bidmachine.mediation.core.network.NetworkInitializeListener

class MaxNetworkAdapter : NetworkAdapter<MaxNetworkAdUnit>(KEY,
                                                           BuildConfig.ADAPTER_NETWORK_VERSION_NAME,
                                                           BuildConfig.ADAPTER_VERSION_NAME) {

    companion object {
        const val KEY = "Max"
    }

    override fun isNetworkInitialized(contextProvider: ContextProvider): Boolean =
        AppLovinSdk.getInstance(contextProvider.context).isInitialized

    override fun initializeNetwork(contextProvider: ContextProvider, listener: NetworkInitializeListener) {
        AppLovinSdk.getInstance(contextProvider.context).apply {
            mediationProvider = AppLovinMediationProvider.MAX
        }.initializeSdk {
            listener.onInitialized()
        }
    }

    override fun createBannerAdObject(networkAdUnit: MaxNetworkAdUnit): BannerAdObject =
        MaxBannerAdObject(networkAdUnit)

    override fun createInterstitialAdObject(networkAdUnit: MaxNetworkAdUnit): InterstitialAdObject =
        MaxInterstitialAdObject(networkAdUnit)

    override fun createRewardedAdObject(networkAdUnit: MaxNetworkAdUnit): RewardedAdObject =
        MaxRewardedAdObject(networkAdUnit)

}