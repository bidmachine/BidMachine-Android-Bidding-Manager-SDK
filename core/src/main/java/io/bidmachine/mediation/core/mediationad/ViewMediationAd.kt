package io.bidmachine.mediation.core.mediationad

import android.content.Context
import android.view.View
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.adblock.BaseAdBlock
import io.bidmachine.mediation.core.adobject.AdObjectListener
import io.bidmachine.mediation.core.adobject.ViewAdObject

abstract class ViewMediationAd<
        SelfType : BaseMediationAd<SelfType, MediationAdListenerType, AdBlockType, AdObjectType>,
        MediationAdListenerType : MediationAdListener<SelfType>,
        AdBlockType : BaseAdBlock<AdBlockType, *, AdObjectType, AdObjectListenerType>,
        AdObjectType : ViewAdObject<AdObjectListenerType>,
        AdObjectListenerType : AdObjectListener<AdObjectType>>(context: Context) :
        BaseMediationAd<SelfType, MediationAdListenerType, AdBlockType, AdObjectType>(context) {

    /**
     * Gets ad object with highest price.
     */
    fun getView(): View? {
        return getMostExpensiveAd()?.let {
            MediationLogger.log(tag, "getView ($it)")

            it.getView()
        }
    }

}