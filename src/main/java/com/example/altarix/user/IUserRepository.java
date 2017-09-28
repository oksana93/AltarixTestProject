package com.example.altarix.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

}
