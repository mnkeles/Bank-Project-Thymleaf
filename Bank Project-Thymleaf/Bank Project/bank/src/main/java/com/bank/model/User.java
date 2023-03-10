package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Size(min = 5, max = 25, message = "username length should be between 5 and 25 characters")
    @Column(name="username",unique = true)
    private String username;

    @Column(name="email",unique = true)
    private String email;

    @Size(min = 5, message = "Minimum password length: 5 characters")
    @Column(name="password")
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //    @ManyToMany(cascade = CascadeType.REMOVE)
//    @JoinTable(name = "user_roles", joinColumns = {
//            @JoinColumn(name = "user_id")}, inverseJoinColumns = {
//            @JoinColumn(name = "role_id")})
//    public Set<Role> roles;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

}
