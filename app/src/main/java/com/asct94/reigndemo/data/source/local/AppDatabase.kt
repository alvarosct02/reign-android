package com.asct94.reigndemo.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asct94.reigndemo.BuildConfig
import com.asct94.reigndemo.models.Post

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun init(applicationContext: Context) {
            INSTANCE = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, BuildConfig.DATABASE_NAME
            ).build()
        }

        fun getInstance(): AppDatabase {
            return INSTANCE ?: throw RuntimeException()
        }

    }
}