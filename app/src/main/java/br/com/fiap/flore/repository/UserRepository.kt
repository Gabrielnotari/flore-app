package br.com.fiap.flore.repository

import br.com.fiap.flore.model.User

interface UserRepository {

    fun saveUser(user: User)
    fun getUser(): User
    fun getUserByEmail(email: String): User?
    fun login(email: String, password: String): Boolean

}