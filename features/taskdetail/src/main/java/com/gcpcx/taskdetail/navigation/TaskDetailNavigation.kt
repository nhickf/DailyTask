package com.gcpcx.taskdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gcpcx.taskdetail.ui.TaskDetailScreen

const val taskDetailRoute = "task_detail_route"

fun NavController.navigateToTaskDetail(navOptions: NavOptions? = NavOptions.Builder()
    .setLaunchSingleTop(true).build()) {
    this.navigate(
        route = taskDetailRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.taskDetail(
    navigateToTaskList: () -> Unit = {},
) {
    composable(route = taskDetailRoute) {
        TaskDetailScreen(leadingIconOnClick = navigateToTaskList)
    }
}