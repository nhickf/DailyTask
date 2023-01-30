package com.gcpx.tasklist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gcpx.core.data.model.Task
import com.gcpx.core.data.model.defaultTask
import com.gcpx.core.ui.TextIcon
import com.gcpcx.core.R

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    TaskItem(
        defaultTask
    )
}

@Composable
fun TaskItem(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Color.Unspecified,
                shape = RoundedCornerShape(15.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = task.title
        )
        TextIcon(
            text = task.length.toString(),
            trailingIcon = R.drawable.ic_clock
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null)
    }
}
