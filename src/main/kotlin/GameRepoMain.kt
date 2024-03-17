import com.pedrorodrepo.gamerepo.model.Game
import com.pedrorodrepo.gamerepo.model.InfoGame
import com.pedrorodrepo.gamerepo.model.User
import com.pedrorodrepo.gamerepo.service.ClientConsumeService
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val user = User.createUser(reader)
    println("User register successfully!")
    println(user)

    do  {
        println("Enter the game ID: ")
        val search = reader.nextLine()

        val consumeService = ClientConsumeService()
        var myGame: Game?

        consumeService.searchGameV2(
            search = search,
            onSuccess = { data ->
                myGame = Game(
                    data.info.title,
                    data.info.thumb
                )

                println(message = "Do you want to insert a custom description? S/N")
                val option = reader.nextLine()

                if (option.equals("s", true)) {
                    println("Insert your custom description to the game: ")
                    val insertDescription = reader.nextLine()
                    myGame?.description = insertDescription
                } else {
                    myGame?.description = myGame?.title

                    println("The description will be the title of the game")
                }

                user.historyGameSearch.add(myGame)
            },
            onFailed = { ex ->
                println("Game not found, try another ID.")

                println(message = "Do you want to check error detail? S/N")
                val option = reader.nextLine()

                if (option.equals("s", true)) {
                    println("Error. '$search': ${ex.message}")
                }
            }
        )

        println("Do you want to search another game? S/N")
        val continuesAnswer =  reader.nextLine()

    } while (continuesAnswer.equals("s", ignoreCase = true))

    println("\nHistory search:")
    println(user.historyGameSearch)

    println("\nGames order by title: ")
    user.historyGameSearch.sortBy {
        it?.title
    }
    user.historyGameSearch.forEach{
        println("Title: " + it?.title)
    }

    if (user.historyGameSearch.size > 0) {
        println(message = "Do you want to remove a game? S/N")
        val removeOpt = reader.nextLine()

        if (removeOpt.equals("s", true)) {
            println(user.historyGameSearch)
            println("Insert the game position on the historic that you want to remove: ")
            val idToRemove = reader.nextInt()
            user.historyGameSearch.removeAt(idToRemove)
            println(user.historyGameSearch)
        }
    }

    println("\nSearch completed successfully!")
}