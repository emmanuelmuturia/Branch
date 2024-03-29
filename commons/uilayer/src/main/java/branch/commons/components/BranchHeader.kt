package branch.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import branch.commons.theme.BranchDarkBlue
import branch.commons.uilayer.R

@Composable
fun BranchHeader(navigateBack: () -> Unit, headerTitle: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 10.dp, bottom = 21.dp)
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier
                .weight(weight = 1f)
        ) {

            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.arrow_back),
                    tint = BranchDarkBlue
                )
            }

        }

        Row(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = headerTitle,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 25.sp,
                color = BranchDarkBlue
            )
        }

        Spacer(modifier = Modifier.weight(weight = 1f))

    }

}