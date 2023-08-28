package com.example.week06d1security.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = true)
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // the most important fields
    @NotEmpty(message = "the username field is required.")
    private String username;

    @NotEmpty(message = "the password field is required.")
    private String password;

    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Todo> todos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        another way
//        List<GrantedAuthority> list = new ArrayList<>();
//        list.add(new SimpleGrantedAuthority(role));

        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
