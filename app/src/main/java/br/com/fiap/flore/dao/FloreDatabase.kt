package br.com.fiap.flore.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.flore.model.User

@Database(entities = [User::class], version = 1)
abstract class FloreDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private lateinit var instance: FloreDatabase

        fun getDatabase(context: Context): FloreDatabase{
            if(!::instance.isInitialized){
                instance = Room
                    .databaseBuilder(
                        context, FloreDatabase::class.java,
                        "flore_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}