package com.example.springboothw.repositories;

import com.example.springboothw.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByPhone(String phone);
    boolean existsByPhone(String phone);
    User findOneByEmail(String email);
    User findUserByUrl(String url);

    @Transactional
    @Modifying
    @Query("update User u SET u.status=:status where u.id=:id_user")
    void updateStatusUserById(Long id_user,String status);
}
