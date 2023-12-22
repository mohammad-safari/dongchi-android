package ce.bhesab.dongchi.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.component.BalanceList
import ce.bhesab.dongchi.component.BottomNavigationBar
import ce.bhesab.dongchi.component.EventList
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Group
import ce.bhesab.dongchi.model.group.GroupScreenState
import ce.bhesab.dongchi.model.group.Transaction
import ce.bhesab.dongchi.theme.DongchiTheme

@Composable
fun GroupScreen(group: Group, navController: NavController?, modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(GroupScreenState.EVENT)
    }

//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
//                Icon(Icons.Filled.Add, contentDescription = "Add")
//            }
//        }
//    ) { it ->
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { state = GroupScreenState.BALANCE }) {
                    Text(text = stringResource(id = R.string.balances))
                }
                Button(onClick = { state = GroupScreenState.EVENT }) {
                    Text(text = stringResource(id = R.string.events))
                }
            }
            if (state == GroupScreenState.EVENT) {
                EventList(eventList = group.eventList)
            } else if (state == GroupScreenState.BALANCE) {
                BalanceList(balanceList = group.balanceList)
            }
        }

        PlusButtonInsert(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp),
            navController
        ) {
            //onClick code
        }

        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            navController = navController
        )
    }


}

@Preview(showBackground = true, showSystemUi = true, locale = "fa")
@Composable
fun GroupScreenPreview() {
    val eventList = listOf<Event>(
        Transaction(3, 22.2, "Sarvenaz", "Mohammad"),
        Expense(4, 30.0, "Amirhossein", "Cake")
    )
    val balanceList = listOf<Balance>(
        Balance("Sarvenaz", mapOf("Amirhossein" to 30.0)),
        Balance("Mohammad", mapOf("Amirhossein" to 20.0))
    )
    val group = Group(eventList, balanceList)
    DongchiTheme {
        GroupScreen(group = group, null)
    }
}