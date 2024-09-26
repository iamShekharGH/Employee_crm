package com.shekharhandigol.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shekharhandigol.theme.BothPreviews

@Composable
fun MyButton(text:String){
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        onClick = {}) {
        Text(text = text)
    }
}

@BothPreviews
@Composable
fun PreviewMyButton(){
    MyButton("Click Here")
}