package com.gcpcx.dailytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gcpcx.taskdetail.navigation.navigateToTaskDetail
import com.gcpcx.taskdetail.navigation.taskDetail
import com.gcpx.tasklist.navigation.navigateToTaskList
import com.gcpx.tasklist.navigation.taskList
import com.gcpx.tasklist.navigation.taskListRoute
import com.grpcx.configuretask.navigation.configureTask
import com.grpcx.configuretask.navigation.navigateToConfigureTask

@Composable
fun AppNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = taskListRoute
) {
    
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        taskList(
            navigateToConfigureTask = {
                navController.navigateToConfigureTask()
            },
            onItemClick = {
                navController.navigateToTaskDetail()
            }
        )
        configureTask()
        taskDetail(navigateToTaskList = {
            navController.navigateToTaskList()
        })
    }

}