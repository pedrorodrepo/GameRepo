package com.pedrorodrepo.gamerepo.service

import com.google.gson.Gson
import com.pedrorodrepo.gamerepo.model.InfoGame
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ClientConsumeService {

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