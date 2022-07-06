package io.bidmachine.mediation.core.adobject

import io.bidmachine.mediation.core.ContextProvider

abstract class FullscreenAdObject<ListenerType : Any> : AdObject<ListenerType>() {

    /**
     * Shows loaded ad.
     */
    abstract fun show(contextProvider: ContextProvider, listener: ListenerType)

}