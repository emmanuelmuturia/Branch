package branch.settings.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import branch.commons.components.BranchBackgroundImage
import branch.commons.components.BranchFooter
import branch.commons.components.BranchHeader
import branch.commons.theme.BranchDarkBlue
import branch.settings.uilayer.R

@Composable
fun AboutScreen(navigateBack: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {

            BranchHeader(
                navigateBack = navigateBack,
                headerTitle = stringResource(id = R.string.about)
            )

            AboutScreenContent()

            Spacer(modifier = Modifier.weight(weight = 1f))

            BranchFooter()

        }

    }

}


@Composable
private fun AboutScreenContent() {

    val aboutScreenViewModel: AboutScreenViewModel = hiltViewModel()

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 7.dp, end = 7.dp),
        verticalArrangement = Arrangement.spacedBy(space = 28.dp)
    ) {

        item(key = 1) {
            AboutListItem(
                imageId = R.drawable.privacy,
                textId = R.string.privacy_policy,
                onClick = { aboutScreenViewModel.getPrivacyPolicy(context = context) }
            )
        }

        item(key = 2) {
            AboutListItem(
                imageId = R.drawable.terms,
                textId = R.string.terms_conditions,
                onClick = { aboutScreenViewModel.getTermsOfUse(context = context) }
            )
        }

        item(key = 3) {

            AboutListItem(imageId = R.drawable.version, textId = R.string.app_version) { }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 57.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "(${aboutScreenViewModel.getAppVersion(context = context)})",
                    style = MaterialTheme.typography.bodyLarge,
                    color = BranchDarkBlue
                )

            }

        }

    }

}

@Composable
private fun AboutListItem(imageId: Int, textId: Int, onClick: () -> Unit) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 7.dp)
                .clickable(onClick = onClick),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                imageVector = ImageVector.vectorResource(id = imageId),
                contentDescription = stringResource(id = textId)
            )

            Spacer(modifier = Modifier.width(width = 21.dp))

            Text(
                text = stringResource(id = textId),
                style = MaterialTheme.typography.titleLarge,
                color = BranchDarkBlue
            )

        }

}