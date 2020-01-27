package com.pw.mini.mogger.user.control.repository;

import com.pw.mini.mogger.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findByUserName(String userName);
}
