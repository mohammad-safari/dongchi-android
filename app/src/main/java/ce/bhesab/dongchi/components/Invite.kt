package ce.bhesab.dongchi.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Invite(){
    Column {
        Box {
            Text(text = "code")
        }
        Text(text = "You can invite others using this link")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Invite")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Later")
        }
    }
}