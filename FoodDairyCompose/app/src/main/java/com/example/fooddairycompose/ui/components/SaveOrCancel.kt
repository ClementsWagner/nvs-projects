package com.example.fooddairycompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddairycompose.R

@Composable
fun SaveOrCancel(onSave: () -> Unit, onCancel: () -> Unit){
    Row(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 40.dp)){
        Box(modifier = Modifier.padding(end = 30.dp)){
            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
        }
        Box() {
            Button(onClick = onSave) {
                Text(text = "Save")
            }
        }
    }
}