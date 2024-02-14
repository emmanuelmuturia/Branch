package branch.uilayer.messages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import branch.commons.components.BranchBackgroundImage
import branch.commons.state.ErrorScreen
import branch.commons.state.LoadingScreen
import branch.commons.theme.BranchDarkBlue
import branch.commons.theme.BranchLightBlue
import branch.commons.theme.Caveat
import branch.domainlayer.BranchState
import branch.domainlayer.dto.BranchMessage
import java.util.Calendar

@Composable
fun MessagesScreen(navController: NavHostController) {

    val messagesScreenViewModel: MessagesScreenViewModel = hiltViewModel()
    val branchState by messagesScreenViewModel.branchState.collectAsStateWithLifecycle()
    val branchMessages by messagesScreenViewModel.branchMessages.collectAsStateWithLifecycle()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            messagesScreenViewModel.getMessages()
        }, containerColor = BranchDarkBlue) {
            Icon(
                modifier = Modifier
                    .size(size = 30.dp),
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "Search Button",
                tint = Color.White
            )
        }
    }) {
        it

        Box(modifier = Modifier.fillMaxSize()) {

            BranchBackgroundImage()

            Column(modifier = Modifier.fillMaxSize()) {

                HomeScreenHeader(navController = navController)

                when (branchState) {

                    is BranchState.Loading -> LoadingScreen()

                    is BranchState.Error -> ErrorScreen {}

                    else -> LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        items(items = branchMessages) { branchMessage ->
                            BranchMessageItem(
                                branchMessage = branchMessage,
                                navController = navController
                            )
                        }

                    }

                }

            }

        }

    }

}


@Composable
fun HomeScreenHeader(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp, end = 14.dp, bottom = 21.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = displayGreeting(),
            style = MaterialTheme.typography.titleLarge,
            color = BranchDarkBlue
        )

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {
                    navController.navigate(route = "aboutScreen")
                }),
            imageVector = Icons.Rounded.Settings,
            contentDescription = "Settings Button",
            tint = BranchDarkBlue
        )

    }
}


@Composable
fun BranchMessageItem(branchMessage: BranchMessage, navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .clip(shape = RoundedCornerShape(size = 21.dp))
            .clickable {
                navController.navigate(route = "conversationScreen/${branchMessage.messageThreadId}")
            },
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = BranchLightBlue)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontFamily = Caveat,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(text = "User ID: ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontFamily = Caveat,
                                fontSize = 21.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(text = branchMessage.messageUserId)
                        }

                    }
                )

                if (branchMessage.messageAgentId != null) {

                    Spacer(modifier = Modifier.height(height = 7.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontFamily = Caveat,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(text = "Agent ID: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = Caveat,
                                    fontSize = 21.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            ) {
                                append(text = branchMessage.messageAgentId.toString())
                            }
                        }
                    )

                }

                Spacer(modifier = Modifier.height(height = 7.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontFamily = Caveat,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(text = "Sent At: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = Caveat,
                                fontSize = 21.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(text = branchMessage.messageTimestamp)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(height = 7.dp))

                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontFamily = Caveat,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(text = "Message: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Caveat,
                            fontSize = 21.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(text = branchMessage.messageBody)
                    }
                })

            }

        }
    }

}


private fun displayGreeting(): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> "Good Morning!" // 0 to 11 is considered morning
        in 12..16 -> "Good Afternoon!" // 12 to 16 is considered afternoon
        else -> "Good Evening!" // After 16 is considered evening
    }
}