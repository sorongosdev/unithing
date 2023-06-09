package com.unimind.unithing


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.unimind.unithing.Data.User

/**유저정보에 관한 Data Access Object*/
@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAllUser() : List<User>

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)
}