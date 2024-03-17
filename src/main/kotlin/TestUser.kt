import com.pedrorodrepo.gamerepo.model.User

fun main () {
    val gamer = User("Pedro", "teste@outlook.com")
    println(gamer)

    val gamer2 = User("Aline", "teste@outlook.com", "27/01/2002", "liliGameplay")
    println(gamer2)

    gamer.let {
        it.bDate = "24/04/1997"
        it.userName = "lilJhin"
    }.also {
        println(gamer)
    }

    gamer.let {
        it.userName = "lilJhinn"
    }.also {
        println(gamer)
    }
}