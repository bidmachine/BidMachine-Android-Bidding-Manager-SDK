package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.AdUnitTransformResult
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.NetworkManager
import io.bidmachine.mediation.core.adobject.Reward
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import io.bidmachine.mediation.core.adobject.RewardedAdObjectListener
import io.bidmachine.mediation.core.network.NetworkAdUnit

class RewardedAdBlock :
        FullscreenAdBlock<RewardedAdBlock, RewardedAdBlockListener, RewardedAdObject, RewardedAdObjectListener>() {

    /**
     * Creates list of rewarded ad objects.
     */
    override fun createAdObjectList(contextProvider: ContextProvider,
                                    adUnitList: List<NetworkAdUnit>): List<AdUnitTransformResult<RewardedAdObject>> =
        NetworkManager.createRewardedAdObjectList(contextProvider, adUnitList)

    override fun createAdObjectListener(): RewardedAdObjectListener = RewardedListener()


    private inner class RewardedListener : FullscreenListener(), RewardedAdObjectListener {

        override fun onAdRewarded(adObject: RewardedAdObject, reward: Reward) {
            MediationLogger.log(adObject.tag, "onAdRewarded ($adObject)")

            listener?.onAdRewarded(this@RewardedAdBlock, adObject.adInfo, reward)
        }

    }

}