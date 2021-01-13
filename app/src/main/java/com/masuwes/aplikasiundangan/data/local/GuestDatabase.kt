package com.masuwes.aplikasiundangan.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masuwes.aplikasiundangan.data.model.Guest
import com.masuwes.aplikasiundangan.utils.Constants

@Database(entities = [Guest::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDao(): GuestDao

    companion object {
        @Volatile
        private var INSTANCE: GuestDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : GuestDatabase {
            synchronized(GuestDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GuestDatabase::class.java, Constants.DATABASE_NAME)
                        .build()
                }
            }
            return INSTANCE as GuestDatabase
        }
    }
}