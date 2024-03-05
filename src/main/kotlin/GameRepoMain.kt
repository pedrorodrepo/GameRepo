import com.pedrorodrepo.gamerepo.model.Game
import com.pedrorodrepo.gamerepo.service.ClientConsumeService
import java.util.*

fun main() {

    val reader = Scanner(System.`in`)
    println("Enter the game ID: ")
    val search = reader.nextLine()

    val consumeService = ClientConsumeService()
    val infoGame = consumeService.searchGame(search)

    var myGame: Game? = null

    val result = runCatching {
        myGame = Game(
            infoGame.info.title,
            infoGame.info.thumb)
    }

    result.onSuccess {
        println("Do you want to insert a custom description? S/N")
        val option = reader.nextLine()

        if (option.equals("s", true)) {
            println("Insert your custom description to the game: ")
            val insertDescription = reader.nextLine()
            myGame?.description = insertDescription
        } else {
            myGame?.description = myGame?.title

            println("The description will be the title of the game")
        }

        println("\n${myGame.toString()}")
    }
    result.onFailure {
        println("Game not found, try another ID.")
    }
}