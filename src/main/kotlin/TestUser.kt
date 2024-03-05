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

//  let - executa uma ação em um objeto e retorna o resultado da expressão lambda;
//
//  run - também executa uma ação em um objeto como o let, mas não retorna o resultado da expressão, e sim o resultado do bloco de código;
//
//  with - executa uma sequência de operações sendo necessário passar o objeto como argumento explícito;
//
//  apply - realiza operações de configuração em um objeto e retorna o próprio objeto modificado;
//
//  also - realiza a mesma coisa que o apply, porém retorna o próprio objeto original.