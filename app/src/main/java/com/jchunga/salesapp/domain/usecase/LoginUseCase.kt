package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.data.repository.UserPreferencesRepository
import com.jchunga.salesapp.data.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository
){
    suspend operator fun invoke( username: String, password: String ) : User? {
        val user = userRepository.getUser(username, password)
        return if(user != null){
            userPreferencesRepository.setLoggedIn(true)
            user;
        }else{
            null
        }
    }
}