package ce.bhesab.dongchi.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.screen.SharesScreen
import java.math.BigDecimal

@Composable
fun ParticipantShare(
    share: SharesScreen.Share,
    inputText: MutableState<String> = remember { mutableStateOf("") },
    step: Int = 100,
    range: ClosedFloatingPointRange<Float> = 0f..100f,
    checked: Boolean = true,
    onCheck: (check: Boolean) -> Unit = {},
    onSlide: (Float) -> Unit = {},
    onChange: (share: String) -> Unit = {},
) {
    var sliderPosition = remember { mutableFloatStateOf(share.percentage) }

    LaunchedEffect(sliderPosition.value) {
        inputText.value = BigDecimal(share.percentage.toDouble()).toString()
    }
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
                sliderPosition.value = it
                onSlide(it)
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
                    if (it.isFocused)
                        inputText.value = share.percentage.toString()
                    else
                        onChange(inputText.value)
                }
        )
    }
}

@Preview
@Composable
fun ParticipantSharePreview() {
    ParticipantShare(SharesScreen.Share("test", 12f, Color.Red))
}