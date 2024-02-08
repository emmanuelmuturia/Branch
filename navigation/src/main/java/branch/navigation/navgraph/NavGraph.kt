package branch.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import branch.commons.state.ErrorScreen
import branch.commons.state.LoadingScreen
import branch.datalayer.BranchSharedPreferences
import branch.navigation.routes.Routes
import branch.settings.about.AboutScreen
import branch.uilayer.conversation.ConversationScreen
import branch.uilayer.messages.MessagesScreen
import branch.uilayer.login.LoginScreen
import branch.uilayer.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {

    val isFirstTimeUser = BranchSharedPreferences(context = LocalContext.current).isFirstTimeUser

    NavHost(
        navController = navController,
        startDestination = if (isFirstTimeUser) Routes.WelcomeScreen.route else Routes.LoginScreen.route
    ) {

        composable(route = Routes.WelcomeScreen.route) {
            WelcomeScreen { navController.navigate(route = Routes.MessagesScreen.route) }
        }

        composable(route = Routes.LoginScreen.route) {
            LoginScreen()
        }

        composable(route = Routes.MessagesScreen.route) {
            MessagesScreen()
        }

        composable(route = Routes.ConversationScreen.route) {
            ConversationScreen()
        }

        composable(route = Routes.AboutScreen.route) {
            AboutScreen { navController.popBackStack() }
        }

        composable(route = Routes.LoadingScreen.route) {
            LoadingScreen()
        }

        composable(route = Routes.ErrorScreen.route) {
            ErrorScreen { navController.popBackStack() }
        }

    }

}