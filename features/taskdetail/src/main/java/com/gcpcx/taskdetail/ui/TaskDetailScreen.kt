package com.gcpcx.taskdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gcpx.core.ui.TextIcon
import com.gcpcx.core.R
import com.gcpx.core.ui.AppToolbar

@Composable
fun TaskDetailScreen(
    leadingIconOnClick: () -> Unit = {},
) {
    TaskDetailContent(leadingIconOnClick)
}

@Composable
fun TaskDetailContent(
    leadingIconOnClick: () -> Unit = {},
) {
    Column {
        AppToolbar(
            leadingIcon = Icons.Default.KeyboardArrowLeft,
            toolbarTitle = "Daily Tasks",
            leadingIconOnClick = leadingIconOnClick
        )
        TaskDetailScope()

    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScope() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                color = Color(
                    arrayOf(
                        0xFFffff00,
                        0xFFbedcaf,
                        0xFFeda3a2,
                    ).random()
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val (minutes, timer, controller) = createRefs()

            TaskRemainingMinutes(
                modifier = Modifier.constrainAs(minutes) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            TaskTimer(
                modifier = Modifier.constrainAs(timer) {
                    top.linkTo(minutes.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(controller.top)
                }
            )
            TaskTimerController(
                modifier = Modifier.constrainAs(controller) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Composable
fun TaskRemainingMinutes(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ElapsedMinutes(modifier = Modifier.weight(1f))
        RemainingMinutes(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun ElapsedMinutes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,

        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Minutes Elapsed")
        TextIcon(
            text = "12",
            leadingIcon = R.drawable.ic_hourglass_bottom
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RemainingMinutes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Minutes Remaining")
        TextIcon(
            text = "12",
            trailingIcon = R.drawable.ic_hourglass_top
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTimer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Design the app", fontSize = 32.sp)
        Timer()
    }
}

@Composable
private fun Timer() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "120", fontSize = 56.sp)

                Text(
                    text = "Minutes",
                    modifier = Modifier.wrapContentWidth(),
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = ":", fontSize = 56.sp)
                Spacer(modifier = Modifier.height(10.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "45", fontSize = 56.sp)

                Text(
                    text = "Seconds",
                    modifier = Modifier.wrapContentWidth(),
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

//
//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceAround
//                )
//        {
//            Text(text = "Minutes",
//                modifier = Modifier.weight(1f),
//                fontSize = 8.sp,
//                textAlign = TextAlign.Center
//            )
//            Text(text = "Seconds",
//                modifier = Modifier.weight(1f),
//                fontSize = 8.sp,
//                textAlign = TextAlign.Center
//            )
//
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTimerController(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_stop),
                contentDescription = null
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_play_circle),
                contentDescription = null
            )

        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_pause_circle),
                contentDescription = null
            )
        }
    }
}