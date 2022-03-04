package dev.ogabek.networking.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dev.ogabek.networking.model.Poster
import dev.ogabek.networking.R
import dev.ogabek.networking.adapter.Adapter
import dev.ogabek.networking.helper.Logger
import dev.ogabek.networking.model.PosterResp
import dev.ogabek.networking.network.retrofit.RetrofitHttp
import dev.ogabek.networking.network.volley.VolleyHandler
import dev.ogabek.networking.network.volley.VolleyHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rv_main: RecyclerView
    private val posts: ArrayList<Poster> = ArrayList()
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        get()

    }

    private fun initViews() {
        rv_main = findViewById(R.id.rv_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        loading = findViewById(R.id.loading)

    }

    private fun get() {
        loading.visibility = View.VISIBLE
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object: VolleyHandler {
            override fun onSuccess(response: String?) {
                loading.visibility = View.GONE
                Logger.d("VolleyHttp", response!!)
                val postArray = Gson().fromJson(response, Array<Poster>::class.java)

                posts.clear()
                posts.addAll(postArray)

                refreshAdapter(posts)

            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                loading.visibility = View.VISIBLE
            }

        })
    }

    private fun refreshAdapter(posters: ArrayList<Poster>) {
        val adapter = Adapter(this, posters)
        rv_main.adapter = adapter
    }

    private fun single() {
        VolleyHttp.get(VolleyHttp.API_SINGLE_POST + 1, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)

            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)

            }

        })
    }

    private fun post() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), object:
            VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)

            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)

            }

        })

    }

    private fun put() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object:
            VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)

            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)

            }

        })

    }

    private fun delete(poster: Poster) {
        VolleyHttp.del(VolleyHttp.API_UPDATE_POST + poster.id, object: VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                get()
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)

            }

        })

    }

    private fun listPost() {
        RetrofitHttp.posterService.listPost().enqueue(object: Callback<ArrayList<PosterResp>> {
            override fun onResponse(call: Call<ArrayList<PosterResp>>, response: Response<ArrayList<PosterResp>>) {
                Logger.d("ListPost", response.body().toString())

            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())

            }

        })
    }

    private fun singlePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.singlePost(poster.id).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())

            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())

            }

        })
    }

    private fun createPost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.createPost(poster).enqueue(object: Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())

            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())

            }
        })
    }

    private fun updatePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.updatePost(poster.id, poster).enqueue(object: Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())

            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())

            }

        })
    }

    private fun deletePost() {
        val poster = Poster(1, 1, "OgabekDev", "Mobile Developer")
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object: Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("ListPost", response.body().toString())

            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("ListPost", t.message.toString())

            }

        })
    }

    fun dialogPoster(poster: Poster) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setPositiveButton(
                "Yes"
            ) { _, _ -> delete(poster) }
            .setNegativeButton("No", null)
            .setIcon(R.drawable.ic_dialog_alert)
            .show()
    }

}