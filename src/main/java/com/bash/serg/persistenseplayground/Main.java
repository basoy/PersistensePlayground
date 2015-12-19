package com.bash.serg.persistenseplayground;

import com.bash.serg.persistenseplayground.entity.Inventory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery <Inventory> _query = criteriaBuilder.createQuery(Inventory.class);
                    Root <Inventory> _from = _query.from(Inventory.class);
                    TypedQuery <Inventory> tq = entityManager.createQuery(_query.select(_from));
                    List <Inventory> result = tq.getResultList();
                    for(Inventory inventory : result){
                        System.out.println(inventory);
                    }

                }
                if(s1.equals(EXIT)) {
                    break;
                }
            }
            if(car != null){
                entityManager.persist(car);
            }
            transaction.commit();

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
