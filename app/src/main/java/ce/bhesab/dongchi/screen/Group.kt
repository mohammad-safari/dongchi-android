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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.MainActivity
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.component.BalanceList
import ce.bhesab.dongchi.component.BottomNavigationBar
import ce.bhesab.dongchi.component.EventList
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.model.group.GroupScreenState
import ce.bhesab.dongchi.viewmodel.GroupViewModel

@Composable
fun GroupScreen(
    navController: NavController?,
    modifier: Modifier = Modifier,
    currentGroup: MutableState<String>,
    context: MainActivity
) {
    var state by remember {
        mutableStateOf(GroupScreenState.EVENT)
    }

    val groupViewModel = remember { GroupViewModel(context) }

    LaunchedEffect(state, groupViewModel.events, groupViewModel.balances) {
        if (state == GroupScreenState.BALANCE) {
            groupViewModel.fetchBalances(currentGroup.value)
        } else {
            groupViewModel.fetchEvents(currentGroup.value)
        }
    }

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
            TabRow(selectedTabIndex = if (state == GroupScreenState.BALANCE) 0 else 1) {
                Tab(
                    selected = state == GroupScreenState.BALANCE,
                    onClick = {
                        state = GroupScreenState.BALANCE
                        groupViewModel.fetchBalances(currentGroup.value)
                    },
                    text = {
                        Text(text = stringResource(id = R.string.balances))
                    },
                )
                Tab(
                    selected = state == GroupScreenState.EVENT,
                    onClick = {
                        state = GroupScreenState.EVENT
                        groupViewModel.fetchEvents(currentGroup.value)
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
                if (groupViewModel.fetchErrorState.value) {
                    ClickableText(
                        text = AnnotatedString("retry"),
                        style = TextStyle.Default.copy(
                            textDecoration = TextDecoration.Underline,
                            color = MaterialTheme.colorScheme.secondary
                        ),
                        onClick = {
                            if (state == GroupScreenState.BALANCE) {
                                groupViewModel.fetchBalances(currentGroup.value)
                            } else {
                                groupViewModel.fetchEvents(currentGroup.value)
                            }
                        }
                    )
                } else {
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
                .padding(bottom = 50.dp, end = 7.dp),
            navController
        )

        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            navController = navController
        )
    }


}

//@Preview(showBackground = true, showSystemUi = true, locale = "fa")
//@Composable
//fun GroupScreenPreview() {
//    DongchiTheme {
//        GroupScreen(
//            null,
//            currentGroup = remember { mutableStateOf("") },
//            context = null
//        )
//    }
//}