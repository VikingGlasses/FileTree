package org.benjamin.filetree.model.repository;

import java.util.List;

import javax.persistence.EntityManager;

public class TestExe {

  public <T> T execute(SingleResultExecutable<T> executable) {
    EntityManager manager = null;
    try {
      manager = FactoryHolder.getFactory().createEntityManager();
      manager.getTransaction().begin();
      T t = executable.execute(manager);
      manager.getTransaction().commit();
      return t;
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
  }
  
  public boolean executeBooleanResult(BooleanResultExecutable executable) {
    EntityManager manager = null;
    boolean b = false;
    try {
      manager = FactoryHolder.getFactory().createEntityManager();
      manager.getTransaction().begin();
      b = executable.execute(manager);
      manager.getTransaction().commit();
      return b;
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
  }

  public <T> List<T> excuteListResult(ListResultExecutable<T> executable) {
    EntityManager manager = null;
    List<T> result = null;
    try {
      manager = FactoryHolder.getFactory().createEntityManager();
      manager.getTransaction().begin();
      result = executable.execute(manager);
      manager.getTransaction().commit();
      return result;
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
  }

}
