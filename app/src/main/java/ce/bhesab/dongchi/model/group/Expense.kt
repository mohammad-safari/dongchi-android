package ce.bhesab.dongchi.model.group


data class Expense(
    override val amount : Double,
    override val from: String,
    val goal: String
) : Event(amount, from)
