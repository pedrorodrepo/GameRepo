package com.pedrorodrepo.gamerepo.model

data class Game(
    val title: String,
    val thumb: String ) {
    var description: String? = null

    override fun toString(): String {
        return "com.pedrorodrepo.gamerepo.model.Game (\n title='$title',\n capa='$thumb',\n description='$description'\n)"
    }
}