package dev.mysticmaster.tamngon247.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.mapper.DishItemMapper
import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest
import dev.mysticmaster.tamngon247.feature.data.request.DishItemRequest
import kotlinx.coroutines.launch

class DishViewModel : ViewModel() {
    private val _dishes = MutableLiveData<List<DishItemMapper>>()
    val dishes: LiveData<List<DishItemMapper>> = _dishes

    init {
        getAllDish()
    }

    private fun getAllDish() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfig().dishService.getAllDish()
                Log.d("TAG", "getAllDish: $response ")

                if (response.isSuccessful) {
                    val list = mutableListOf<DishItemMapper>()

                    response.body()?.map {
                        it

                        val dishItemMapper = DishItemMapper(
                            it.dishName,
                            "",
                            "",
                            "",
                            "",
                            it.dishPrice,
                            it.status,
                            it.id
                        )

                        val response1 = RetrofitConfig().imageService.getImageById(it.idImage)
                        if (response1.isSuccessful) {
                            response1.body()?.let { it1 ->
                                dishItemMapper.idImage = it1.id
                                dishItemMapper.path = it1.path
                                dishItemMapper.url = it1.url
                            }
                        }

                        val response2 =
                            RetrofitConfig().categoryService.getCategoryById(it.idCategory)
                        if (response2.isSuccessful) {
                            response2.body()?.let { it2 ->
                                dishItemMapper.categoryName = it2.categoryName
                            }
                        }

                        list.add(dishItemMapper)
                    }
                    _dishes.postValue(list)
                } else _dishes.postValue(emptyList())
            } catch (e: Exception) {
                Log.e("TAG", "getAllDish: " + e.message)
                _dishes.postValue(emptyList())
            }
        }
    }

    fun addDish(dishItemRequest: DishItemRequest) {
        try {
            viewModelScope.launch {
                dishItemRequest.id = null
                Log.e("TAG", "newDishViewModel: ${dishItemRequest}")
                val response = RetrofitConfig().dishService.addDish(dishItemRequest)
                Log.d("TAG", "addDish: $response ")
                if (response.isSuccessful) {
                    getAllDish()
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "addDish: " + e.message)
        }
    }
}