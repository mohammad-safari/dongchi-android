package ce.bhesab.dongchi.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

class SharesScreen {
    @Composable
    fun Shares() {
        DonutPieChart(
            pieChartData = PieChartData(
                slices = listOf(PieChartData.Slice("slice", 3f, Color.Cyan) { "cyan" },
                    PieChartData.Slice("slice", 1f, Color.Red) { "red" }), PlotType.Donut
            ), pieChartConfig = PieChartConfig(), modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        ) { slice -> }
    }

    @Preview
    @Composable
    fun SharesPreview() {
        Shares()
    }
}