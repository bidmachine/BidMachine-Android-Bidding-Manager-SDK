package io.bidmachine.mediation.core.adblock

import android.content.Context
import io.bidmachine.mediation.core.AdHolder
import io.bidmachine.mediation.core.adobject.AdObject
import io.bidmachine.mediation.core.network.NetworkAdUnit

interface AdBlock<
        AdBlockType : AdBlock<AdBlockType, AdBlockListenerType, AdObjectType>,
        AdBlockListenerType,
        AdObjectType : AdObject<*>> :
        AdHolder<AdObjectType> {

    var listener: AdBlockListenerType?
    var timeOutMs: Long

    fun load(context: Context, adBlockParameters: AdBlockParameters, networkAdUnitList: List<NetworkAdUnit>)

    fun canShowAd(): Boolean = getMostExpensiveAd() != null

    fun destroy()

}