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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.screen.SharesScreen

@Composable
fun ParticipantShare(share: SharesScreen.Share, modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableStateOf(share.percentage) }
    var inputText by remember { mutableStateOf((share.percentage * 100).toInt().toString()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = share.percentage > 0, onCheckedChange = { /*TODO*/ },
            colors = CheckboxDefaults.colors(share.color),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = share.person,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.weight(1f)

        )

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(share.color, share.color),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(4f)
        )

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f)
                .padding(1.dp)
        )
    }
}

@Preview
@Composable
fun ParticipantSharePreview() {
    ParticipantShare(share = SharesScreen.Share("test", 0.12f, Color.Red))
}