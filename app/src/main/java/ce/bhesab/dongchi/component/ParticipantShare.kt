package ce.bhesab.dongchi.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.screen.SharesScreen
import java.math.BigDecimal
import kotlin.math.roundToInt

@Composable
fun ParticipantShare(
//    name: String,
//    shares: MutableState<List<SharesScreen.Share>>,
    share: SharesScreen.Share,
    inputText: MutableState<String> = remember { mutableStateOf("") },
    step: Int = 100,
    range: ClosedFloatingPointRange<Float> = 0f..100f,
    checked: Boolean = true,
    onCheck: (check: Boolean) -> Unit = {},
    onSlide: (Float) -> Unit = {},
    onChange: (share: String) -> Unit = {},
) {
//    val shares by shares
//    val share = shares.first{it.person==name}
    var sliderPosition = remember { mutableFloatStateOf(share.percentage) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked, onCheckedChange = { onCheck(it) },
            colors = CheckboxDefaults.colors(share.color),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = share.person,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.weight(1f)

        )

        Slider(
            value = sliderPosition.value,
            onValueChange = {
                val nit = it.toLong()
                sliderPosition.value = nit.toFloat()
                inputText.value = BigDecimal(nit).toString()
                onSlide(nit.toFloat())
            },
            colors = SliderDefaults.colors(share.color, share.color),
            enabled = checked,
            valueRange = range,
            steps = step,
            onValueChangeFinished = null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(4f)
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                enabled = checked,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .weight(1.5f)
                    .padding(1.dp)
                    .onFocusChanged {
                        if (!it.isFocused)
                            inputText.value = ((share.percentage * 100).roundToInt() /100).toString()
                    },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                imeAction = ImeAction.Done
//            ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onChange(inputText.value)
                    }
                )
            )
        }
    }
}

@Preview
@Composable
fun ParticipantSharePreview() {
    ParticipantShare(/*"test", remember {
        mutableStateOf(
            listOf(
                SharesScreen.Share(
                    "test",
                    12f,
                    Color.Red
                )
            )
        )
    },*/ SharesScreen.Share(
            "test",
        12f,
        Color.Red
    ))
}