package br.com.fiap.flore.repository

import android.content.Context
import br.com.fiap.flore.dao.FloreDatabase
import br.com.fiap.flore.model.User

class RoomUserRepository(context: Context): UserRepository {

    private val floreDatabase = FloreDatabase.getDatabase(context).userDao()

    override fun saveUser(user: User) {
        floreDatabase.save(user)
    }

    override fun getUser(): User {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Int): User {
        return floreDatabase.getUserById(1) ?:User()
    }

    override fun getUserByEmail(email: String): User? {
        return floreDatabase.getUserByEmail(email)
    }

    override fun login(email: String, password: String): Boolean {
        val user = floreDatabase.login(email, password)
        return user != null
    }

    override fun update(user: User): Int {
        return floreDatabase.update(user)
    }

    override fun delete(user: User): Int {
        return floreDatabase.delete(user)
    }
}