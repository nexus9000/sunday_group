package edu.itstep.runapp;

import jakarta.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;

public class RunApp {
private static  Logger logger = Logger.getLogger(RunApp.class);
    public static void main (String...arg) {
          initTable();
    }
    private static void initTable() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        EntityManager em = factory.createEntityManager();
      try {
          em.getTransaction().begin();
          PersonTest person = new PersonTest("ORM-HIBERNATE", 100);
         // PersonTest person1 = new PersonTest("John", 20);
          em.persist(person);
         // em.persist(person1);
//        List<Users> result = em.createQuery("from Users").getResultList();
//        System.out.println("##################################################");
//        for (Users user : result) {
//            System.out.println(user.getUserName() + " " + user.getPassword());
//        }
          em.getTransaction().commit();
      }catch(HibernateException he){
          em.getTransaction().rollback();
          logger.error(he.getMessage());
      }
        em.close();
    }
}
