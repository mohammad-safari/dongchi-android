package ce.bhesab.dongchi.model.dashboard

data class Overview(
    val totalBalance: Long,
    val totalDebt: Long,
    val totalCred: Long,
    val currency: String,
    val charts: List<ChartData>,
    val events: List<Event>,
)