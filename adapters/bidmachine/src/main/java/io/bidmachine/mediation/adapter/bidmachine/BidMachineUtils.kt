package io.bidmachine.mediation.adapter.bidmachine

import io.bidmachine.PriceFloorParams
import io.bidmachine.mediation.core.adobject.MediationError
import io.bidmachine.models.RequestBuilder
import io.bidmachine.utils.BMError

internal object BidMachineUtils {

    fun Double.toPriceFloorParams(): PriceFloorParams? = PriceFloorParams().addPriceFloor(this)

    fun BMError.toMediationAdError(): MediationError = MediationError(code, message)

    fun <T : RequestBuilder<*, *>> fillAdRequest(requestBuilder: T, networkAdUnit: BidMachineNetworkAdUnit): T {
        requestBuilder.setTargetingParams(networkAdUnit.getTargetingParams())
        networkAdUnit.getNetworks()?.also {
            requestBuilder.setNetworks(it)
        }
        requestBuilder.setCustomParams(networkAdUnit.getCustomParams())
        requestBuilder.setPlacementId(networkAdUnit.getPlacementId())
        return requestBuilder
    }

}