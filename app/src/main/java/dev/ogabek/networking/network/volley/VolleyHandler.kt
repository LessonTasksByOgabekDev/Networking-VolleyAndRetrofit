package dev.ogabek.networking.network.volley

interface VolleyHandler {
    fun onSuccess(response: String?)
    fun onError(error: String?)
}