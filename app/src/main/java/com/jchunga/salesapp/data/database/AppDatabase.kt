package com.jchunga.salesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jchunga.salesapp.data.dao.PointSaleDao
import com.jchunga.salesapp.data.dao.ProductDao
import com.jchunga.salesapp.data.dao.UserDao
import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.data.entity.Product

@Database(entities=[User::class, PointSale::class, Product::class], version=1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun pointSaleDao(): PointSaleDao

    abstract fun productDao(): ProductDao


//    companion object{
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }

}