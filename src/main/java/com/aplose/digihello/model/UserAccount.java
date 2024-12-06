package com.aplose.digihello.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();


    public UserAccount(String username, String password, String... authorities) {
        this.username = username;
        this.password = password;
        this.authorities = Arrays.stream(authorities)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    public UserAccount() {
    }

    /**
     * Getter for getid
     *
     * @return id
     */

    public Long getId() {
        return id;
    }

    /**
     * Setter for getid
     *
     * @return id
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for getusername
     *
     * @return username
     */

    public String getUsername() {
        return username;
    }

    /**
     * Setter for getusername
     *
     * @return username
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for getpassword
     *
     * @return password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Setter for getpassword
     *
     * @return password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for getauthorities
     *
     * @return authorities
     */

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Setter for getauthorities
     *
     * @return authorities
     */

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAccount{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append('}');
        return sb.toString();
    }
}
