package dev.mysticmaster.tamngon247.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.mysticmaster.tamngon247.feature.data.model.CategoryModel
import dev.mysticmaster.tamngon247.ui.dialog.category.CategoryDeleteDialog
import dev.mysticmaster.tamngon247.ui.dialog.category.CategoryUpdateDialog
import dev.mysticmaster.tamngon247.ui.theme.ItemColor
import dev.mysticmaster.tamngon247.util.ExtraImage
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel

@Composable
fun CategoryManagerCardItem(
    categoryViewModel: CategoryViewModel,
    index: Int,
    categoryModel: CategoryModel
) {
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogUpdate by remember { mutableStateOf(false) }



    if (showDialogDelete) {
        CategoryDeleteDialog(
            onConfirmation = {
                categoryViewModel.deleteCategory(categoryModel.id)
                showDialogDelete = false
            },
            onDismiss = { showDialogDelete = false }
        )
    }

    if (showDialogUpdate) {
        CategoryUpdateDialog(
            categoryModel = categoryModel,
            categoryViewModel = categoryViewModel,
            onDismiss = { showDialogUpdate = false }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = ItemColor)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${index + 1}.", fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp)
            )

            AsyncImage(
                model = if (!categoryModel.imageUrl.isNullOrEmpty()) categoryModel.imageUrl else ExtraImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .wrapContentHeight()
                    .size(45.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = categoryModel.categoryName,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { showDialogUpdate = true }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Update",
                    tint = Color.White,
                )
            }

            IconButton(onClick = { showDialogDelete = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                )
            }
        }
    }
}