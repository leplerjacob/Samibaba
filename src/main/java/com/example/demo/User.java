package com.example.demo;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="User_Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="email", nullable=false)
    @NonNull
    @Size (min=3, max=30)
    private String email;

    @Column(name="password")
    @NonNull
    @Size(min=8)
    private String password;

    @Column(name="first_name")
    @NonNull
    @Size(min=3, max=30)
    private String firstName;

    @Column(name="last_name")
    @NonNull
    @Size(min=3, max=30)
    private String lastName;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="username")
    @NonNull
    @Size(min=3, max=30)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private Collection<Course> courses;

    public User() {
    }

    public User(String email,
                String password,
                String firstName,
                String lastName,
                boolean enabled,
                String username
                ) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.username = username;
        this.roles = roles;
        this.courses = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }



}
