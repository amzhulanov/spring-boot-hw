package com.example.springboothw.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long id;

    @NotNull
    @Column(name="city_fld")
    private String city;

    @NotNull
    @Column(name="street_fld")
    private String street;

    @NotNull
    @Column(name="num_house_fld")
    private String house;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;

    //public Address(String city, String street, String house, User user) {
    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
       // this.user = user;
    }
}
