package ce.bhesab.dongchi.api.group.model

import java.math.BigDecimal

class GroupEventRetrievalResponse(
    val creditorUsername: String,
    val totalAmount: BigDecimal,
    val type: String,
    val participants: List<String>,
    val shares: Map<String, String>
) {
}
