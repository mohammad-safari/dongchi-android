package ce.bhesab.dongchi.screen

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.component.BottomNavigationBar
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.model.dashboard.ChartData
import ce.bhesab.dongchi.model.dashboard.Overview
import ce.bhesab.dongchi.theme.DongchiTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import kotlin.math.abs

class DashboardScreen {
    val mockOverview
        @Composable get() = Overview(
            totalBalance = -1000,
            totalDebtToGroups = Pair(3000, 3),
            totalCredToGroups = Pair(2000, 3),
            currency = "﷼",
            charts = listOf(
                ChartData(
                    title = stringResource(R.string.overviewTitle),
                    descr = stringResource(R.string.overviewTitle),
                    values = listOf(
                        Pair(stringResource(R.string.debt), 3000),
                        Pair(stringResource(R.string.cred), 2000)
                    ),
                    type = "bar"
                )
            ),
            events = listOf()
        )

    @Composable
    fun Dashboard(
        modifier: Modifier = Modifier, navController: NavController?, overview: Overview
    ) {
//        Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
//                Icon(Icons.Filled.Add, contentDescription = "Add")
//            }
//        }
//        ) { it ->
        Box {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(10.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = stringResource(R.string.overviewTitle),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                Statistics(overview)
                Recents()


            }

            PlusButtonInsert(
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 80.dp),
                null
            ) {

            }

            BottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                navController = navController
            )
        }

    }


    @Composable
    private fun Statistics(overview: Overview) {
        val chartData = overview.charts[0]
        val (barDataList, xAxisData) = prepareDataAndAxis(chartData)
        val barChartData = BarChartData(
            chartData = barDataList,
            xAxisData = xAxisData,
            backgroundColor = MaterialTheme.colorScheme.surface,
            barStyle = BarStyle(
                isGradientEnabled = true,
                selectionHighlightData = null,
                paddingBetweenBars = 20.dp,
                barWidth = 50.dp
            )
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            BarChart(
                barChartData = barChartData,
                modifier = Modifier
                    .height(250.dp)
                    .width(200.dp)
            )
        }
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(
                        R.string.debtBrief,
                        overview.totalDebtToGroups.first,
                        overview.currency,
                        overview.totalDebtToGroups.second
                    ),
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = stringResource(
                        R.string.credBrief,
                        overview.totalCredToGroups.first,
                        overview.currency,
                        overview.totalCredToGroups.second
                    ),
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = stringResource(
                        R.string.balanceBrief,
                        abs(overview.totalBalance),
                        overview.currency,
                        stringResource(if (overview.totalBalance > 0) R.string.cred else R.string.debt)
                    ),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }

    @Composable
    private fun Recents() {
        Text(
            text = stringResource(R.string.recent),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
            }
        }
    }

    @Composable
    private fun prepareDataAndAxis(chartData: ChartData): Pair<List<BarData>, AxisData> {
        val barDataList = listOf(
            BarData(
                label = chartData.values[0].first,
                // todo check consistency in cast
                point = Point(1f, chartData.values[0].second.toFloat()),
                gradientColorList = listOf(Color.Red, Color.Transparent),
                description = chartData.values[0].first
            ), BarData(
                label = chartData.values[1].first,
                point = Point(2f, chartData.values[1].second.toFloat()),
                gradientColorList = listOf(Color.Green, Color.Transparent),
                description = chartData.values[1].first
            )
        )
        val xAxisData = AxisData.Builder().axisStepSize(50.dp).steps(chartData.values.size - 1)
            .topPadding(20.dp).bottomPadding(40.dp).startDrawPadding(60.dp)
            .labelData { index -> barDataList[index].label }.shouldDrawAxisLineTillEnd(true)
            .typeFace(
                ResourcesCompat.getFont(LocalContext.current, R.font.vazir_thin) ?: Typeface.DEFAULT
            )
            .axisLabelColor(MaterialTheme.colorScheme.onSurface)
            .axisLineColor(MaterialTheme.colorScheme.onSurface).build()
        return Pair(barDataList, xAxisData)
    }

    @Preview(showSystemUi = true, locale = "fa")
    @Composable
    fun DashboardPreview() {
        DongchiTheme {
            Dashboard(overview = mockOverview, navController = null)
        }
    }
}