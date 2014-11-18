package by.kruglik.bean;

/**
 * Created by kruglik on 14.11.2014.
 */
public class User {
    public User(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
