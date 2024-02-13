package branch.uilayer.conversation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import branch.commons.components.BranchBackgroundImage
import branch.commons.components.BranchHeader
import branch.uilayer.messages.BranchMessageItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun ConversationScreen(navigateBack: () -> Unit, navController: NavHostController, navigateToMessagesScreen: () -> Unit) {

    val conversationScreenViewModel: ConversationScreenViewModel = hiltViewModel()
    val branchState by conversationScreenViewModel.branchState.collectAsStateWithLifecycle()
    val branchMessages by conversationScreenViewModel.branchMessages.collectAsStateWithLifecycle()

    var messageResponse by rememberSaveable { mutableStateOf(value = "") }

    val isLoading by conversationScreenViewModel.isLoading.collectAsStateWithLifecycle()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    val messageThreadId = navController.currentBackStackEntry?.arguments?.getInt("messageThreadId")

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {

            BranchHeader(navigateBack = navigateBack, headerTitle = "Conversations")

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    if (messageThreadId != null) {
                        conversationScreenViewModel.getMessagesByThread(threadId = messageThreadId)
                    }
                },
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White
                    )
                }) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(weight = 1f)
                ) {

                    items(items = branchMessages) { branchMessage ->
                        BranchMessageItem(
                            branchMessage = branchMessage,
                            navController = navController
                        )
                    }

                }

                if (messageThreadId != null) {
                    BranchMessageInputField(
                        messageResponse = messageResponse,
                        onMessageResponseChanged = { messageResponse = it },
                        conversationScreenViewModel = conversationScreenViewModel,
                        messageThreadId = messageThreadId,
                        navigateToMessagesScreen = navigateToMessagesScreen
                    )
                }

            }

        }

    }

}


@Composable
fun BranchMessageInputField(
    messageThreadId: Int,
    conversationScreenViewModel: ConversationScreenViewModel,
    messageResponse: String,
    onMessageResponseChanged: (String) -> Unit,
    navigateToMessagesScreen: () -> Unit
) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 3.dp)
    ) {

        OutlinedTextField(
            value = messageResponse,
            onValueChange = { onMessageResponseChanged(it) },
            label = {},
            shape = RoundedCornerShape(size = 21.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Icon(
            modifier = Modifier
                .size(size = 30.dp)
                .clickable(onClick = {
                    scope.launch {
                        conversationScreenViewModel.createMessage(
                            messageThreadId = messageThreadId,
                            messageBody = messageResponse
                        )
                    }
                }),
            imageVector = Icons.AutoMirrored.Rounded.Send,
            contentDescription = "Notifications Button",
            tint = Color.White
        )

    }

}