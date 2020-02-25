package com.example.springboothw.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min=2,max=30)
    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "url")
    private String url;

//    @Column(name = "date_reg")
//    private Date date_reg;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews;

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(@NotNull @Size(min = 2, max = 30) String phone, String firstName) {
        this.phone = phone;
        this.firstName = firstName;
    }
}

