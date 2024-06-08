package dev.mysticmaster.tamngon247.feature.config

import mystic.master.tamngon247.feature.data.remote.service.AdminService
import mystic.master.tamngon247.feature.data.remote.service.CategoryService
import mystic.master.tamngon247.feature.data.remote.service.CustomerService
import mystic.master.tamngon247.feature.data.remote.service.DishService
import mystic.master.tamngon247.feature.data.remote.service.ImageService
import mystic.master.tamngon247.feature.data.remote.service.OrderItemService
import mystic.master.tamngon247.feature.data.remote.service.OrderService
import mystic.master.tamngon247.feature.data.remote.service.StorageImageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitConfig {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val adminService: AdminService by lazy {
        retrofit.create(AdminService::class.java)
    }

    val customerService: CustomerService by lazy {
        retrofit.create(CustomerService::class.java)
    }

    val categoryService: CategoryService by lazy {
        retrofit.create(CategoryService::class.java)
    }

    val dishService: DishService by lazy {
        retrofit.create(DishService::class.java)
    }

    val imageService: ImageService by lazy {
        retrofit.create(ImageService::class.java)
    }

    val orderService: OrderService by lazy {
        retrofit.create(OrderService::class.java)
    }

    val orderItemService: OrderItemService by lazy {
        retrofit.create(OrderItemService::class.java)
    }

    val storageImageService: StorageImageService by lazy {
        retrofit.create(StorageImageService::class.java)
    }
}