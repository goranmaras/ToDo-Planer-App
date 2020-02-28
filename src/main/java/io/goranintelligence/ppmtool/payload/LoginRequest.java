package io.goranintelligence.ppmtool.payload;


import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Korisničko ime ne može biti prazno")
    private String username;
    @NotBlank(message = "Zaporka ne može biti prazna")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
