package dev.mysticmaster.tamngon247.ui.dialog.dish

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest
import dev.mysticmaster.tamngon247.feature.data.request.DishItemRequest
import dev.mysticmaster.tamngon247.ui.components.DropdownMenuBox
import dev.mysticmaster.tamngon247.viewmodel.DishViewModel

@Composable
fun DishAddDialog(
    dishViewModel: DishViewModel,
    onDismiss: () -> Unit
) {
    var dishName by remember { mutableStateOf("") }
    var dishPrice by remember { mutableStateOf(0) }
    var selectedMainCourse by remember { mutableStateOf("Món chính") }
    val mainCourseOptions = listOf("Món chính", "Món phụ", "Topping")
    var context = LocalContext.current

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
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Thêm món ăn",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))
                DropdownMenuBox(selectedMainCourse, mainCourseOptions) { selectedMainCourse = it }
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = dishPrice.toString(),
                    onValueChange = {
                        try {
                            dishPrice = it.toInt()
                        }catch (e : NumberFormatException){
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
                    horizontalArrangement = Arrangement.Center
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

                            if (dishPrice <= 0){
                                Toast.makeText(
                                    context,
                                    "Giá món ăn phải lớn hơn 0",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@Button
                            }

                            val dishItemRequest =
                                DishItemRequest(
                                    dishName,
                                   idCategory =  "66608809ff4c95b44ec21056",
                                   idImage =  "chuaco",
                                    dishPrice = dishPrice,
                                    true
                                )
                            Log.e("TAG", "newDish: ${dishItemRequest}")
                            dishViewModel.addDish(dishItemRequest)
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