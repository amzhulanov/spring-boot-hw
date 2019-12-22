package com.example.springboothw.controllers;

import com.example.springboothw.entities.Role;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.RoleRepository;
import com.example.springboothw.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private UserService userService;
    private RoleRepository roleRepository;



    public UserController(UserService userService, RoleRepository roleRepository){
        this.userService = userService; this.roleRepository=roleRepository;
    }

    @GetMapping("/user/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/user/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.findByPhone(principal.getName());
        System.out.println("телефон - "+user.getPhone()+" : пароль - "+user.getPassword());
        model.addAttribute("user", user);
        System.out.println("возвращаю данные юзера в профиль");
        return "profile";
    }

    @GetMapping(path="/admin/users")
    public String getAllUser(Model model){
        List<User> users= (List<User>) userService.findAll();
        model.addAttribute("users",users);
        return "user_list";
    }

    @PostMapping("/user/regNewUser")
    public String regNewUser(Model model){
        List<Role> roles= roleRepository.findAll();
        model.addAttribute("roles",roles);
        return "registr";
    }

    @GetMapping(path="/user/add")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public String addUser(Principal principal, Model model, @RequestParam Map<String, String> params){
        System.out.println(params.get("phone"));
        User user =new User();

        user.setPhone(params.get("phone").toString());
        user.setPassword(params.get("password"));
        user.setFirstName(params.get("firstname"));
        user.setLastName(params.get("lastname"));

        Collection<Role> roles=new Collection<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        roles.add(roleRepository.findOneByName("ROLE_CUSTOMER"));
        user.setRoles(roles);
        userService.save(user);
        return "success";

    }
}
