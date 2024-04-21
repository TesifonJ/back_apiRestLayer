package com.sopra.apirestcontroller.domain.persistance.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;

@Repository
public interface iProductDao extends JpaRepository<ProductEntity, Long> {

}
