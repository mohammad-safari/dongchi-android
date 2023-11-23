package ce.bhesab.dongchi.model.dashboard

data class Overview(
    val totalBalance: Long,
    val totalDebtToGroups: Pair<Long, Int>,
    val totalCredToGroups: Pair<Long, Int>,
    val currency: String,
    val charts: List<ChartData>,
    val events: List<Event>,
)