package dev.mysticmaster.tamngon247.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.model.CategoryItem
import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryItem>>()
    val categories: LiveData<List<CategoryItem>> = _categories

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().categoryService.getAllCategory()
                Log.d("TAG", "getAllCategory: $response ")

                if (response.isSuccessful) {
                    _categories.postValue(response.body()?.map { it.toCategoryItem() })
                } else _categories.postValue(emptyList())
            } catch (e: Exception) {
                Log.e("TAG", "getAllCategory: " + e.message)
                _categories.postValue(emptyList())
            }
        }
    }

    private fun getCategoryById(id: String?): LiveData<CategoryItem?> {
        val liveData = MutableLiveData<CategoryItem?>()
        id?.let {
            viewModelScope.launch {
                val response = RetrofitConfig().categoryService.getCategoryById(id)
                Log.d("TAG", "getCategoryById: $response ")
                try {
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()?.toCategoryItem())
                    } else liveData.postValue(null)
                } catch (e: Exception) {
                    Log.e("TAG", "getCategoryById: " + e.message)
                    liveData.postValue(null)
                }
            }
        }
        return liveData
    }

    fun addCategory(categoryItemRequest: CategoryItemRequest) {
        try {
            viewModelScope.launch {
                categoryItemRequest.id = null
                Log.e("TAG", "newCategoryViewModel: ${categoryItemRequest}")
                val response = RetrofitConfig().categoryService.addCategory(categoryItemRequest)
                Log.d("TAG", "addCategory: $response ")
                if (response.isSuccessful) {
                    getAllCategory()
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "addCategory: " + e.message)
        }
    }

    fun updateCategory(categoryItemRequest: CategoryItemRequest) {
        try {
            viewModelScope.launch {
                Log.d("TAG", "updateCategory: " + categoryItemRequest.id.toString())
                val response = RetrofitConfig().categoryService.updateCategory(
                    categoryItemRequest.id.toString(),
                    categoryItemRequest
                )
                if (response.isSuccessful) {
                    getAllCategory()
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "updateCategory: " + e.message)
        }
    }

    fun deleteCategory(id: String) {
        viewModelScope.launch {
            try {
                val checkDish = RetrofitConfig().dishService.getDishesByIdCategory(id)
                if (checkDish.isSuccessful && checkDish.body() != null && !checkDish.body()!!
                        .isEmpty()
                ) {
                    Log.d("TAG", "Loại món ăn có phụ thuộc, không thể xóa")
                } else {
                    val response = RetrofitConfig().categoryService.deleteCategory(id)
                    Log.d("TAG", "deleteCategory: $response")
                    if (response.isSuccessful) {
                        getAllCategory()
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "deleteCategory: " + e.message)
            }
        }
    }

}