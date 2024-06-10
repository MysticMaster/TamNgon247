package dev.mysticmaster.tamngon247.ui.dialog.category

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeStream
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.feature.data.request.CategoryRequest
import dev.mysticmaster.tamngon247.util.uriToBitmap
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel

@Composable
fun CategoryAddDialog(
    categoryViewModel: CategoryViewModel,
    onDismiss: () -> Unit
) {
    var context = LocalContext.current
    var categoryName by remember { mutableStateOf("") }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri.value = uri
            bitmap.value =
                uri?.let { context.contentResolver.openInputStream(it)?.use(::decodeStream) }
        }
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = imageUri.value)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.logotamngon)
            }).build()
    )

    val categoryAddResponse by categoryViewModel.categoryAddResponse.observeAsState()

    LaunchedEffect(categoryAddResponse) {
        categoryAddResponse?.let { response ->
            Toast.makeText(context, response, Toast.LENGTH_LONG).show()
            categoryViewModel.resetCategoryAddResponse() // Optional: Reset LiveData to prevent showing the toast multiple times
        }
    }

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
                    text = "Thêm loại món ăn",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = if (imageUri.value != null) painter else painterResource(id = R.drawable.add_image),
                    contentDescription = "Thêm ảnh danh mục",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable {
                            launcher.launch("image/*")
                        }
                )

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

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 80.dp),
                    horizontalArrangement = Arrangement.Center
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
                                "",
                                categoryName = categoryName,
                                "",
                                "",
                                status = true
                            )

                            Log.e("TAG", "newCategory: ${categoryRequest}")
                            if (bitmap.value != null) {
                                categoryViewModel.addCategory(categoryRequest, bitmap.value)
                            } else {
                                categoryViewModel.addCategory(categoryRequest, null)
                            }
                            onDismiss()


                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        )
                    ) {
                        Text("THÊM")
                    }
                }

            }
        }
    }
}