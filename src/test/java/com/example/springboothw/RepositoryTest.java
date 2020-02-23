package com.example.springboothw;

import com.example.springboothw.entities.Product;
import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.ProductRepository;
import com.example.springboothw.repositories.UserRepository;
import com.example.springboothw.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void setProductRepository(){
        Product product=new Product("white","bread",new BigDecimal(10.5f));
        Product out=entityManager.persist(product);
        entityManager.flush();
        List<Product> productsList = (List<Product>)productsRepository.findAll();
        System.out.println(productsList);
        Assert.assertEquals(21, productsList.size());
    }
/*
тестирую правило валидации номера телефона
 */
    @Test
    public void setWrongPhoneUser(){
        User user=new User("1","Ivan");
        String cause=null;
        try{
            User out=entityManager.persist(user);
        }catch (ConstraintViolationException ex){
            cause="size must be between 2 and 30";
        }
        Assert.assertEquals("size must be between 2 and 30",cause);
    }

}
