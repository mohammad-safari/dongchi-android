package ce.bhesab.dongchi.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ce.bhesab.dongchi.model.Event
import ce.bhesab.dongchi.model.Expense
import ce.bhesab.dongchi.model.Transaction

@Composable
fun EventCard(event: Event){
    Card {
        Row {
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
    ce.bhesab.dongchi.components.EventCard(event = Expense(1, 22.1, "Mohammad", "Salary"))
}
