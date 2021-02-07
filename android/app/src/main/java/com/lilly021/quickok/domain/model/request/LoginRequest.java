package com.lilly021.quickok.domain.model.request;

public class LoginRequest {

    public String username;
    public String password;
    public String client_id;
    public String client_secret;
    public String grant_type;

    public LoginRequest(String username, String password, String client_id, String client_secret,
                        String grant_type) {
        this.username = username;
        this.password = password;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grant_type = grant_type;
    }


}
