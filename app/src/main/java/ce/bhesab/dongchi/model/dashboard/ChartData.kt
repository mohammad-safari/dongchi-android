package ce.bhesab.dongchi.model.dashboard

data class ChartData(
    val title: String,
    val type: String,
    val values: List<Any>,
    val descr: String,
)