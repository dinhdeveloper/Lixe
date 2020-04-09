package com.dinh.service
import com.main.model.Film
import com.main.model.Song
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface APIService {
//    @get:GET("category/list")
//    val allCategory: Call<List<Category>>
//
//    @GET("uis")
//    fun getUser(@Query("id") id: String): Call<Account>

//    @GET("product/cate=")
//    fun getProductcById(@Query("id") id: Int): Call<List<Product>>

    @GET("song/list")
    fun getAllSong(): Call<List<Song>>

    @GET("film/list")
    fun getAllFilm(): Call<List<Film>>

    //    @POST("product/list")
    //    Call<ArrayList<Product>> getProductLists(@Body PagingProduct pagingProduct);
//    @GET("product/cate={id}")
//    fun getProductById(@Path("id") id: Int): Call<ArrayList<Product>>
}