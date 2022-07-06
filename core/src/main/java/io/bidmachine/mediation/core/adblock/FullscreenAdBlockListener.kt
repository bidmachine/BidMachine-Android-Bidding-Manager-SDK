package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.AdObject

interface FullscreenAdBlockListener<AdObjectType : AdObject<*>, AdBlockType : AdBlock<AdBlockType, *, AdObjectType>> :
        AdBlockListener<AdObjectType, AdBlockType> {

    fun onAdClosed(adBlock: AdBlockType, adInfo: AdInfo)

}