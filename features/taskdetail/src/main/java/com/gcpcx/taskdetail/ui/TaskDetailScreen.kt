package com.gcpcx.taskdetail.ui

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.gcpcx.taskdetail.data.Timer
import com.gcpcx.taskdetail.domain.TaskDetailUiState
import com.gcpcx.taskdetail.domain.TaskDetailViewModel
import com.gcpx.core.data.model.Task
import com.gcpx.core.ui.AppToolbar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.Duration

@Composable
@Preview(showBackground = true)
fun TaskDetailScreen(
    taskId: Int = 0,
    leadingIconOnClick: () -> Unit = {},
) {

    val viewModel = koinViewModel<TaskDetailViewModel> {
        parametersOf(taskId)
    }
    val uiState by viewModel.uiState.collectAsState()

    TaskDetailContent(
        state = uiState,
        leadingIconOnClick = leadingIconOnClick,
        onStart = viewModel::startTimer,
        onPause = {},
        onStop = {}
    )
}

@Composable
fun TaskDetailContent(
    state: TaskDetailUiState,
    leadingIconOnClick: () -> Unit = {},
    onStart: (length: Int) -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
) {
    Column {
        AppToolbar(
            leadingIcon = Icons.Default.KeyboardArrowLeft,
            toolbarTitle = "Daily Tasks",
            leadingIconOnClick = leadingIconOnClick
        )
        when (state) {
            is TaskDetailUiState.Success -> {
                TaskDetailScope(
                    task = state.timer,
                    onStart = {
                        onStart(state.timer.length)
                    },
                    onPause = onPause,
                    onStop = onStop
                )
            }
            else -> {}
        }

    }
}

@Composable
fun TaskDetailScope(
    task: Timer,
    onStart: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
) {
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
                },
                task = task
            )
            TaskTimerController(
                modifier = Modifier.constrainAs(controller) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                onStart = onStart,
                onPause = onPause,
                onStop = onStop
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
private fun ElapsedMinutes(
    minutes: String = "12",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Minutes Elapsed")
        TextIcon(
            text = minutes,
            leadingIcon = R.drawable.ic_hourglass_bottom
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RemainingMinutes(
    minutes: String = "12",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Minutes Remaining")
        TextIcon(
            text = minutes,
            trailingIcon = R.drawable.ic_hourglass_top
        )
    }
}

@Composable
fun TaskTimer(
    task: Timer,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Design the app", fontSize = 32.sp)
        Timer(
            minutes = task.minutes,
            seconds = task.seconds
        )
    }
}

@Composable
private fun Timer(
    minutes: String = "120",
    seconds: String = "45"
) {
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
                Text(text = minutes, fontSize = 56.sp)

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
                Text(text = seconds, fontSize = 56.sp)

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
fun TaskTimerController(
    modifier: Modifier = Modifier,
    onStart: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onStop) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_stop),
                contentDescription = null
            )
        }
        IconButton(onClick = onStart) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_play_circle),
                contentDescription = null
            )

        }
        IconButton(onClick = onPause) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_pause_circle),
                contentDescription = null
            )
        }
    }
}