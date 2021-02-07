package com.lilly021.quickok.domain.model.request;

public class EditProfileRequest {

    public Long id;
    public String firstName;
    public String lastName;

    public EditProfileRequest(Long id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
}
