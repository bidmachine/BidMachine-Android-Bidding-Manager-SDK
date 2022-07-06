package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.adobject.FullscreenAdObject
import io.bidmachine.mediation.core.adobject.FullscreenAdObjectListener

abstract class FullscreenAdBlock<
        SelfType : FullscreenAdBlock<SelfType, AdBlockListenerType, AdObjectType, AdObjectListenerType>,
        AdBlockListenerType : FullscreenAdBlockListener<AdObjectType, SelfType>,
        AdObjectType : FullscreenAdObject<AdObjectListenerType>,
        AdObjectListenerType : FullscreenAdObjectListener<AdObjectType>> :
        BaseAdBlock<SelfType, AdBlockListenerType, AdObjectType, AdObjectListenerType>() {

    protected open inner class FullscreenListener : BaseAdObjectListener(), FullscreenAdObjectListener<AdObjectType> {

        override fun onAdClosed(adObject: AdObjectType) {
            MediationLogger.log(adObject.tag, "onAdClosed ($adObject)")

            listener?.onAdClosed(this@FullscreenAdBlock as SelfType, adObject.adInfo)
        }

    }

}