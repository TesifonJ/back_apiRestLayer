package com.sopra.apirestcontroller.domain.persistance.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;

@Repository
public interface iUserDao extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findUserByUsername(String username);

}
