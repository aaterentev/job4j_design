package ru.job4j.generics;

/**
 * @author Alex Terentev (ShaDar-ru)
 * @version 1.0
 * @date 08.07.2021
 */
public class User extends Base {

    public User(String id) {
        super(id);
    }

    public String getId() {
        return super.getId();
    }
}
