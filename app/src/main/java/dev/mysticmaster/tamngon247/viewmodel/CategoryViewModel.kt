package dev.mysticmaster.tamngon247.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.mapper.CategoryItemMapper
import dev.mysticmaster.tamngon247.feature.data.model.CategoryItem
import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryItemMapper>>()
    val categories: LiveData<List<CategoryItemMapper>> = _categories

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().categoryService.getAllCategory()
                Log.d("TAG", "getAllCategory: $response ")

                if (response.isSuccessful) {
                    val list = mutableListOf<CategoryItemMapper>()

                    response.body()?.map {
                        it

                        val categoryItemMapper = CategoryItemMapper(
                            it.categoryName,
                            "",
                            "",
                            "",
                            it.status,
                            it.id
                        )

                        val response1 = RetrofitConfig().imageService.getImageById(it.idImage)
                        if (response1.isSuccessful) {
                            response1.body()?.let { it1 ->
                                categoryItemMapper.idImage = it1.id
                                categoryItemMapper.path = it1.path
                                categoryItemMapper.url = it1.url
                            }
                        }
                        list.add(categoryItemMapper)
                    }
                    _categories.postValue(list)
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