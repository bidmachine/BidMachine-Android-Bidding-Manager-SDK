package io.bidmachine.mediation.core.mediationad

import android.content.Context
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adblock.RewardedAdBlock
import io.bidmachine.mediation.core.adblock.RewardedAdBlockListener
import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.Reward
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import io.bidmachine.mediation.core.adobject.RewardedAdObjectListener

class RewardedMediationAd(context: Context) :
        FullscreenMediationAd<RewardedMediationAd, RewardedMediationAdListener, RewardedAdBlock, RewardedAdObject, RewardedAdObjectListener>(
            context) {

    override fun createPreBidAdBlock(): RewardedAdBlock = RewardedAdBlock().apply {
        listener = PreBidAdBlockListener()
    }

    override fun createPostBidAdBlock(): RewardedAdBlock = RewardedAdBlock().apply {
        listener = PostBidAdBlockListener()
    }

    private fun onAdRewarded(adBlock: RewardedAdBlock, adInfo: AdInfo, reward: Reward) {
        MediationLogger.log(adBlock.tag, "onAdRewarded ($adInfo)")

        Utils.onUiThread {
            listener?.onAdRewarded(this, adInfo, reward)
        }
    }


    private inner class PreBidAdBlockListener : FullscreenPreBidAdBlockListener(), RewardedAdBlockListener {

        override fun onAdRewarded(adBlock: RewardedAdBlock, adInfo: AdInfo, reward: Reward) {
            this@RewardedMediationAd.onAdRewarded(adBlock, adInfo, reward)
        }

    }

    private inner class PostBidAdBlockListener : FullscreenPostBidAdBlockListener(), RewardedAdBlockListener {

        override fun onAdRewarded(adBlock: RewardedAdBlock, adInfo: AdInfo, reward: Reward) {
            this@RewardedMediationAd.onAdRewarded(adBlock, adInfo, reward)
        }

    }

}