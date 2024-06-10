package dev.mysticmaster.tamngon247.ui.dialog.dish

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.feature.data.model.CategoryModel
import dev.mysticmaster.tamngon247.feature.data.model.DishModel
import dev.mysticmaster.tamngon247.feature.data.request.DishRequest
import dev.mysticmaster.tamngon247.ui.components.DropdownMenuBox
import dev.mysticmaster.tamngon247.util.ExtraImage
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel
import dev.mysticmaster.tamngon247.viewmodel.DishViewModel

@Composable
fun DishUpdateDialog(
    dishModel: DishModel,
    dishViewModel: DishViewModel,
    categoryViewModel: CategoryViewModel,
    onDismiss: () -> Unit
) {

    val categoryState = categoryViewModel.categories.observeAsState(initial = emptyList())
    Log.d("TAG", "CategoryInDishManagerScreen: ${categoryState.value}")

    val categories = categoryState.value

    var dishName by remember { mutableStateOf(dishModel.dishName) }
    var idCategory by remember { mutableStateOf(dishModel.idCategory) }
    var dishPrice by remember { mutableStateOf(dishModel.dishPrice) }

    val selectedCategory = remember {
        mutableStateOf<CategoryModel?>(categories.firstOrNull { it.id == idCategory })
    }

    var context = LocalContext.current
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
                    text = "Cập nhật món ăn",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier.size(160.dp)) {
                    if (imageUri.value == null) {
                        AsyncImage(
                            model = if (!dishModel.imageUrl.isNullOrEmpty()) dishModel.imageUrl else ExtraImage,
                            contentDescription = "Ảnh món ăn",
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
                            contentDescription = "Cập nhật ảnh món ăn",
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

                Spacer(modifier = Modifier.height(10.dp))
                DropdownMenuBox(categories, selectedCategory)
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = dishPrice.toString(),
                    onValueChange = {
                        try {
                            dishPrice = it.toInt()
                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                context,
                                "Sai định dạng giá món ăn",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Nhập giá món ăn")
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = dishName,
                    onValueChange = { dishName = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    ),
                    placeholder = {
                        Text(text = "Nhập tên món ăn")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (dishName.length < 4) {
                                Toast.makeText(
                                    context,
                                    "Món ăn không được để trống và phải có trên 4 ký tự",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@Button
                            }

                            if (dishPrice <= 0) {
                                Toast.makeText(
                                    context,
                                    "Giá món ăn phải lớn hơn 0",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@Button
                            }

                            val dishRequest =
                                DishRequest(
                                    id = dishModel.id,
                                    dishName = dishName,
                                    idCategory = selectedCategory.value!!.id,
                                    imagePath = dishModel.imagePath,
                                    imageUrl = dishModel.imageUrl,
                                    dishPrice = dishPrice,
                                    status = dishModel.status
                                )

                            Log.e("TAG", "updateDish: ${dishRequest}")

                            if (bitmap.value != null) {
                                dishViewModel.updateDish(dishRequest,bitmap.value)
                            } else {
                                dishViewModel.updateDish(dishRequest,null)
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