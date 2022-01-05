package com.example.fooddairycompose.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.fooddairycompose.R

@Composable
fun EditTopAppBar(title: String, onEdit: () -> Unit, onBack: () -> Unit, onDelete: () -> Unit){
    TopAppBar(title = {Text(title)},
        actions = {
            IconButton(onClick = onEdit) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                    contentDescription = "edit"
                )
        }
            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                    contentDescription = "delete"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "back")
            }
        })
}