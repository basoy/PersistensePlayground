package com.bash.serg.persistenseplayground;

import com.bash.serg.persistenseplayground.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.LinkedList;

/**
 * Created by Serg Bash on 12/14/2015.
 */
public class Main {

    public static void main(String[] args)
    {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit_demo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Inventory car1 = new Inventory("Maybach",2013,"silver");
        Inventory car2 = new Inventory("Ford",2014,"black");
        Inventory car3 = new Inventory("Lamborgini",2015,"grey");

        try
        {
            entityManager.persist(car1);
            entityManager.persist(car2);
            entityManager.persist(car3);
            transaction.commit();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            transaction.rollback();
        }
        finally
        {
            entityManager.close();
        }

    }

}
