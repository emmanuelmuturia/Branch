package branch.uilayer.conversation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import branch.commons.components.BranchBackgroundImage
import branch.commons.components.BranchHeader
import branch.commons.state.ErrorScreen
import branch.commons.state.LoadingScreen
import branch.commons.theme.BranchDarkBlue
import branch.domainlayer.BranchState
import branch.uilayer.messages.BranchMessageItem
import kotlinx.coroutines.launch

@Composable
fun ConversationScreen(
    navigateBack: () -> Unit,
    navController: NavHostController
) {

    val conversationScreenViewModel: ConversationScreenViewModel = hiltViewModel()
    val branchMessages by conversationScreenViewModel.branchMessages.collectAsStateWithLifecycle()
    val branchState by conversationScreenViewModel.branchState.collectAsStateWithLifecycle()

    var messageResponse by rememberSaveable { mutableStateOf(value = "") }

    val messageThreadId = navController.currentBackStackEntry?.arguments?.getInt("messageThreadId")

    LaunchedEffect(key1 = Unit) {

        if (messageThreadId != null) {
            conversationScreenViewModel.getMessagesByThread(threadId = messageThreadId)
        }

    }

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        when (branchState) {

            is BranchState.Loading -> LoadingScreen()

            is BranchState.Error -> ErrorScreen { navigateBack() }

            else -> Column(modifier = Modifier.fillMaxSize()) {

                BranchHeader(navigateBack = navigateBack, headerTitle = "Conversation")

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
                        messageThreadId = messageThreadId
                    )
                }

            }

        }

    }

}


@Composable
private fun BranchMessageInputField(
    messageThreadId: Int,
    conversationScreenViewModel: ConversationScreenViewModel,
    messageResponse: String,
    onMessageResponseChanged: (String) -> Unit
) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = messageResponse,
            onValueChange = { onMessageResponseChanged(it) },
            label = {
                Text(text = "Enter message", style = MaterialTheme.typography.bodyLarge)
            },
            shape = RoundedCornerShape(size = 21.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(focusedContainerColor = BranchDarkBlue, unfocusedContainerColor = BranchDarkBlue)
        )

        Spacer(modifier = Modifier.width(width = 7.dp))

        Icon(
            modifier = Modifier
                .size(size = 42.dp)
                .clickable(onClick = {
                    scope.launch {
                        conversationScreenViewModel.createMessage(
                            messageThreadId = messageThreadId,
                            messageBody = messageResponse
                        )
                        conversationScreenViewModel.getMessagesByThread(threadId = messageThreadId)
                    }
                }),
            imageVector = Icons.AutoMirrored.Rounded.Send,
            contentDescription = "Notifications Button",
            tint = Color.White
        )

    }

}