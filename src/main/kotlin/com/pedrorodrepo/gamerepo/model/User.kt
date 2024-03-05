package com.pedrorodrepo.gamerepo.model

import kotlin.random.Random

data class User(
    var name: String,
    var email: String
) {
    var id: String? = null
        private set
    var bDate: String? = null
    var userName: String? = null
        set(value) {
            field = value

            if (id.isNullOrBlank())
                createId()
        }

    constructor(name: String, email: String, bDate: String, userName: String) :
            this(name, email) {
                this.bDate = bDate
                this.userName = userName
                createId()
            }

    init {
        this.email = validEmail()

        if (this.name.isNullOrBlank()) {
            throw IllegalArgumentException("Invalid name! Please try another one.")
        }
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', id=$id, bDate=$bDate, userName=$userName)"
    }

    private fun createId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        "$userName#$tag".also { this.id = it }
    }

    fun validEmail(): String {
        val regex = Regex(pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        if (regex.matches(email))
            return email
        else
            throw IllegalArgumentException("Invalid E-mail! Please try another one.")
    }
}
