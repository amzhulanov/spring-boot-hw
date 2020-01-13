package com.example.springboothw.controllers;

import com.example.springboothw.entities.Role;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.RoleRepository;
import com.example.springboothw.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private RoleRepository roleRepository;


    public RegistrationController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    /**
     * Показываю форму для регистрации нового пользователя с ролью обычного пользователя
     * http://localhost:8189/app/registration/showRegistrationPage.html
     *
     * @param model user -создаю нового пользователя и передаю на страницу
     * @return user_registration_form
     */
    @GetMapping
    public String regNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("rolies",roleRepository.findAll());
        return "user_registration_form";
    }

    /**
     * @param user - получаю заполненного User в моделе
     *             и передаю в сервис для присвоения дефолтной роли и сохранения в базе
     * @return
     * проверяю, чтобы оба пароля совпадали, и пользователя не было в базе
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String addUser(@Valid @ModelAttribute User user,BindingResult userBindingResult, @RequestParam String password_repeat,  Model model) {
        //,BindingResult userBindingResult
        if (user.getPassword() != null && !user.getPassword().equals(password_repeat)) {
            model.addAttribute("passwordError", "Passwords are different!");
            return "user_registration_form";
        }
        if (userBindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(userBindingResult);
            System.out.println("обработка ошибок - "+ControllerUtils.getErrors(userBindingResult));
            model.mergeAttributes(errors);
            return "user_registration_form";
        }
//Необходимо добавить обработку BindingResult в html страницу
        if (!userService.saveDefaultUser(user)){
            model.addAttribute("usernameError", "User exists!");
            return "user_registration_form";
        }

        return "index";
    }
}
