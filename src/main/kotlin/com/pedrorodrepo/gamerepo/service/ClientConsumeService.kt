package com.pedrorodrepo.gamerepo.service

import com.google.gson.Gson
import com.pedrorodrepo.gamerepo.model.InfoGame
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ClientConsumeService {

    fun searchGameV2(search: String,
             onSuccess: (InfoGame) -> Unit,
             onFailed: (Throwable) -> Unit) {
        try {
            val baseAPIUrl = "https://www.cheapshark.com/api/1.0/games?id="
            val fullUrl = "$baseAPIUrl$search"

            val client: HttpClient = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .build()

            val response = client
                .send(request, HttpResponse.BodyHandlers.ofString())

            val jsonBody = response.body()

            val gson = Gson()
            val data = gson.fromJson(jsonBody, InfoGame::class.java)

            onSuccess(data)
        } catch (e: Exception) {
            println("Could not find a game with this ID.")
            onFailed(e)
        }
    }

    @Deprecated("This method have no treatment to exception.",
        ReplaceWith("searchGameV2(search, onSuccess, onFailed)"))
    fun searchGame(search: String): InfoGame {
        val baseAPIUrl = "https://www.cheapshark.com/api/1.0/games?id="
        val fullUrl = "$baseAPIUrl$search"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(fullUrl))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val jsonBody = response.body()

        val gson = Gson()
        return gson.fromJson(jsonBody, InfoGame::class.java)
    }
}