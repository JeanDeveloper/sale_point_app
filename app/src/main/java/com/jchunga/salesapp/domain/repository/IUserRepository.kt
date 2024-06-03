package com.jchunga.salesapp.domain.repository

import com.jchunga.salesapp.data.entity.User

interface IUserRepository {

    suspend fun createUser(user: User)

    suspend fun getAllUsers(): List<User>

    suspend fun isLoggedIn(): Boolean

    suspend fun getUser( username: String, password: String ): User?


}