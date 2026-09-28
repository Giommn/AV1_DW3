package com.autobots.autobots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobots.autobots.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
