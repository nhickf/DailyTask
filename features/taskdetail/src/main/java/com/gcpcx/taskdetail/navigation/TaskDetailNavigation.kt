package com.gcpcx.taskdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import com.gcpcx.taskdetail.ui.TaskDetailScreen

const val taskDetailParam = "taskId"
const val taskDetailRoute = "task_detail_route"
const val taskDetailRouteWithParam = "task_detail_route/{$taskDetailParam}"

fun NavController.navigateToTaskDetail(
    taskId : Int,
    navOptions: NavOptions? = NavOptions.Builder()
        .setLaunchSingleTop(true).build()
) {
    this.navigate(
        route = "${taskDetailRoute}/$taskId",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.taskDetail(
    navigateToTaskList: () -> Unit = {},
) {
    composable(route = taskDetailRouteWithParam) {
        TaskDetailScreen(
            leadingIconOnClick = navigateToTaskList,
            taskId = it.arguments?.getString(taskDetailParam)?.toInt() ?: 0
        )
    }
}