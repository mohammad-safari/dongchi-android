package ce.bhesab.dongchi.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Transaction

@Composable
fun EventCard(event: Event){
    Card(modifier = Modifier.padding(20.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            if (event is Expense){
                Text(text = event.goal)
                Text(text = event.from)
                Text(text = event.amount.toString())
            }
            else if (event is Transaction){
                Text(text = event.from)
                Text(text = event.to)
                Text(text = event.amount.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventPreview() {
    ce.bhesab.dongchi.component.EventCard(event = Transaction(22.1, "Mohammad", "Amirhossein"))
}
