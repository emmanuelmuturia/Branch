package branch.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import branch.datalayer.BranchSharedPreferences
import branch.navigation.routes.Routes

@Composable
fun NavGraph(navController: NavHostController) {

    val isFirstTimeUser = BranchSharedPreferences(context = LocalContext.current).isFirstTimeUser

    NavHost(
        navController = navController,
        startDestination = if (isFirstTimeUser) Routes.WelcomeScreen.route else Routes.LoginScreen.route
    ) {

        composable(route = Routes.WelcomeScreen.route) {

        }

        composable(route = Routes.LoginScreen.route) {

        }

        composable(route = Routes.MessagesScreen.route) {

        }

        composable(route = Routes.ConversationScreen.route) {

        }

        composable(route = Routes.AboutScreen.route) {

        }

        composable(route = Routes.LoadingScreen.route) {

        }

        composable(route = Routes.ErrorScreen.route) {

        }

    }

}