package com.example.springboothw.repositories;

import com.example.springboothw.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Collection<Role> findOneByName(String name);
    List<Role> findAll();
}
