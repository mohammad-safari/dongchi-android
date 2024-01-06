package ce.bhesab.dongchi.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.theme.DongchiTheme

@Composable
fun JoinGroup(navController: NavController?) {
    var code by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                modifier = Modifier
                    .padding(vertical = 8.dp),
                label = { Text(stringResource(R.string.code)) },
                leadingIcon = {
                    Icon(Icons.Default.Code, contentDescription = null)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.join))
            }
        }
    }
}


@Preview(showSystemUi = true, locale = "fa")
@Composable
fun Amos() {
    DongchiTheme {
        JoinGroup(null)
    }
}