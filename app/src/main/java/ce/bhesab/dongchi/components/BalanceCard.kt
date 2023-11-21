package ce.bhesab.dongchi.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ce.bhesab.dongchi.model.Balance

@Composable
fun BalanceCard(balance: Balance, extend: Boolean, onExtendChange: (Boolean) -> Unit){
    Card {
        Column {
            Row {
                Text(text = balance.user)
                Button(onClick = {onExtendChange(!extend)}) {
                    // icon ^
                }
            }
//            Spacer()
            if (extend){
                for (entry in balance.values.entries){
                    Row {
                        Text(text = entry.key)
                        Text(text = entry.value.toString())
                    }
                }
            }
        }
    }
}