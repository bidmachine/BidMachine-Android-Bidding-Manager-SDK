package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdObject

interface AdBlockLoadListener<AdObjectType : AdObject<*>, AdBlockType : AdBlock<AdBlockType, *, AdObjectType>> {

    fun onAdBlockLoaded(adBlock: AdBlockType)

    fun onAdBlockFailToLoad(adBlock: AdBlockType)

}