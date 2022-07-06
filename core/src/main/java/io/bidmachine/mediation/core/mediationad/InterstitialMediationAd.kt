package io.bidmachine.mediation.core.mediationad

import android.content.Context
import io.bidmachine.mediation.core.adblock.InterstitialAdBlock
import io.bidmachine.mediation.core.adblock.InterstitialAdBlockListener
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObjectListener

class InterstitialMediationAd(context: Context) :
        FullscreenMediationAd<InterstitialMediationAd, InterstitialMediationAdListener, InterstitialAdBlock, InterstitialAdObject, InterstitialAdObjectListener>(context) {

    override fun createPreBidAdBlock(): InterstitialAdBlock = InterstitialAdBlock().apply {
        listener = PreBidAdBlockListener()
    }

    override fun createPostBidAdBlock(): InterstitialAdBlock = InterstitialAdBlock().apply {
        listener = PostBidAdBlockListener()
    }


    private inner class PreBidAdBlockListener : FullscreenPreBidAdBlockListener(), InterstitialAdBlockListener

    private inner class PostBidAdBlockListener : FullscreenPostBidAdBlockListener(), InterstitialAdBlockListener

}