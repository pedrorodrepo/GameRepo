import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

fun main() {
    val gson = Gson()

    val reader = Scanner(System.`in`)
    println("Enter the game ID: ")
    val search = reader.nextLine()

    val baseAPIUrl = "https://www.cheapshark.com/api/1.0/games?id="
    val fullUrl = "$baseAPIUrl$search"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(fullUrl))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())

    val jsonBody = response.body()

//    try {
//        val json = response.body()
//
//        val myInfoGame = gson.fromJson(json, InfoGame::class.java)
//        val myGame = Game(myInfoGame.info.title, myInfoGame.info.thumb)
//
//        println(myGame.toString())
//
//    } catch (ex: Exception) {
//        println("Game not found, try another ID.")
//    }

    val result = runCatching {
        val myInfoGame = gson.fromJson(jsonBody, InfoGame::class.java)
        val myGame = Game(myInfoGame.info.title, myInfoGame.info.thumb)
        println(myGame.toString())
    }

    result.onFailure {
        println("Game not found, try another ID.")
    }

    result.onSuccess {
        println("Do you want to insert a custom description? S/N")
        val option = reader.nextLine()

        if (option.equals("s", true)) {
            println("Insert your custom description to the game: ")
            val description = reader.nextLine()
        } else {
            println("The description will be the title of the game")

        }
    }
}