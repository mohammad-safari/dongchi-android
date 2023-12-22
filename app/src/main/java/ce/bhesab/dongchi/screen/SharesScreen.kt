package ce.bhesab.dongchi.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.component.ParticipantShare
import ce.bhesab.dongchi.component.RadioGroup
import ce.bhesab.dongchi.theme.DongchiTheme
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

class SharesScreen {
    @Composable
    fun Shares(shares: List<SharesScreen.Share>,navController: NavController?) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(R.string.shareScreenTitle),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                item() {
                    SharesListTopRow()
                }
                items(shares) { share ->
                    ParticipantShare(share)
                }
                item() {
                    SharesOverview()

                }
            }
            Spacer(modifier = Modifier.weight(1f))
            SharesActionButton()
        }
    }

    @Composable
    private fun SharesOverview() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {

            DonutPieChart(
                pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice("slice", 3f, Color.Cyan) { "cyan" },
                        PieChartData.Slice("slice", 2f, Color.Red) { "red" },
                        PieChartData.Slice("slice", 1f, Color.Green) { "green" }),
                    plotType = PlotType.Donut
                ),
                pieChartConfig = PieChartConfig(),
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(20.dp)
            )
        }
    }

    @Composable
    private fun SharesListTopRow() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TriStateCheckbox(
                state = ToggleableState.Indeterminate,
                onClick = { /*TODO*/ }, modifier = Modifier
            )
            Text(
                text = stringResource(R.string.selectAll),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "براساس:")
            val kinds = listOf("%", "سهم", "قیمت")
            val (selected, setSelected) = remember { mutableStateOf("%") }
            RadioGroup(
                mItems = kinds,
                selected, setSelected
            )
        }
    }

    @Composable
    private fun SharesActionButton() {
        Row() {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.confirmParticipantsShare))
            }
            OutlinedButton(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.back))
            }
        }
    }


    data class Share(val person: String, val percentage: Float, val color: Color)


    @Preview(showSystemUi = true, locale = "fa")
    @Composable
    fun SharesPreview() {
        DongchiTheme {
            Shares(
                listOf(
                    Share("محمد", 0.5f, Color.Cyan),
                    Share("امیر", 0.3f, Color.Red),
                    Share("حسین", 0f, Color.Yellow),
                    Share("سروناز", 0.2f, Color.Green)
                ),
                null
            )
        }
    }
}