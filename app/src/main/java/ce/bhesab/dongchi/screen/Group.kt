package ce.bhesab.dongchi.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
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
import ce.bhesab.dongchi.viewmodel.GroupViewModel
import com.google.android.material.tabs.TabLayout

@Composable
fun GroupScreen(navController: NavController?, modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(GroupScreenState.EVENT)
    }

    val groupViewModel = GroupViewModel()
    if (state == GroupScreenState.BALANCE){
        groupViewModel.fetchBalances("8", "tokentoken")
    } else {
        groupViewModel.fetchEvents("8", "tokentoken")
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
            TabRow(selectedTabIndex = if(state == GroupScreenState.BALANCE) 0 else 1) {
            Tab(
                selected = state == GroupScreenState.BALANCE,
                onClick = {
                    state = GroupScreenState.BALANCE
                    groupViewModel.fetchBalances("8", "tokentoken")
                          },
                text = {
                    Text(text = stringResource(id = R.string.balances))
                },
            )
            Tab(selected = state == GroupScreenState.EVENT,
                onClick = {
                    state = GroupScreenState.EVENT
                    groupViewModel.fetchEvents("8", "tokentoken")
                          },
                text = {
                    Text(text = stringResource(id = R.string.events))
                },
            )
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                if(groupViewModel.fetchErrorState.value){
                    ClickableText(
                        text = AnnotatedString("retry"),
                        style = TextStyle.Default.copy(
                            textDecoration = TextDecoration.Underline,
                            color = MaterialTheme.colorScheme.secondary
                        ),
                        onClick = {
                            if (state == GroupScreenState.BALANCE){
                                groupViewModel.fetchBalances("8", "tokentoken")
                            } else {
                                groupViewModel.fetchEvents("8", "tokentoken")
                            }
                        }
                    )
                } else{
                    if (state == GroupScreenState.EVENT) {
                        EventList(eventList = groupViewModel.events.value)
                    } else if (state == GroupScreenState.BALANCE) {
                        BalanceList(balanceList = groupViewModel.balances.value)
                    }
                }
            }

        }

        PlusButtonInsert(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp),
            navController
        ) {
            navController?.navigate("share")
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
    DongchiTheme {
        GroupScreen(null)
    }
}