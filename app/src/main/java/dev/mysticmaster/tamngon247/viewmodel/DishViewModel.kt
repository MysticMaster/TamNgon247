package dev.mysticmaster.tamngon247.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.model.DishModel
import dev.mysticmaster.tamngon247.feature.data.request.CategoryRequest
import dev.mysticmaster.tamngon247.feature.data.request.DishRequest
import dev.mysticmaster.tamngon247.util.bitmapToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class DishViewModel(private val context: Context) : ViewModel() {
    private val _dishes = MutableLiveData<List<DishModel>>()
    val dishes: LiveData<List<DishModel>> = _dishes

    private val _addMessage = MutableLiveData<String?>()
    val addMessage : LiveData<String?> = _addMessage

    private val _updateMessage = MutableLiveData<String?>()
    val updateMessage : LiveData<String?> = _updateMessage

    private val _deleteMessage = MutableLiveData<String?>()
    val deleteMessage : LiveData<String?> = _deleteMessage

    init {
        getAllDish()
    }

    private fun getAllDish() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().dishService.getAllDish()
                Log.d("TAG", "getAllDish: $response ")

                if (response.code() == 200) {
                    _dishes.postValue(response.body()?.map { it.toDishModel() })
                } else _dishes.postValue(emptyList())
            } catch (e: Exception) {
                Log.e("TAG", "getAllDish: " + e.message)
                _dishes.postValue(emptyList())
            }
        }
    }

    fun addDish(dishRequest: DishRequest, bitmap: Bitmap?) {
        try {
            viewModelScope.launch {
                Log.d("SUCCESS", "newDish: ${dishRequest}")

                val dishName: RequestBody =
                    dishRequest.dishName.toRequestBody("text/plain".toMediaTypeOrNull())
                val idCategory: RequestBody =
                    dishRequest.idCategory.toRequestBody("text/plain".toMediaTypeOrNull())
                val dishPrice: RequestBody =
                    dishRequest.dishPrice.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val status: RequestBody =
                    dishRequest.status.toString().toRequestBody("text/plain".toMediaTypeOrNull())

                val partMap = hashMapOf(
                    "dish_name" to dishName,
                    "_id_category" to idCategory,
                    "dish_price" to dishPrice,
                    "status" to status
                )

                var filePart: MultipartBody.Part? = null
                if (bitmap != null) {
                    val file = bitmapToFile(bitmap, context)
                    val fileReqBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    filePart = MultipartBody.Part.createFormData("filename", file.name, fileReqBody)
                }

                val response = RetrofitConfig().dishService.addDish(filePart, partMap)

                if (response.code() == 201) {
                 _addMessage.postValue("Thêm mới món ăn thành công")
                    getAllDish()
                } else {
                    _addMessage.postValue("Thêm mới món ăn thất bại")
                }
            }
        } catch (e: Exception) {
            _addMessage.postValue("Đã xảy ra sự cố với máy chủ")
            Log.e("ERROR", "addDish: " + e.message)
        }
    }

    fun updateDish(dishRequest: DishRequest, bitmap: Bitmap?) {
        try {
            viewModelScope.launch {

                val dishName: RequestBody =
                    dishRequest.dishName.toRequestBody("text/plain".toMediaTypeOrNull())
                val idCategory: RequestBody =
                    dishRequest.idCategory.toRequestBody("text/plain".toMediaTypeOrNull())
                val imagePath: RequestBody =
                    dishRequest.imagePath!!.toRequestBody("text/plain".toMediaTypeOrNull())
                val imageUrl: RequestBody =
                    dishRequest.imageUrl!!.toRequestBody("text/plain".toMediaTypeOrNull())
                val dishPrice: RequestBody =
                    dishRequest.dishPrice.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val status: RequestBody =
                    dishRequest.status.toString().toRequestBody("text/plain".toMediaTypeOrNull())

                Log.d("UpDateDishRequest", "updateDish: " + dishRequest)

                val partMap = hashMapOf(
                    "dish_name" to dishName,
                    "_id_category" to idCategory,
                    "image_path" to imagePath,
                    "image_url" to imageUrl,
                    "dish_price" to dishPrice,
                    "status" to status
                )

                var filePart: MultipartBody.Part? = null
                if (bitmap != null) {
                    val file = bitmapToFile(bitmap, context)
                    val fileReqBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    filePart = MultipartBody.Part.createFormData("filename", file.name, fileReqBody)
                }

                val response = RetrofitConfig().dishService.updateDish(
                    dishRequest.id.toString(),
                    filePart,
                    partMap
                )

                if (response.code() == 200) {
                    _updateMessage.postValue("Cập nhật món ăn thành công")
                    getAllDish()
                } else {
                    _updateMessage.postValue("Cập nhật món ăn thất bại")
                }
            }
        } catch (e: Exception) {
            _updateMessage.postValue("Đã xảy ra sự cố với máy chủ")
            Log.e("ERROR", "updateDish: " + e.message)
        }
    }

    fun deleteDish(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().dishService.deleteDish(id)
                if (response.code() == 200) {
                    getAllDish()
                    _deleteMessage.postValue("Đã xóa món ăn này")
                } else if (response.code() == 400) {
                    _deleteMessage.postValue("Món ăn này tồn tại đơn hàng liên quan")
                } else {
                    Log.d("ERROR", "Failed to delete dish")
                }
            } catch (e: Exception) {
                _deleteMessage.postValue("Đã xảy ra sự cố với máy chủ")
                Log.e("ERROR", "deleteDish: ${e.message}")
            }
        }
    }

    fun resetAddMessage() {
        _addMessage.postValue(null)
    }
    fun resetUpdateMessage() {
        _updateMessage.postValue(null)
    }
    fun resetDeleteMessage() {
        _deleteMessage.postValue(null)
    }

}