package org.benjamin.filetree.model.repository;

import javax.persistence.EntityManagerFactory;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;
import org.benjamin.filetree.model.entity.Type;
import org.hibernate.cfg.Configuration;

/**
 * Singleton class for EntityManagerFactory, uses hibernate as JPA provider.
 * 
 * @author benjamin
 * @see EntityManagerFactory
 *
 */
public class FactoryHolder {
  
  private static EntityManagerFactory factory;
  
  public static EntityManagerFactory getFactory() {
    if (factory == null) {
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
