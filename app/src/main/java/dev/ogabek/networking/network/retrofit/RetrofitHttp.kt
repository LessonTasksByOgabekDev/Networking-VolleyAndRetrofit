package dev.ogabek.networking.network.retrofit

import dev.ogabek.networking.network.retrofit.services.PosterService
import dev.ogabek.networking.network.volley.VolleyHttp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    const val IS_TESTER = true
    const val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    const val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server(): String {
        return if (IS_TESTER) {
            SERVER_DEVELOPMENT
        } else {
            SERVER_PRODUCTION
        }
    }

    val posterService: PosterService = retrofit.create(PosterService::class.java)
    // Add services if necessary

}