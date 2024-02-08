package branch.commons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import branch.commons.uilayer.R
import java.util.Calendar

@Composable
fun BranchFooter() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 7.dp)
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(
                R.string.branch_footer,
                Calendar.getInstance()[Calendar.YEAR]
            ),
            style = MaterialTheme.typography.labelLarge
        )

    }

}