package com.trelloplus.model;

import com.trelloplus.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {  // Now implements UserDetails, Spring Security's protocol for auth feature

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "teamLead")
    private List<Project> projectsManaged = new ArrayList<>();

    private String username;
    private String passwordHash; // We'll map this to getPassword()

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private boolean isActive;

    private String phone;
    private String avatarUrl;
    private String bio;
    private String location;

    // This is how Spring Security gets the roles for the user
    //Returns user roles as Spring Security authorities like ROLE_ADMIN
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + this.role.name()); // e.g., ROLE_ADMIN, ROLE_INTERNEE
    }

    /* The syntax: Collection<? extends GrantedAuthority>
    * means:
    * A collection (like a List, Set, etc.) of objects
    * that are instances of GrantedAuthority or its subclasses.*/



    //Used to identify user during login
    @Override
    public String getUsername() {
        return this.username;
    }

    //Returns hashed password for login validation:
    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can add logic later if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Checks if account is active (you already have isActive)
    @Override
    public boolean isEnabled() {
        return this.isActive; // Only allow login if user is active
    }
}
