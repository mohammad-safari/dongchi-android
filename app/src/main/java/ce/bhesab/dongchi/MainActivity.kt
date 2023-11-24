package ce.bhesab.dongchi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ce.bhesab.dongchi.screen.DashboardScreen
import ce.bhesab.dongchi.theme.DongchiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DongchiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Root()
                }
            }
        }
    }
}

@Composable
fun Root(modifier: Modifier = Modifier) {
    val dashboardScreen = DashboardScreen()
    dashboardScreen.Dashboard(navController = null, overview = dashboardScreen.mockOverview)
}

@Preview(showBackground = true, showSystemUi = true, locale = "fa")
@Composable
fun GreetingPreview() {
    DongchiTheme {
        Root()
    }
}