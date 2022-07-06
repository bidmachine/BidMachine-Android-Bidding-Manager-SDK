package io.bidmachine.mediation.core.adobject

import android.view.View

abstract class ViewAdObject<ListenerType : Any> : AdObject<ListenerType>() {

    /**
     * Gets loaded ad view.
     */
    abstract fun getView(): View?

}