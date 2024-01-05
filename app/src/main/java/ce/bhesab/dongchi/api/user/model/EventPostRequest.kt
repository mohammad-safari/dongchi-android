package ce.bhesab.dongchi.api.user.model

import java.math.BigDecimal

class EventPostRequest(
    val creditorUsername: String,
    totalAmount: BigDecimal,
    getEventType: EventType,
    participantsUserNameShareMap: Map<String, BigDecimal>
) {
    val totalAmount: BigDecimal
    val getEventType: EventType
    val participantsUserNameShareMap: Map<String, BigDecimal>

    init {
        this.totalAmount = totalAmount
        this.getEventType = getEventType
        this.participantsUserNameShareMap = participantsUserNameShareMap
    }
}