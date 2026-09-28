package com.autobots.autobots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobots.autobots.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
