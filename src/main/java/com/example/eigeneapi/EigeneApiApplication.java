package com.example.eigeneapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Persistence;
import java.util.Objects;

@SpringBootApplication
public class EigeneApiApplication {

    public static void main(String[] args) {SpringApplication.run(EigeneApiApplication.class, args);}

        /*für die Greeting class*/
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

    /*Zum Hinzufügen neuer Rezepte;
	 "recipeTitle" ist das Attribut, welches alle Rezepte bei der Erstellung haben müssen.
	 Die anderen Angaben können bei der Erstellung angegeben werden, müssen sie aber nicht.*/
    @PostMapping("/recipe/add")
    public String createRecipe(@RequestParam(value = "recipeTitle") String recipeTitle) {
        Recipe SaveRecipe = new Recipe();
        SaveRecipe.setRecipeTitle(recipeTitle);



        DataBase.Persist(SaveRecipe);

        return "reciped persisted";
    }

    /*Zum Verändern vorhandener Studenten;
	 "id" ist das Attribut, welches angegeben werden muss, um den zu verändernden Eintrag abrufen zu können.
	 Darauf folgen die zu verändernden Parameter als Eingabe.*/
    @PostMapping ("/recipe/change")
    public String createRecipe(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "recipeTitle",required = false) String recipeTitle) {

        //Einlesen des zu verändernden Objekts:
        Recipe EingelesenesRezept = DataBase.CallRecipeById(id);

        //Ändern der Parameter:
        if (Objects.nonNull(recipeTitle))
            EingelesenesRezept.setRecipeTitle(recipeTitle);


        //Updaten des zu verändernden Objekts:
        DataBase.EvictAndMerge(EingelesenesRezept, id);
        return "recipe changed";
    }

    //Anzeigen eines Objekts mit einer bestimmten id:
    @GetMapping("/recipes/show")
    public String viewRecipes(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        String RecipeString = null;

       Recipe recipe = DataBase.CallRecipeById(id);

        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            RecipeString = om.writeValueAsString(recipe);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return RecipeString;
    }



}
