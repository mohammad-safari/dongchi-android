package ce.bhesab.dongchi.model

data class Transaction(
    override val id: Int,
    override val amount : Double,
    override val from: String,
    val to: String
) : Event(id, amount, from)
