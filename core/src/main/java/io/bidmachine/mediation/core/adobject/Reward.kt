package io.bidmachine.mediation.core.adobject

data class Reward(val currency: String, val value: Int) {

    constructor() : this("", 0)

}