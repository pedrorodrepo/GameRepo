class Game(
    val title: String,
    val thumb: String ) {
    val description = ""

    override fun toString(): String {
        return "Game (\n title='$title',\n capa='$thumb',\n description='$description'\n)"
    }
}