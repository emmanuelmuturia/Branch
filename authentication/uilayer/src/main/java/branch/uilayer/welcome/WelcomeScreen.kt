package branch.uilayer.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import branch.authentication.uilayer.BuildConfig
import branch.authentication.uilayer.R
import branch.commons.components.BranchBackgroundImage
import branch.commons.theme.BranchDarkBlue

@Composable
fun WelcomeScreen(navigateToMessagesScreen: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        WelcomeScreenBottomSheet(navigateToMessagesScreen = navigateToMessagesScreen)

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WelcomeScreenBottomSheet(navigateToMessagesScreen: () -> Unit) {

    var isChecked by rememberSaveable {
        mutableStateOf(value = false)
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 420.dp,
        sheetDragHandle = { BottomSheetDefaults.DragHandle(
            color = Color.White,
            height = 7.dp
        ) },
        sheetTonalElevation = 21.dp,
        containerColor = Color.Transparent,
        sheetContainerColor = BranchDarkBlue,
        sheetShape = RoundedCornerShape(size = 49.dp),
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(height = 10.dp))

                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 35.sp
                )

                Spacer(modifier = Modifier.height(height = 10.dp))

                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = stringResource(R.string.welcome_message),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(height = 10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 3.dp, end = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        modifier = Modifier.testTag(tag = "welcomeScreenCheckBox"),
                        checked = isChecked, onCheckedChange = {
                            isChecked = it
                        }, colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            checkmarkColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.width(width = 3.dp))

                    HyperlinkText(
                        fullText = stringResource(R.string.agreement),
                        hyperLinks = mutableMapOf(
                            stringResource(R.string.terms_of_use) to BuildConfig.termsOfUse,
                            stringResource(R.string.privacy_policy) to BuildConfig.privacyPolicy
                        )
                    )

                }

                Spacer(modifier = Modifier.height(height = 14.dp))

                if (isChecked) {
                    Button(
                        modifier = Modifier.width(width = 240.dp),
                        onClick = navigateToMessagesScreen,
                        shape = RoundedCornerShape(size = 15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(width = 1.dp, color = Color.White)
                    ) {
                        Text(
                            text = stringResource(R.string.let_s_go),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }

            }
        }) {

    }

}


@Composable
private fun HyperlinkText(
    fullText: String,
    hyperLinks: Map<String, String>
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        for ((key, value) in hyperLinks) {

            val startIndex = fullText.indexOf(key)
            if (startIndex >= 0) {
                val endIndex = startIndex + key.length

                addStyle(
                    style = SpanStyle(
                        fontSize = TextUnit.Unspecified,
                        fontWeight = FontWeight.Normal,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = startIndex,
                    end = endIndex
                )
                addStringAnnotation(
                    tag = "URL",
                    annotation = value,
                    start = startIndex,
                    end = endIndex
                )
            }
        }
        addStyle(
            style = SpanStyle(
                fontSize = TextUnit.Unspecified
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = Modifier,
        text = annotatedString,
        style = MaterialTheme.typography.labelLarge,
        onClick = {
            annotatedString
                .getStringAnnotations(tag = "URL", start = it, end = it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}