package org.benjamin.filetree.model.repository;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;
import org.benjamin.filetree.model.entity.Type;
import org.hibernate.cfg.Configuration;

public class FactoryHolder {
  
  static EntityManagerFactory factory;
  
  public static EntityManagerFactory getFactory() {
    if (factory == null || !factory.isOpen()) {
      Logger.getLogger("org.hibernate").setLevel(Level.OFF);
      factory = new Configuration()
                   .configure("hibernate.cfg.xml")
                   .addAnnotatedClass(Branch.class)
                   .addAnnotatedClass(Leaf.class)
                   .addAnnotatedClass(Type.class)
                   .buildSessionFactory();
    }
    return factory;
  }

}
