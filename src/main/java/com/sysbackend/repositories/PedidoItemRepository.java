package com.sysbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sysbackend.domain.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer>{

}
