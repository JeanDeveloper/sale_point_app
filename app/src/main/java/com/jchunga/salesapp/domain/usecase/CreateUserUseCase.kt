package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.data.repository.UserRepository
import com.jchunga.salesapp.domain.repository.IUserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User)  = userRepository.createUser(user)
}