package com.coursework.Kotik.Models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name="t_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name="user_role", nullable = false)
    private String user_role;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_purchases",
            joinColumns = { @JoinColumn(name = "t_user_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")}
    )
    List<Product> productList = new ArrayList<>();

    @Transient
    private String role;

    @Transient
    private Collection<GrantedAuthority> authorities;

    public User() {
    }

    public User(Long id, String username, String email, String password,
                Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    // Security
    //-------------------------

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
