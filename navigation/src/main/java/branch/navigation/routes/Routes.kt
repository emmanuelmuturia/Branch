package branch.navigation.routes

sealed class Routes(val route: String) {

    data object WelcomeScreen : Routes(route = "welcomeScreen")

    data object LoginScreen : Routes(route = "loginScreen")

    data object MessagesScreen : Routes(route = "messagesScreen")

    data object ConversationScreen : Routes(route = "conversationScreen")

    data object AboutScreen : Routes(route = "aboutScreen")

    data object LoadingScreen : Routes(route = "loadingScreen")

    data object ErrorScreen : Routes(route = "errorScreen")

}