package com.example.springboothw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tryToStart() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
        //   .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Интернет магазин")));
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin/showListUsers"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/user/login"));
    }

    /**
     * tryEditProduct()
     * http://localhost:8189/app/products/show
     */
    @Test
    public void tryToShowProducts() throws Exception {
        mockMvc.perform(get("/products/show"))
                .andDo(print())
                .andExpect(status().isOk())
           .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Каталог товаров")));
    }
    /**
     * tryEditProduct()
     *Testing edit product unregister user
     * http://localhost:8189/app/products/edit/1
     */
    @Test
    public void tryEditProduct() throws Exception {
        mockMvc.perform(get("/products/edit/1"))
                .andDo(print())
                //.andExpect(status().isOk())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/user/login"));

    }
}
