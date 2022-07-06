package io.bidmachine.mediation.core.mediationad

import android.content.Context
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adblock.BaseAdBlock
import io.bidmachine.mediation.core.adblock.FullscreenAdBlockListener
import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.FullscreenAdObject
import io.bidmachine.mediation.core.adobject.FullscreenAdObjectListener
import io.bidmachine.mediation.core.adobject.MediationError

abstract class FullscreenMediationAd<
        SelfType : FullscreenMediationAd<SelfType, MediationAdListenerType, AdBlockType, AdObjectType, AdObjectListenerType>,
        MediationAdListenerType : FullscreenMediationAdListener<SelfType>,
        AdBlockType : BaseAdBlock<AdBlockType, *, AdObjectType, AdObjectListenerType>,
        AdObjectType : FullscreenAdObject<AdObjectListenerType>,
        AdObjectListenerType : FullscreenAdObjectListener<AdObjectType>>(context: Context) :
        BaseMediationAd<SelfType, MediationAdListenerType, AdBlockType, AdObjectType>(context) {

    /**
     * Shows ad object with highest price.
     */
    fun showAd() {
        getMostExpensiveAd()?.also {
            MediationLogger.log(tag, "showAd ($it)")

            it.show(contextProvider, it.listener)
        } ?: listener?.onAdFailToShow(this as SelfType, MediationError.invalidState("Nothing to show"))
    }

    private fun onAdClosed(adBlock: AdBlockType, adInfo: AdInfo) {
        MediationLogger.log(adBlock.tag, "onAdClosed ($adInfo)")

        Utils.onUiThread {
            listener?.onAdClosed(this as SelfType, adInfo)
        }
    }


    protected open inner class FullscreenPreBidAdBlockListener : PreBidAdBlockListener(),
            FullscreenAdBlockListener<AdObjectType, AdBlockType> {

        override fun onAdClosed(adBlock: AdBlockType, adInfo: AdInfo) {
            this@FullscreenMediationAd.onAdClosed(adBlock, adInfo)
        }

    }

    protected open inner class FullscreenPostBidAdBlockListener : PostBidAdBlockListener(),
            FullscreenAdBlockListener<AdObjectType, AdBlockType> {

        override fun onAdClosed(adBlock: AdBlockType, adInfo: AdInfo) {
            this@FullscreenMediationAd.onAdClosed(adBlock, adInfo)
        }

    }

}