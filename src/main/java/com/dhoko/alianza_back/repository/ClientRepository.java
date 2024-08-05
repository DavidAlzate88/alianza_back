package com.dhoko.alianza_back.repository;

import com.dhoko.alianza_back.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

}
