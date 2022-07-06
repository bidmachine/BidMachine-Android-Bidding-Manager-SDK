package io.bidmachine.mediation.core

import io.bidmachine.mediation.core.adobject.AdObject

interface AdHolder<AdObjectType : AdObject<*>> {

    fun getLoadedAdObjectList(): List<AdObjectType>

    fun getLoadedAdObjectSize(): Int = getLoadedAdObjectList().size

    fun getMostExpensiveAd(): AdObjectType?

}