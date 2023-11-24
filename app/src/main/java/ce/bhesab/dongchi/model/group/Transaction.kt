package ce.bhesab.dongchi.model.group

import ce.bhesab.dongchi.model.group.Event

data class Transaction(
    override val id: Int,
    override val amount : Double,
    override val from: String,
    val to: String
) : Event(id, amount, from)
