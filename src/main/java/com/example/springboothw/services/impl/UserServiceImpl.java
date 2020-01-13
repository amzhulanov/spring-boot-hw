package com.example.springboothw.services.impl;


import com.example.springboothw.entities.Role;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.RoleRepository;
import com.example.springboothw.repositories.UserRepository;
import com.example.springboothw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByPhone(String phone) {
        System.out.println("телефон для поиска=" + phone);
        return userRepository.findOneByPhone(phone);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByPhone(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean isUserExist(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    /**
     * При регистрации пользователь заполняет поля данных на форме.
     * Дефолтная роль ROLE_CUSTOMER присваивается по умолчанию.
     * Если в базе уже был найден пользователь с таким же телефоном и ролью ROLE_ONECLICK,
     * то существующий пользователь обновляется.
     * Если в базе уже был найден пользователь с таким же телефоном и ролью отличной от ROLE_ONECLICK,
     * то генерируется ошибка
     * Иначе создаётся новый пользователь
     */
    @Override
    public Boolean saveDefaultUser(User user) {
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            return false;
        }
        if (userRepository.findOneByPhone(user.getPhone()) != null) {
            System.out.println("Найден такой же пользователь  с ролью " + userRepository.findOneByPhone(user.getPhone()).getRoles().toString());
            for (Role role : userRepository.findOneByPhone(user.getPhone()).getRoles()) {
                if (!role.getName().equals("ROLE_ONECLICK")) {
                    return false;
                }
            }
        }
        User newUser = new User();
        newUser.setId(userRepository.findOneByPhone(user.getPhone()).getId());
        newUser.setRoles(roleRepository.findOneByName("ROLE_CUSTOMER"));
        newUser.setPhone(user.getPhone());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    private String validateUser(User user) {
        String nameError = "";
        return nameError;
    }

    public User addNewUser(String phone) {
        if (userRepository.findOneByPhone(phone) != null) {
            return userRepository.findOneByPhone(phone);
        }
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setRoles(roleRepository.findOneByName("ROLE_ONECLICK"));
        userRepository.save(newUser);
        return newUser;

    }


}
