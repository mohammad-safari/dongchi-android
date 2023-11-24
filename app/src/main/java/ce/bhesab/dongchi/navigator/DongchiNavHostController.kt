package ce.bhesab.dongchi.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ce.bhesab.dongchi.screen.DashboardScreen
import ce.bhesab.dongchi.screen.Intro

@Composable
fun DongchiNavHostController(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "intro"
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable("intro"){
            Intro(navController)
        }
        composable("dashboard"){
            val dashboardScreen = DashboardScreen()
            dashboardScreen.Dashboard(navController = navController, overview = dashboardScreen.mockOverview)
        }
    }
}

