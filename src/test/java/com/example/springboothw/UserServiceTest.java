package com.example.springboothw;

import com.example.springboothw.entities.User;
import com.example.springboothw.repositories.UserRepository;
import com.example.springboothw.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    private User userFromDB = new User();

    @MockBean
    private UserRepository userRepository;

    @Before
    public void initUser(){
        userFromDB.setPhone("89380000000");
        userFromDB.setEmail("admin@gmail.ru");
        userFromDB.setPassword("100100");
        userFromDB.setId(100L);
    }
    /*
тест метода поиска пользователя по телефону
 */
    @Test
    public void findOneUserTest() {
        UserService userService=mock(UserService.class);

        Mockito.doReturn(userFromDB)
                .when(userRepository)
                .findOneByPhone("89380000000");

        User userOne = userService.findByPhone("89380000000");
        Assert.assertNotNull(userOne);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("89380000000"));
    }

    /*
тест метода поиска пользователя электронному адресу
 */
    @Test
    public void findOneByEmailTest() {
        UserService userService=mock(UserService.class);
        User userFromDB = new User();
        userFromDB.setPhone("89380000000");
        userFromDB.setEmail("admin@gmail.ru");

        Mockito.doReturn(userFromDB)
                .when(userRepository)
                .findOneByEmail("admin@gmail.com");

        User userOne = userService.findOneByEmail("admin@gmail.com");
        Assert.assertNotNull(userOne);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByEmail(ArgumentMatchers.eq("admin@gmail.com"));
    }

    /*
    тест добавления нового пользователя
     */
    @Test
    public void saveUserTest(){
        User userTemplate=new User();
        userTemplate.setPhone("89380000000");
        userTemplate.setEmail("admin@gmail.ru");
        userTemplate.setPassword("100100");

       UserService userService=mock(UserService.class);
       when(userService.saveUser(userTemplate)).thenAnswer(new Answer<User>() {
           public User answer(InvocationOnMock invocation) throws Throwable{
               return userFromDB;
           }
       });
        Assert.assertEquals(100L,userService.saveUser(userTemplate).getId().longValue());
    }
}
