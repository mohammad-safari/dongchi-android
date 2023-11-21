package ce.bhesab.dongchi.model

data class Expense(
    override val id: Int,
    override val amount : Double,
    override val from: String,
    val goal: String
) : Event(id, amount, from)
