package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.AdUnitTransformResult
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.NetworkManager
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObjectListener
import io.bidmachine.mediation.core.network.NetworkAdUnit

class InterstitialAdBlock :
        FullscreenAdBlock<InterstitialAdBlock, InterstitialAdBlockListener, InterstitialAdObject, InterstitialAdObjectListener>() {

    /**
     * Creates list of interstitial ad objects.
     */
    override fun createAdObjectList(contextProvider: ContextProvider,
                                    adUnitList: List<NetworkAdUnit>): List<AdUnitTransformResult<InterstitialAdObject>> =
        NetworkManager.createInterstitialAdObjectList(contextProvider, adUnitList)

    override fun createAdObjectListener(): InterstitialAdObjectListener = InterstitialListener()


    private inner class InterstitialListener : FullscreenListener(), InterstitialAdObjectListener

}