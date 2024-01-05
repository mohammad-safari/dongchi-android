package ce.bhesab.dongchi.model.group

data class Transaction(
    override val amount : Double,
    override val from: String,
    val to: String
) : Event(amount, from)
