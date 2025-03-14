package model;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public String setEmail(String email) {
        this.email = email;
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String setPassword(String password) {
        this.password = password;
        return password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}