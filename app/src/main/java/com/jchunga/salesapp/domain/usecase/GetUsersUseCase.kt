package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.data.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> = userRepository.getAllUsers()
}
