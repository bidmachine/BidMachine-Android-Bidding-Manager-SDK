package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.AdObject
import io.bidmachine.mediation.core.adobject.MediationError

interface AdBlockListener<AdObjectType : AdObject<*>, AdBlockType : AdBlock<AdBlockType, *, AdObjectType>> :
        AdBlockLoadListener<AdObjectType, AdBlockType> {

    fun onAdShown(adBlock: AdBlockType, adInfo: AdInfo)

    fun onAdFailToShow(adBlock: AdBlockType, adInfo: AdInfo, adError: MediationError)

    fun onAdClicked(adBlock: AdBlockType, adInfo: AdInfo)

    fun onAdExpired(adBlock: AdBlockType, adInfo: AdInfo)

}