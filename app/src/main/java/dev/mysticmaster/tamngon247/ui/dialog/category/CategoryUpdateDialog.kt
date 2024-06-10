package dev.mysticmaster.tamngon247.ui.dialog.category

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.feature.data.model.CategoryModel
import dev.mysticmaster.tamngon247.feature.data.request.CategoryRequest
import dev.mysticmaster.tamngon247.util.ExtraImage
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel

@Composable
fun CategoryUpdateDialog(
    categoryModel: CategoryModel,
    categoryViewModel: CategoryViewModel,
    onDismiss: () -> Unit
) {
    var context = LocalContext.current
    var categoryName by remember { mutableStateOf(categoryModel.categoryName) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri.value = uri
            bitmap.value =
                uri?.let {
                    context.contentResolver.openInputStream(it)?.use(BitmapFactory::decodeStream)
                }
        }
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = imageUri.value)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.logotamngon)
            }).build()
    )

    Dialog(onDismissRequest = onDismiss) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Cập nhật loại món ăn",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier.size(160.dp)) {
                    if (imageUri.value == null) {
                        AsyncImage(
                            model = if (!categoryModel.imageUrl.isNullOrEmpty()) categoryModel.imageUrl else ExtraImage,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .wrapContentHeight()
                                .size(130.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                        )
                    }

                    if (imageUri.value != null) {
                        Image(
                            painter = painter,
                            contentDescription = "Cập nhật ảnh danh mục",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .size(130.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.update_image),
                        contentDescription = "Cập nhật button",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .align(alignment = Alignment.BottomEnd)
                            .clickable {
                                launcher.launch("image/*")
                            }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    ),
                    placeholder = {
                        Text(text = "Nhập tên loại món ăn")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (categoryName.length < 4) {
                                Toast.makeText(
                                    context,
                                    "Loại món ăn không được để trống và phải có trên 4 ký tự",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@Button
                            }

                            val categoryRequest = CategoryRequest(
                                id = categoryModel.id,
                                categoryName = categoryName,
                                imagePath = categoryModel.imagePath,
                                imageUrl = categoryModel.imageUrl,
                                status = categoryModel.status
                            )

                            Log.e("TAG", "updateCategory: ${categoryRequest}")
                            if (bitmap.value != null) {
                                categoryViewModel.updateCategory(categoryRequest, bitmap.value)
                            } else {
                                categoryViewModel.updateCategory(categoryRequest, null)
                            }
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Lưu")
                    }

                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Hủy")
                    }
                }

            }
        }
    }
}