package ru.itis.maletskov.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class A {
    private String name;

    public A(String name) {
        this.name = name;
    }

    public static void m(A a) {
        a.setName("Vova");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(name, a.name);
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    public static void main(String[] args) {
        Map<A, Integer> map = new HashMap<>();
        map.put(new A("name"), 1);
        map.put(new A("name"), 2);
        map.put(new A("name"), 3);
        map.put(new A("name"), 4);
        map.put(new A("name1"), 1);
        for (Map.Entry<A, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}