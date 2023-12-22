package ce.bhesab.dongchi.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event

@Composable
fun BalanceList(balanceList: List<Balance>){
    LazyColumn{
        items(balanceList) {balance ->
            BalanceCard(balance = balance)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceListPreview(){
    val balanceList = listOf<Balance>(Balance("Sarvenaz", mapOf("Amirhossein" to 30.0)),
                                      Balance("Mohammad", mapOf("Amirhossein" to 20.0))
    )
    BalanceList(balanceList = balanceList)
}