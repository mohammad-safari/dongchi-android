package ce.bhesab.dongchi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ce.bhesab.dongchi.screen.DashboardScreen
import ce.bhesab.dongchi.screen.GroupScreen
import ce.bhesab.dongchi.screen.Intro
import ce.bhesab.dongchi.screen.LoginScreen
import ce.bhesab.dongchi.screen.SharesScreen
import ce.bhesab.dongchi.screen.SignUpScreen
import ce.bhesab.dongchi.screen.ViewGroups
import ce.bhesab.dongchi.theme.DongchiTheme
import ce.bhesab.dongchi.viewmodel.BaseViewModel
import ce.bhesab.dongchi.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private val baseViewModel = BaseViewModel(this)

    private val loginViewModel = LoginViewModel(this)

    init {
        baseViewModel.checkLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DongchiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }

    @Composable
    fun AppContent() {
        val startDestination = if (loginViewModel.loginSuccess.value) "dashboard" else "intro"
        val navController = rememberNavController()
        val currentGroup = remember { mutableStateOf("") }

        NavHost(navController = navController, startDestination = startDestination) {
            composable("intro") {
                Intro(navController)
            }
            composable("dashboard") {
                val dashboardScreen = DashboardScreen()
                dashboardScreen.Dashboard(
                    navController = navController,
                    overview = dashboardScreen.mockOverview
                )
            }
            composable("signup") {
                SignUpScreen(navController = navController, context = this@MainActivity)
            }
            composable("login") {
                LoginScreen(navController = navController, context = this@MainActivity)
            }
            composable("groups") {
                ViewGroups(navController = navController, context = this@MainActivity, currentGroup)
            }
            composable("group") {
                GroupScreen(navController = navController, context = this@MainActivity,currentGroup = currentGroup)
            }
            composable(
                "share/{groupId}",
                arguments = listOf(navArgument("groupId") { type = NavType.StringType })
            ) { backStackEntry ->
                val share = SharesScreen()
                val groupId = backStackEntry.arguments?.getString("groupId")
                if (groupId != null)
                    share.SharesSetting(groupId, navController = navController)
            }
        }
    }

    @Preview(showBackground = true, showSystemUi = true, locale = "fa")
    @Composable
    fun GreetingPreview() {
        DongchiTheme {
            AppContent()
        }
    }
}
