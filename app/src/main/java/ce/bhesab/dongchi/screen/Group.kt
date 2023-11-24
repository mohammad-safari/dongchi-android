package ce.bhesab.dongchi.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.components.BalanceList
import ce.bhesab.dongchi.components.EventList
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Group
import ce.bhesab.dongchi.model.group.GroupScreenState
import ce.bhesab.dongchi.model.group.Transaction

@Composable
fun GroupScreen(group: Group) {
    var state: GroupScreenState = GroupScreenState.EVENT

    Column (modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()) {
        Row (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround){
            Button(onClick = {state = GroupScreenState.BALANCE}) {
                Text(text = "Balances")
            }
            Button(onClick = {state = GroupScreenState.EVENT}) {
                Text(text = "Events")
            }
        }
        if (state == GroupScreenState.EVENT){
            EventList(eventList = group.eventList)
        }
        else if (state == GroupScreenState.BALANCE){
            BalanceList(balanceList = group.balanceList)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GroupScreenPreview(){
    val eventList = listOf<Event>(
        Transaction(3, 22.2, "Sarvenaz", "Mohammad"),
        Expense(4, 30.0, "Amirhossein", "Cake")
    )
    val balanceList = listOf<Balance>(
        Balance("Sarvenaz", mapOf("Amirhossein" to 30.0)),
        Balance("Mohammad", mapOf("Amirhossein" to 20.0))
    )
    val group = Group(eventList, balanceList)
    GroupScreen(group = group)
}