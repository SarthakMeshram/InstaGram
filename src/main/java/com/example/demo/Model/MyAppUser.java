package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "my_app_user")
public class MyAppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String verifcationToken;
    private boolean isVerified;

    @Column(name= "reset_token")
    private String resetToken;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerifcationToken() {
        return verifcationToken;
    }

    public void setVerifcationToken(String verifcationToken) {
        this.verifcationToken = verifcationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
    
}
