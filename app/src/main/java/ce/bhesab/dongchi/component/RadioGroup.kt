package ce.bhesab.dongchi.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
    mItems.forEach { item ->
        RadioButton(
            selected = selected == item,
            onClick = {
                setSelected(item)
            },
        )
        Text(text = item)
        Spacer(Modifier.padding(5.dp))
    }
}