package io.bidmachine.mediation.core.adblock

data class AdBlockParameters(val priceFloor: Double?) {

    override fun toString(): String {
        return "priceFloor - $priceFloor"
    }

}