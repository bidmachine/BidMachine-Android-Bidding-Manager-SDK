package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.AdUnitTransformResult
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.NetworkManager
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.BannerAdObjectListener
import io.bidmachine.mediation.core.network.NetworkAdUnit

class BannerAdBlock : ViewAdBlock<BannerAdBlock, BannerAdBlockListener, BannerAdObject, BannerAdObjectListener>() {

    /**
     * Creates list of banner ad objects.
     */
    override fun createAdObjectList(contextProvider: ContextProvider,
                                    adUnitList: List<NetworkAdUnit>): List<AdUnitTransformResult<BannerAdObject>> =
        NetworkManager.createBannerAdObjectList(contextProvider, adUnitList)

    override fun createAdObjectListener(): BannerAdObjectListener = BannerListener()


    private open inner class BannerListener : BaseAdObjectListener(), BannerAdObjectListener {

        override fun onAdExpanded(adObject: BannerAdObject) {
            MediationLogger.log(adObject.tag, "onAdExpanded ($adObject)")

            listener?.onAdExpanded(this@BannerAdBlock, adObject.adInfo)
        }

        override fun onAdCollapsed(adObject: BannerAdObject) {
            MediationLogger.log(adObject.tag, "onAdCollapsed ($adObject)")

            listener?.onAdExpanded(this@BannerAdBlock, adObject.adInfo)
        }

    }

}