package com.jchunga.salesapp.core.utils.di

import android.content.Context
import androidx.room.Room
import com.jchunga.salesapp.data.dao.PointSaleDao
import com.jchunga.salesapp.data.dao.ProductDao
import com.jchunga.salesapp.data.dao.UserDao
import com.jchunga.salesapp.data.database.AppDatabase
import com.jchunga.salesapp.data.repository.UserPreferencesRepository
import com.jchunga.salesapp.data.repository.UserRepository
import com.jchunga.salesapp.domain.usecase.CheckLoginStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun providePointSaleDao(db: AppDatabase): PointSaleDao = db.pointSaleDao()

    @Singleton
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(@ApplicationContext  context: Context): UserPreferencesRepository {
        return UserPreferencesRepository(context)
    }

    @Provides
    @Singleton
    fun provideCheckLoginStatusUseCase(userRepository: UserRepository): CheckLoginStatusUseCase {
        return CheckLoginStatusUseCase(userRepository)
    }



}