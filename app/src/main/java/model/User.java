package model;

/**
 * Created by Aprendiz on 20/02/2018.
 */

public class User {
    private long _id;
    private String name;
    private String document;
    private String email;
    private String password;

    public User() {
    }

    public User(long _id, String name, String document, String email, String password) {
        this._id = _id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
    }

    public User(String document) {
        this.document = document;
    }


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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
}
