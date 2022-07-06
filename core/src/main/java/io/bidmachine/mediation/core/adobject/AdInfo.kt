package io.bidmachine.mediation.core.adobject

import io.bidmachine.mediation.core.network.NetworkAdUnit

data class AdInfo(val networkAdUnit: NetworkAdUnit,
                  val adObjectParameters: AdObjectParameters,
                  var price: Double?) {

    override fun toString(): String {
        return "${networkAdUnit.networkKey}, price - $price"
    }

}