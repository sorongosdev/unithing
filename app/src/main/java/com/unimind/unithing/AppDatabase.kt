package com.unimind.unithing

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unimind.unithing.Data.User

/**데이터베이스라는 것을 알려주는 어노테이션
 * 싱글톤
 * */
@Database(entities = [User::class], version = 1) // 여러개 가능
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                //하나만 만들어짐
                synchronized(AppDatabase::class.java) {
                    //초기화 해줘야함, 데이터베이스 빌더를 통해서
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "user-database.db" //database name
                    ).build() //만들어짐
                }
                Log.d("AppDatabase", "getInstance 호출됨")
            }
            return INSTANCE
            Log.d("AppDatabase", "인스턴스 반환됨")
        }
    }
}