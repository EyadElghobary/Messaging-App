package com.example.demo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id
    String id;

    // @Indexed(unique = true)
    @NonNull
    private String username;

    // @Indexed(unique = true)
    @NonNull
    private String email;

    @JsonIgnore
    @NonNull
    private String password;

    private String firstname;

    private String lastname;

    private List<String> groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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


// @Document(collection = "users")
// @Data
// public class User {
//     @Id
//     String id;

//     // @Indexed(unique = true)
//     // This is what users will use to friend each other
//     @NonNull
//     private String username;

//     // @Indexed(unique = true)
//     @NonNull
//     private String email;

//     @NonNull
//     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//     private String password;

//     private String firstname;

//     private String lastname;

//     private List<String> groups;

//     @JsonIgnore
//     public String getPassword() {
//         return this.password;
//     }


// }