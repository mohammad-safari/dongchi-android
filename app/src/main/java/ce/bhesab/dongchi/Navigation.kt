//package ce.bhesab.dongchi
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//
//@Composable
//fun Navigation(
//    modifier: Modifier = Modifier,
//    navController:  = rememberNavController(),
//    startDestination: String = "profile"
//) {
//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = startDestination
//    ) {
//        composable("profile") {
//            ProfileScreen(
//                onNavigateToFriends = { navController.navigate("friendsList") },
//                /*...*/
//            )
//        }
//        composable("friendslist") { FriendsListScreen(/*...*/) }
//    }
//}
//
//@Composable
//fun ProfileScreen(
//    onNavigateToFriends: () -> Unit,
//    /*...*/
//) {
//    /*...*/
//    Button(onClick = onNavigateToFriends) {
//        Text(text = "See friends list")
//    }
//}