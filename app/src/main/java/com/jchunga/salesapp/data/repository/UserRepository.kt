package com.jchunga.salesapp.data.repository

import com.jchunga.salesapp.data.dao.UserDao
import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor( private val userDao: UserDao, private val userPreferencesRepository: UserPreferencesRepository) : IUserRepository {
    override suspend fun createUser(user: User)  = userDao.insert(user)
    override suspend fun getAllUsers(): List<User> = userDao.getAllUsers()
    override suspend fun isLoggedIn():Boolean = userPreferencesRepository.isLoggedIn()
    override suspend fun getUser(username: String, password: String): User? = userDao.getUser(username, password)
}