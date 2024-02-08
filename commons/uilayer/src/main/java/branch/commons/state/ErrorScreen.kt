package branch.commons.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import branch.commons.components.BranchBackgroundImage
import branch.commons.components.BranchHeader
import branch.commons.uilayer.R

@Composable
fun ErrorScreen(navigateBack: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {

            BranchHeader(navigateBack = navigateBack, headerTitle = "Error")

            ErrorScreenContent()

        }

    }

}


@Composable
private fun ErrorScreenContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(size = 140.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.error),
            contentDescription = stringResource(R.string.error_image),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(height = 21.dp))

        Text(
            modifier = Modifier.padding(start = 7.dp, end = 7.dp),
            text = stringResource(R.string.error_encountered_please_try_again),
            style = MaterialTheme.typography.bodyLarge
        )

    }

}