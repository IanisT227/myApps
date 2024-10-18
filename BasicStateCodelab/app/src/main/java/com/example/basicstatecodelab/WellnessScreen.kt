package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter(modifier)
        WellnessTaskList(list = wellnessViewModel.tasks,
            modifier = modifier,
            onCloseTask = { task -> wellnessViewModel.remove(task) },
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskCheckStatus(
                    task,
                    checked
                )
            })
    }

}