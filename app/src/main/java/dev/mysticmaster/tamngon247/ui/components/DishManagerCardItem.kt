package dev.mysticmaster.tamngon247.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import dev.mysticmaster.tamngon247.feature.data.mapper.DishItemMapper
import dev.mysticmaster.tamngon247.ui.theme.ItemColor
import dev.mysticmaster.tamngon247.viewmodel.DishViewModel

@Composable
fun DishManagerCardItem(
    dishViewModel: DishViewModel,
    index: Int,
    dishItemMapper: DishItemMapper
) {
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogUpdate by remember { mutableStateOf(false) }

//    if (showDialogDelete) {
//        CategoryDeleteDialog(
//            onConfirmation = {
//                categoryViewModel.deleteCategory(categoryItem.id)
//                showDialogDelete = false
//            },
//            onDismiss = { showDialogDelete = false }
//        )
//    }
//
//    if (showDialogUpdate) {
//        CategoryUpdateDialog(
//            categoryItem = categoryItem,
//            categoryViewModel = categoryViewModel,
//            onDismiss = { showDialogUpdate = false }
//        )
//    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = ItemColor)
            .padding(12.dp)
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${index + 1}.", fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(end = 15.dp)
        )

        AsyncImage(
            model = if (!dishItemMapper.url.isNullOrEmpty()) dishItemMapper.url
            else "https://firebasestorage.googleapis.com/v0/b/tamngon247.appspot.com/o/categories%2Flogotamngon.png?alt=media&token=a8ac489c-e0c0-4df4-a728-30de494c10ed",
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .wrapContentHeight()
                .size(45.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                modifier = Modifier.fillMaxWidth(0.75f),
                text = dishItemMapper.categoryName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.75f),
                text = dishItemMapper.dishName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.75f),
                text = dishItemMapper.dishPrice.toString() + "đ",
                fontSize = 13.sp,
                color = Color.Red
            )
        }

        IconButton(onClick = { showDialogUpdate = true }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "", tint = Color.White)
        }
        IconButton(onClick = { showDialogDelete = true }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "", tint = Color.White)
        }
    }
}