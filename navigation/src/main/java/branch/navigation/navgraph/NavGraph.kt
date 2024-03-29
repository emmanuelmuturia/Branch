package branch.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import branch.commons.state.ErrorScreen
import branch.commons.state.LoadingScreen
import branch.navigation.routes.Routes
import branch.settings.about.AboutScreen
import branch.uilayer.conversation.ConversationScreen
import branch.uilayer.login.LoginScreen
import branch.uilayer.messages.MessagesScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route
    ) {

        composable(route = Routes.LoginScreen.route) {
            LoginScreen(
                navigateToMessagesScreen = { navController.navigate(route = Routes.MessagesScreen.route) }
            )
        }

        composable(route = Routes.MessagesScreen.route) {
            MessagesScreen(navController = navController)
        }

        composable(route = Routes.ConversationScreen.route, arguments = listOf(
            navArgument(name = "messageThreadId") {
                type = NavType.IntType
            }
        )) {
            ConversationScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
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