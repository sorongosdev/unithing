package com.unimind.unithing

import androidx.room.*
import com.unimind.unithing.Data.User

/**유저정보에 관한 Data Access Object*/
@Dao
interface UserDao {

    @Insert
    fun insert(word: User)

    @Delete
    fun delete(word: User)

    @Update
    fun update(word: User)
}