package io.bidmachine.mediation.core.mediationad

import android.content.Context
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adblock.BannerAdBlock
import io.bidmachine.mediation.core.adblock.BannerAdBlockListener
import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.BannerAdObjectListener

class BannerMediationAd(context: Context) :
        ViewMediationAd<BannerMediationAd, BannerMediationAdListener, BannerAdBlock, BannerAdObject, BannerAdObjectListener>(context) {

    override fun createPreBidAdBlock(): BannerAdBlock = BannerAdBlock().apply {
        listener = BannerPreBidAdBlockListener()
    }

    override fun createPostBidAdBlock(): BannerAdBlock = BannerAdBlock().apply {
        listener = BannerPostBidAdBlockListener()
    }

    private fun onAdExpanded(adBlock: BannerAdBlock, adInfo: AdInfo) {
        MediationLogger.log(adBlock.tag, "onAdExpanded ($adInfo)")

        Utils.onUiThread {
            listener?.onAdExpanded(this, adInfo)
        }
    }

    private fun onAdCollapsed(adBlock: BannerAdBlock, adInfo: AdInfo) {
        MediationLogger.log(adBlock.tag, "onAdCollapsed ($adInfo)")

        Utils.onUiThread {
            listener?.onAdCollapsed(this, adInfo)
        }
    }


    private inner class BannerPreBidAdBlockListener : PreBidAdBlockListener(), BannerAdBlockListener {

        override fun onAdExpanded(adBlock: BannerAdBlock, adInfo: AdInfo) {
            this@BannerMediationAd.onAdExpanded(adBlock, adInfo)
        }

        override fun onAdCollapsed(adBlock: BannerAdBlock, adInfo: AdInfo) {
            this@BannerMediationAd.onAdCollapsed(adBlock, adInfo)
        }

    }

    private inner class BannerPostBidAdBlockListener : PostBidAdBlockListener(), BannerAdBlockListener {

        override fun onAdExpanded(adBlock: BannerAdBlock, adInfo: AdInfo) {
            this@BannerMediationAd.onAdExpanded(adBlock, adInfo)
        }

        override fun onAdCollapsed(adBlock: BannerAdBlock, adInfo: AdInfo) {
            this@BannerMediationAd.onAdCollapsed(adBlock, adInfo)
        }

    }

}