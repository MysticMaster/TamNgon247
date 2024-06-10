package dev.mysticmaster.tamngon247.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.model.CategoryModel
import dev.mysticmaster.tamngon247.feature.data.request.CategoryRequest
import dev.mysticmaster.tamngon247.util.bitmapToFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class CategoryViewModel(private val context: Context) : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _addMessage = MutableLiveData<String?>()
    val addMessage : LiveData<String?> = _addMessage

    private val _updateMessage = MutableLiveData<String?>()
    val updateMessage : LiveData<String?> = _updateMessage

    private val _deleteMessage = MutableLiveData<String?>()
    val deleteMessage : LiveData<String?> = _deleteMessage

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().categoryService.getAllCategory()
                Log.d("TAG", "getAllCategory: $response ")

                if (response.code() == 200) {

                    _categories.postValue(response.body()?.map { it.toCategoryModel() })
                } else _categories.postValue(emptyList())
            } catch (e: Exception) {
                Log.e("TAG", "getAllCategory: " + e.message)
                _categories.postValue(emptyList())
            }
        }
    }

    fun getCategoryById(id: String?): Flow<CategoryModel?> = flow {
        id?.let {
            val response = RetrofitConfig().categoryService.getCategoryById(it)
            Log.d("TAG", "getCategoryById: $response ")

            try {
                if (response.code() == 200) {
                    emit(response.body()?.toCategoryModel())
                } else emit(null)
            } catch (e: Exception) {
                Log.e("TAG", "getCategoryById: " + e.message)
                emit(null)
            }
        }
    }

    fun addCategory(categoryRequest: CategoryRequest, bitmap: Bitmap?) {
        try {
            viewModelScope.launch {
                Log.d("SUCCESS", "newCategory: ${categoryRequest}")

                val categoryName: RequestBody =
                    categoryRequest.categoryName.toRequestBody("text/plain".toMediaTypeOrNull())
                val status: RequestBody = categoryRequest.status.toString()
                    .toRequestBody("text/plain".toMediaTypeOrNull())

                val partMap = hashMapOf(
                    "category_name" to categoryName,
                    "status" to status
                )

                var filePart: MultipartBody.Part? = null
                if (bitmap != null) {
                    val file = bitmapToFile(bitmap, context)
                    val fileReqBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    filePart = MultipartBody.Part.createFormData("filename", file.name, fileReqBody)
                }

                val response = RetrofitConfig().categoryService.addCategory(filePart, partMap)

                if (response.code() == 201) {
                    _addMessage.postValue("Thêm mới danh mục thành công")
                    getAllCategory()
                } else {
                    _addMessage.postValue("Thêm mới danh mục thất bại")
                }
            }
        } catch (e: Exception) {
            _addMessage.postValue("Đã xảy ra sự cố với máy chủ")
            Log.e("ERROR", "addCategory: " + e.message)
        }
    }

    fun updateCategory(categoryRequest: CategoryRequest, bitmap: Bitmap?) {
        try {
            viewModelScope.launch {
                val categoryName: RequestBody =
                    categoryRequest.categoryName.toRequestBody("text/plain".toMediaTypeOrNull())
                val imagePath: RequestBody =
                    categoryRequest.imagePath!!.toRequestBody("text/plain".toMediaTypeOrNull())
                val imageUrl: RequestBody =
                    categoryRequest.imageUrl!!.toRequestBody("text/plain".toMediaTypeOrNull())
                val status: RequestBody = categoryRequest.status.toString()
                    .toRequestBody("text/plain".toMediaTypeOrNull())

                val partMap = hashMapOf(
                    "category_name" to categoryName,
                    "image_path" to imagePath,
                    "image_url" to imageUrl,
                    "status" to status
                )

                var filePart: MultipartBody.Part? = null
                if (bitmap != null) {
                    val file = bitmapToFile(bitmap, context)
                    val fileReqBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    filePart = MultipartBody.Part.createFormData("filename", file.name, fileReqBody)
                }

                val response = RetrofitConfig().categoryService.updateCategory(
                    categoryRequest.id.toString(),
                    filePart,
                    partMap
                )

                if (response.code() == 200) {
                    _updateMessage.postValue("Cập nhật danh mục thành công")
                    getAllCategory()
                } else {
                    _updateMessage.postValue("Cập nhật danh mục thất bại")
                }
            }
        } catch (e: Exception) {
            _updateMessage.postValue("Đã xảy ra sự cố với máy chủ")
            Log.e("ERROR", "updateCategory: " + e.message)
        }
    }

    fun deleteCategory(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().categoryService.deleteCategory(id)
                if (response.code() == 200) {
                    getAllCategory()
                    _deleteMessage.postValue("Đã xóa danh mục này")
                } else if (response.code() == 400) {
                    _deleteMessage.postValue("Danh mục này tồn tại sản phẩm liên quan")
                } else {
                    Log.d("ERROR", "Failed to delete category")
                }
            } catch (e: Exception) {
                _deleteMessage.postValue("Đã xảy ra sự cố với máy chủ")
                Log.e("ERROR", "deleteCategory: ${e.message}")
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