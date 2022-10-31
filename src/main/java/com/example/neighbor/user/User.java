package com.example.neighbor.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id = 0;
    private String mail;
    private String phone;
    private String name;
    private String surname;
    private String patronymic;
    private String photoURL;
    private String description;
    private String hashPassword;
    private Role role;

    public User() {
    }

    public User(String phone, String name){
        this.phone = phone;
        this.name = name;
    }

    public void setId(long id) { this.id = id; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }
    public void setDescription(String description) { this.description = description; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }
    public void setRole(Role role ) { this.role = role; }


    public long getId() { return id; }
    public String getMail() { return mail; }
    public String getPhone() { return phone; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPatronymic() { return patronymic; }
    public String getPhotoURL() { return photoURL; }
    public String getDescription() { return description; }
    public String getHashPassword() { return hashPassword; }
    public Role getRole(){ return role; };


}
