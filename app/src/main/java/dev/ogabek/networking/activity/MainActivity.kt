package dev.ogabek.networking.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dev.ogabek.networking.model.Poster
import dev.ogabek.networking.R
import dev.ogabek.networking.helper.Logger
import dev.ogabek.networking.model.PosterResp
import dev.ogabek.networking.network.retrofit.RetrofitHttp
import dev.ogabek.networking.network.volley.VolleyHandler
import dev.ogabek.networking.network.volley.VolleyHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tv_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        tv_text = findViewById(R.id.tv_text)
        singlePost()
    }

    private fun get() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object: VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                tv_text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                tv_text.text = error
            }

        })
    }
    private fun single() {
        VolleyHttp.get(VolleyHttp.API_SINGLE_POST + 1, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                tv_text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                tv_text.text = error
            }

        })
    }
    private fun post() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), object:
            VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                tv_text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                tv_text.text = error
            }

        })

    }
    private fun put() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object:
            VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                tv_text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                tv_text.text = error
            }

        })

    }
    private fun delete() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        VolleyHttp.del(VolleyHttp.API_UPDATE_POST + poster.id, object: VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                tv_text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                tv_text.text = error
            }

        })

    }


    private fun listPost() {
        RetrofitHttp.posterService.listPost().enqueue(object: Callback<ArrayList<PosterResp>> {
            override fun onResponse(call: Call<ArrayList<PosterResp>>, response: Response<ArrayList<PosterResp>>) {
                Logger.d("ListPost", response.body().toString())
                tv_text.text = response.body().toString()
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())
                tv_text.text = t.message.toString()
            }

        })
    }

    private fun singlePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.singlePost(poster.id).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())
                tv_text.text = response.body().toString()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())
                tv_text.text = t.message.toString()
            }

        })
    }

    private fun createPost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.createPost(poster).enqueue(object: Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())
                tv_text.text = response.body().toString()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())
                tv_text.text = t.message.toString()
            }
        })
    }

    private fun updatePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.updatePost(poster.id, poster).enqueue(object: Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())
                tv_text.text = response.body().toString()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())
                tv_text.text = t.message.toString()
            }

        })
    }

    private fun deletePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object: Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())
                tv_text.text = response.body().toString()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())
                tv_text.text = t.message.toString()
            }

        })
    }

}