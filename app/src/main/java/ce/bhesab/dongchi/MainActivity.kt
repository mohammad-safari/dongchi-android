package ce.bhesab.dongchi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ce.bhesab.dongchi.theme.DongchiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DongchiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) { TODO("not yet implemented") }
                }
            }
        }
    }
}

@Composable
fun DebtScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Overall Debt Status
        TotalDebt()

        // List of Individual Debts
        DebtList()

        // Bottom Button
        StartNewGroupButton()
    }
}

@Composable
fun TotalDebt() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) // Adjust as needed
            .background(Color.Red)
    ) {
        Text(
            text = "Overall, you owe $491.00",
            //style = MaterialTheme.typography.h6,
            color = Color.White
        )
    }
}

@Composable
fun DebtList() {
    // You'll want to replace this with a dynamic list, probably using LazyColumn
    Column {
        DebtItem(name = "913", amount = 692.00, isOwed = true)
        DebtItem(name = "Sharafi 1401 fall", amount = 201.00, isOwed = false)
        // Add more items...
    }
}

@Composable
fun DebtItem(name: String, amount: Double, isOwed: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = if (isOwed) "You owe $name $$amount" else "$name owes you $$amount",
            //style = MaterialTheme.typography.subtitle1
        )
        // Add icons or other elements as needed
    }
}

@Composable
fun StartNewGroupButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(onClick = { /* Handle click */ }) {
            Text("Start a new group")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DebtScreen()
}