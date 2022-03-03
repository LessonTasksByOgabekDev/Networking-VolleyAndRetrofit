package dev.ogabek.networking.network.retrofit.services

import dev.ogabek.networking.model.Poster
import dev.ogabek.networking.model.PosterResp
import retrofit2.Call
import retrofit2.http.*

interface PosterService {

    @Headers("Content-type:application/json; charset=UTF-8")

    @GET("posts")
    fun listPost(): Call<ArrayList<PosterResp>>

    @GET("posts/{id}")
    fun singlePost(@Path("id") id: Int): Call<PosterResp>

    @POST("posts")
    fun createPost(@Body poster: Poster): Call<PosterResp>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int, @Body poster: Poster): Call<PosterResp>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int,): Call<PosterResp>

}
