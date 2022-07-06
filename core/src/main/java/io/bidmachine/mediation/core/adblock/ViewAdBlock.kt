package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdObjectListener
import io.bidmachine.mediation.core.adobject.ViewAdObject

abstract class ViewAdBlock<
        SelfType : BaseAdBlock<SelfType, AdBlockListenerType, AdObjectType, AdObjectListenerType>,
        AdBlockListenerType : AdBlockListener<AdObjectType, SelfType>,
        AdObjectType : ViewAdObject<AdObjectListenerType>,
        AdObjectListenerType : AdObjectListener<AdObjectType>> :
        BaseAdBlock<SelfType, AdBlockListenerType, AdObjectType, AdObjectListenerType>()