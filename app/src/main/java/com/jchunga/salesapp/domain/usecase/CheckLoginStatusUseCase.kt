package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.repository.UserRepository
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(): Boolean {
        return userRepository.isLoggedIn()
    }
}