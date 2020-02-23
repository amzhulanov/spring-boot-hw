package com.example.springboothw.services.impl;


import com.example.springboothw.entities.Role;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.OrderRepository;
import com.example.springboothw.repositories.RoleRepository;
import com.example.springboothw.repositories.UserRepository;
import com.example.springboothw.services.OrderService;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository
                                    , RoleRepository roleRepository
                                  //,OrderService orderService) {
                                  ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        //this.orderService = orderService;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByPhone(String phone) {
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

    @Override
    //public Boolean saveUser(User user) {
    public User saveUser(User user) {
//        if (userRepository.findOneByEmail(user.getEmail()) != null
//                || userRepository.findOneByPhone(user.getPhone()) != null) {
//            return false;
//        }
//        User newUser = new User();
//        newUser.setRoles(roleRepository.findOneByName("ROLE_CUSTOMER"));
//        newUser.setPhone(user.getPhone());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findOneByName("ROLE_CUSTOMER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
       // userRepository.save(newUser);
        //orderRepository.updateAllUserByPhone(newUser.getPhone(),newUser);
       // return true;
        return //userRepository.save(newUser);
                userRepository.save(user);
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }


}
