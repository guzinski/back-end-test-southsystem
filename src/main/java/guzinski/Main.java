package guzinski;

import com.google.inject.Guice;

public class Main {


    public static void main(String[] args) {
        var injector = Guice.createInjector();
        var app = injector.getInstance(Application.class);
        app.start();
    }

}
