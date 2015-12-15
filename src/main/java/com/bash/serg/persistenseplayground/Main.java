package com.bash.serg.persistenseplayground;

import com.bash.serg.persistenseplayground.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Serg Bash on 12/14/2015.
 */
public class Main {

public static void main(String [] args){
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Inventory car1 = new Inventory("Maybah", 2012, "silver");
    Inventory car2 = new Inventory("Ford", 2015, "black");
    Inventory car3 = new Inventory("Lamborgini", 2015, "grey");

    session.save(car1);
    session.save(car2);
    session.save(car3);

    session.getTransaction().commit();
    session.close();
}
}
