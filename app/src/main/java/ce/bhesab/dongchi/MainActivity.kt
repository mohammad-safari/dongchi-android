package ce.bhesab.dongchi

import android.content.Context
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Group
import ce.bhesab.dongchi.model.group.Transaction
import ce.bhesab.dongchi.screen.DashboardScreen
import ce.bhesab.dongchi.screen.GroupScreen
import ce.bhesab.dongchi.screen.Intro
import ce.bhesab.dongchi.screen.LoginScreen
import ce.bhesab.dongchi.screen.SharesScreen
import ce.bhesab.dongchi.screen.SignUpScreen
import ce.bhesab.dongchi.screen.view1_amir.ViewGroups
import ce.bhesab.dongchi.theme.DongchiTheme
import ce.bhesab.dongchi.viewmodel.BaseViewModel

class MainActivity() : AppCompatActivity() {
    private val baseViewModel = BaseViewModel(this)

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
        val startDestination = if (baseViewModel.isLogin.value) "dashboard" else "intro"
        val navController = rememberNavController()

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
                ViewGroups(navController = navController)
            }
            composable("group") {
                GroupScreen(navController = navController)
            }
            composable("share") {
                val share = SharesScreen()
                share.Shares(
                    listOf(
                        SharesScreen.Share("محمد", 0.5f, Color.Cyan),
                        SharesScreen.Share("امیر", 0.3f, Color.Red),
                        SharesScreen.Share("حسین", 0f, Color.Yellow),
                        SharesScreen.Share("سروناز", 0.2f, Color.Green)
                    ), navController = navController
                )
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
