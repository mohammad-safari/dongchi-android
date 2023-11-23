package ce.bhesab.dongchi.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.model.group.Balance

@Composable
fun BalanceCard(balance: Balance){
    var extend = true
//    var onExtendChange : () ->Unit  = {
//        extend = !extend
//    }
    Card {
        Column (modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            Row (modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = balance.user)
                Button(onClick = {extend = !extend}) {
                    if (extend) Text(text = "up")
                    else Text(text = "down")
                }
            }
//            Spacer()
            if (extend){
                for (entry in balance.values.entries){
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = entry.key)
                        Text(text = entry.value.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceCardPreview(){
    BalanceCard(balance = Balance("Sarvenaz", mapOf("Amirhossein" to 2.0, "Mohammad" to -3.0)))
}