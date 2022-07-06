package io.bidmachine.mediation.adapter.bidmachine

import io.bidmachine.BidMachine
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.MediationError
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import io.bidmachine.mediation.core.network.NetworkAdapter
import io.bidmachine.mediation.core.network.NetworkInitializeListener

class BidMachineNetworkAdapter(private val sellerId: String? = null) :
        NetworkAdapter<BidMachineNetworkAdUnit>(KEY,
                                                BuildConfig.ADAPTER_NETWORK_VERSION_NAME,
                                                BuildConfig.ADAPTER_VERSION_NAME) {

    companion object {
        const val KEY = "BidMachine"
    }

    override fun isNetworkInitialized(contextProvider: ContextProvider): Boolean = BidMachine.isInitialized()

    override fun initializeNetwork(contextProvider: ContextProvider, listener: NetworkInitializeListener) {
        if (sellerId.isNullOrEmpty()) {
            listener.onFailToInitialize(MediationError.invalidParameter("SellerId is null or empty"))
            return
        }
        BidMachine.initialize(contextProvider.context, sellerId) {
            listener.onInitialized()
        }
    }

    override fun createBannerAdObject(networkAdUnit: BidMachineNetworkAdUnit): BannerAdObject =
        BidMachineBannerAdObject(networkAdUnit)

    override fun createInterstitialAdObject(networkAdUnit: BidMachineNetworkAdUnit): InterstitialAdObject =
        BidMachineInterstitialAdObject(networkAdUnit)

    override fun createRewardedAdObject(networkAdUnit: BidMachineNetworkAdUnit): RewardedAdObject =
        BidMachineRewardedAdObject(networkAdUnit)

}