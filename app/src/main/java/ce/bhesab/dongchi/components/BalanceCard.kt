package ce.bhesab.dongchi.components

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.GroupScreenState

@Composable
fun BalanceCard(balance: Balance){
    var extend by remember {
        mutableStateOf(true)
    }
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
                        /* TODO */
                        if (extend) Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(MaterialTheme.shapes.large),
                            tint = Color.White,
                            contentDescription = null,
                        )
                        else Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(MaterialTheme.shapes.large),
                            tint = Color.White,
                            contentDescription = null,
                        )
                    }
                }
                if (extend){
                    for (entry in balance.values.entries){
                        Card {
                            Row(modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = entry.key)
                                if(entry.value < 0) Text(text = entry.value.toString(), color = Color.Red)
                                else Text(text = entry.value.toString(), color = Color.Green)
                            }
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