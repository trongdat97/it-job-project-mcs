package com.example.accountservice.model;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserInfoToken{
    private String username;
    private Set<Role> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserInfoToken(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public static UserInfoToken build(User user){
        return new UserInfoToken(
                user.getUsername(),
                user.getRoles()
        );
    }
}
