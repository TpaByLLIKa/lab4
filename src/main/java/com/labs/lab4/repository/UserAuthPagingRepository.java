package com.labs.lab4.repository;

import com.labs.lab4.model.UserAuth;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserAuthPagingRepository extends PagingAndSortingRepository<UserAuth, UUID> {
}
