package com.grpcx.configuretask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.grpcx.configuretask.ui.ConfigureTaskScreen

const val configureTaskRoute = "configure_task_route"

fun NavController.navigateToConfigureTask(navOptions: NavOptions? = NavOptions.Builder()
    .setLaunchSingleTop(true).build()){
    this.navigate(
        route = configureTaskRoute,
        navOptions = navOptions,

    )
}

fun NavGraphBuilder.configureTask(){
    composable(route = configureTaskRoute){
        ConfigureTaskScreen()
    }
}