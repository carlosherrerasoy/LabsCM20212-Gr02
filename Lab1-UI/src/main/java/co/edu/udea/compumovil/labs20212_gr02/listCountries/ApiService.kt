package co.edu.udea.compumovil.labs20212_gr02.listCountries

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

 @GET("/")
 fun getAllCountries(): Call<List<Countries>>

 @GET("posts/{id}")
 fun getPostById(@Path("id") id: Int): Call<Countries>

 @POST("posts/{id}")
 fun editPostById(@Path("id") id: Int, @Body post: Countries?): Call<Countries>
}