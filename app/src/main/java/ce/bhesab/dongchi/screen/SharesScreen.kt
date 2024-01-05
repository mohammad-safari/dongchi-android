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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import ce.bhesab.dongchi.model.shares.ShareType
import ce.bhesab.dongchi.theme.DongchiTheme
import ce.bhesab.dongchi.viewmodel.SharesSettingViewModel
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import kotlin.math.roundToInt

class SharesScreen {
    private val eventViewModel = SharesSettingViewModel()
    val members =
        listOf(
            "محمد",
            "امیر",
            "حسین",
            "سروناز"
        )

    @Composable
    fun SharesSetting(groupId: String, navController: NavController?) {
        val shares = remember {
            mutableStateOf(
                members.map { Share(it, 100f / members.size, Color(stringToColor(it))) }
            )
        }
        val shareType = remember {
            mutableStateOf(ShareType.BY_PERC)
        }
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
                    SharesListTopRow(shares, shareType)
                }
                items(shares.value) { share ->
                    ParticipantShare(share,
//                        share.person, shares,
                        step = 100,
                        range = 0f..100f,
                        checked = share.percentage > 0,
                        onCheck = {
                            if (share.percentage > 0) {// make unchecked by setting zero
//                                share.percentage = 0f
                                val chosen =
                                    shares.value.filter { it.percentage > 0 }.map { it.person }
                                shares.value = shares.value.map {
                                    if (share.person == it.person && it.percentage > 0 || it.percentage == 0f) it.copy(
                                        percentage = 0f
                                    ) else it.copy(
                                        percentage = (100f / (chosen.size - 1))
                                    )
                                }
                            } else {
                                val chosen =
                                    shares.value.filter { it.percentage > 0 }.map { it.person }
//                                share.percentage = (100f / chosen.size)
                                shares.value = shares.value.map {
                                    if (it.person == share.person || it.percentage > 0) it.copy(
                                        percentage = (100f / (chosen.size + 1))
                                    ) else it.copy(
                                        percentage = 0f
                                    )
                                }
                            }

                        },
                        onSlide = {
//                            share.percentage = it
                            var prev = share.percentage
                            shares.value = shares.value.map {
                                if (share.person == it.person) {
                                    share.copy(percentage = it.percentage)
                                } else it
                            }
                        },
                        onChange = {
//                            share.percentage = it.toFloat()
                            shares.value =
                                shares.value.map {
                                    if (share.person == it.person) share.copy(
                                        percentage = it.percentage
                                    ) else it
                                }
                        })
                }
                item() {
                    SharesOverview(shares)

                }
            }
            Spacer(modifier = Modifier.weight(1f))
            SharesActionButton(navController = navController)
        }

    }

    @Composable
    private fun SharesOverview(shares: MutableState<List<Share>>) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            var slices = shares.value.map { part ->
                PieChartData.Slice(
                    part.person,
                    part.percentage,
                    part.color
                ) { part.person }
            }
            DonutPieChart(
                pieChartData = PieChartData(
                    slices = slices,
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
    private fun SharesListTopRow(
        shares: MutableState<List<Share>>,
        shareType: MutableState<ShareType>
    ) {
        val chosenPeople = shares.value.filter { it.percentage > 0f }.map { it.person }
        var toggleableState = remember {
            mutableStateOf(
                if (chosenPeople.size == members.size
                ) ToggleableState.On else
                    if ((chosenPeople.isNotEmpty())) ToggleableState.Indeterminate
                    else
                        ToggleableState.Off
            )
        }
        LaunchedEffect(shares.value) {
            toggleableState.value =
                if (chosenPeople.size == members.size
                ) ToggleableState.On else
                    if ((chosenPeople.isNotEmpty())) ToggleableState.Indeterminate
                    else
                        ToggleableState.Off
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TriStateCheckbox(
                state = toggleableState.value,
                onClick = {
                    if (toggleableState.value == ToggleableState.On ||
                        toggleableState.value == ToggleableState.Indeterminate
                    ) {
                        toggleableState.value = ToggleableState.Off
                        shares.value = shares.value.map {
                            it.copy(percentage = 0f)
                        }
                    } else {
                        shares.value = shares.value.map {
                            it.copy(percentage = 1f / shares.value.size)
                        }
                    }
                }, modifier = Modifier
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
            Text(text = stringResource(R.string.by))
            val kinds = listOf("%", stringResource(R.string.shares), stringResource(R.string.price))
            val (selected, setSelected) = remember { mutableStateOf("%") }
            RadioGroup(
                mItems = kinds,
                selected, setSelected
            )
        }
    }

    @Composable
    private fun SharesActionButton(navController: NavController?) {
        Row() {
            Button(
                onClick = { navController?.navigate("group") },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.confirmParticipantsShare))
            }
            OutlinedButton(
                onClick = { navController?.navigate("group") },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(R.string.back))
            }
        }
    }


    data class Share(val person: String, var percentage: Float, val color: Color)


    @Preview(showSystemUi = true, locale = "fa")
    @Composable
    fun SharesPreview() {
        DongchiTheme {
            SharesSetting(
                "0",
                null
            )
        }
    }
}

fun stringToColor(str: String, prc: Int = -10): Int {

    // Change the darkness or lightness
    fun shade(color: Int, prc: Int): Int {
        val amt = (2.55 * prc).roundToInt()
        val r = (color shr 16) + amt
        val g = (color shr 8 and 0x00FF) + amt
        val b = (color and 0x0000FF) + amt
        return ((0xFF shl 24) +
                (if (r < 255) if (r < 1) 0 else r else 255) * 0x10000 +
                (if (g < 255) if (g < 1) 0 else g else 255) * 0x100 +
                (if (b < 255) if (b < 1) 0 else b else 255))
    }

    // Convert init to an RGBA
    fun intToRGBA(i: Int): Int {
        return ((i shr 24) and 0xFF) +
                ((i shr 16) and 0xFF) +
                ((i shr 8) and 0xFF) +
                (i and 0xFF)
    }

    return shade(str.hashCode(), prc)
}
