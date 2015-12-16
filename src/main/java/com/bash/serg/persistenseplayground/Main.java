package com.bash.serg.persistenseplayground;

import com.bash.serg.persistenseplayground.entity.Inventory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Serg Bash on 12/14/2015.
 */
public class Main {
    private final static String INSERT = "insert";
    private final static String SELECT = "select";
    private final static String EXIT = "exit";

    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit_demo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Scanner keyboard = new Scanner(System.in);
        Inventory car = new Inventory();
        try
        {
            while(keyboard.hasNext()) {
                String s1 = keyboard.nextLine();
                String [] res = new String[3];
                if(s1.contains(INSERT)){
                    for(int i = 0; i < 2; i++) {
                        res = s1.split(",");
                    }
                    List <String> cars = Arrays.asList(res);
                    car = new Inventory(cars.get(0).replace("insert ", ""),
                            Integer.parseInt(cars.get(1)), cars.get(2));
                    break;

                }
                if(s1.equals(SELECT)){
                    System.out.println("select");
                }
                if(s1.equals(EXIT)) {
                    break;
                }
            }
            entityManager.persist(car);
            transaction.commit();
            System.exit(0);

        } catch (Exception ex)
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
