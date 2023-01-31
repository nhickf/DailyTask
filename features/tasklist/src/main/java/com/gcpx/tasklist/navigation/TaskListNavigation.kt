package com.gcpx.tasklist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gcpx.tasklist.ui.TaskListScreen

const val taskListRoute = "task_list_route"

fun NavController.navigateToTaskList(navOptions: NavOptions? = NavOptions.Builder()
    .setLaunchSingleTop(true)
    .setPopUpTo(route = taskListRoute,inclusive = true)
    .build()) {
    this.navigate(taskListRoute, navOptions)
}

fun NavGraphBuilder.taskList(
    navigateToConfigureTask: () -> Unit,
    onItemClick : () -> Unit
) {
    composable(route = taskListRoute) {
        TaskListScreen(
            navigateToConfigureTask = navigateToConfigureTask,
            onItemClick = onItemClick
        )
    }
}