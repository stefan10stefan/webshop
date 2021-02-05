package com.model.dto;

import com.model.User;

public class UserDTO extends AbstractEntityDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String type;
    private Long lat;
    private Long lng;

    public UserDTO() {}

    public UserDTO(User user) {
        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        type = user.getType();
        lat = user.getLat();
        lng = user.getLng();
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
