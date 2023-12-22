package ce.bhesab.dongchi.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Transaction

@Composable
fun EventList(eventList: List<Event>){
    LazyColumn{
        items(eventList) {event ->
            EventCard(event = event)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventListPreview(){
    val eventList = listOf<Event>(Transaction(3, 22.2, "Sarvenaz", "Mohammad"),
                                  Expense(4, 30.0, "Amirhossein", "Cake"))
    EventList(eventList = eventList)
}