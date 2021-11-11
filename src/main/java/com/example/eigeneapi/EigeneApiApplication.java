package com.example.eigeneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class EigeneApiApplication {

    public static void main(String[] args) {SpringApplication.run(EigeneApiApplication.class, args);}

        @RestController
        public class GreetingController {
            private static final String template = "Hello, %s!";
            private final AtomicLong counter = new AtomicLong();

            @GetMapping("/greeting")
            @CrossOrigin(origins = "http://localhost:8100")
            public Greeting greeting(@RequestParam(value = "name", defaultValue = "User") String name) {
                return new Greeting(counter.incrementAndGet(), String.format(template, name));
            }
    }

   // public String createRecipeTitle(@RequestBody String recipeTitle) {

       // Recipe recipe = new Recipe (recipeTitle);

   // }


}
