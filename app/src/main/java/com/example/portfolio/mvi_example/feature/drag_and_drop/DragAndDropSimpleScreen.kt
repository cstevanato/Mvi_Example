package com.example.portfolio.mvi_example.feature.drag_and_drop

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.portfolio.mvi_example.ui.theme.Mvi_ExampleTheme

@Composable
fun DragAndDropSimpleScreen(modifier: Modifier = Modifier) {

    var textCopied by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Drag and Drop Simple Screen",
            modifier
                .padding(bottom = 50.dp)
                .dragAndDropSource(
                    { _: Offset ->
                        DragAndDropTransferData(
                            clipData = ClipData.newPlainText("text", "Drag me!")
                        )
                    }
                )
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray)
                .dragAndDropTarget(
                    shouldStartDragAndDrop = { event ->
                        event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                    },
                    target = remember {
                        object : DragAndDropTarget {
                            override fun onDrop(event: DragAndDropEvent): Boolean {
                                val draggedData =
                                    event.toAndroidDragEvent().clipData.getItemAt(0).text
                                println("Dragged data: $draggedData on the target!")
                                textCopied = draggedData.toString()
                                return true
                            }
                        }
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (textCopied.isNotEmpty())
                Text(textCopied)
        }
    }
}

@Preview
@Composable
fun DragAndDropSimpleScreenPreview() {
    Mvi_ExampleTheme {
        DragAndDropSimpleScreen()
    }
}