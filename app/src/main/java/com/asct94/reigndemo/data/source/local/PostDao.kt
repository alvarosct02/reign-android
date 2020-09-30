package com.asct94.reigndemo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asct94.reigndemo.models.Post
import io.reactivex.Single

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: Post)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entityList: List<Post>)

    @Query("SELECT * FROM Post WHERE deleted = 0 ORDER BY substr(createdAt,7,4)||substr(createdAt,4,2)||substr(createdAt,1,2)||substr(createdAt,11,8) DESC")
    fun getAll(): Single<List<Post>>

    @Query("UPDATE Post SET deleted = 1 WHERE id = :id")
    fun deleteById(id: Long)


}
