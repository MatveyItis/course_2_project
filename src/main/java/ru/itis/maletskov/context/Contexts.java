package ru.itis.maletskov.context;

public class Contexts {
    public static ApplicationContextPrimitiveImpl primitive() {
        return ApplicationContextPrimitiveImpl.getContext();
    }
}
