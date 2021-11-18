package com.example.eigeneapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import javax.persistence.Entity;
import javax.persistence.Id;

public class DataBase {

    //Diese Funktion persistiert ein ihr übergebenes Objekt:
    public static String Persist(Object Eingabe){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        session.beginTransaction();
        session.persist(Eingabe);
        session.flush();
        session.close();
        return "persistence successfully done";
    }

    //Diese Funktion liest das Objekt mit der ihr übergebenen id ein und gibt es als "Recipe"-Objekt aus:
    public static Recipe CallRecipeById(Integer input){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();


        session.beginTransaction();
        Recipe recipe = session.load(Recipe.class, input.longValue());
        session.flush();
        return recipe;
    }

    //Diese Funktion updatet das ihr übergebene Objekt:
    public static void EvictAndMerge(Object object, Integer id){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();


        session.beginTransaction();
        session.evict(session.load(Recipe.class, id.longValue()));
        session.flush();
        session.merge(object);
        session.flush();
        session.close();
    }

}
