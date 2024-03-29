package branch.commons.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import branch.commons.uilayer.R

@Composable
fun BranchBackgroundImage() {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.branch),
        contentDescription = stringResource(R.string.branch_background_image),
        contentScale = ContentScale.FillBounds
    )

}