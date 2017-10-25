package org.benjamin.filetree.model.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;

/**
 * Quick and dirty implementation of JpaExecutionContainer, experimental.
 * @author benjamin
 *
 */
public class JpaExecutionContainerImpl implements JpaExecutionContainer {

  @Override
  public <T> T execute(SingleResultExecutable<T> executable) {
    EntityManager manager = null;
    EntityTransaction transaction = null;
    T t = null;
    try {
      manager = FactoryHolder.getFactory().createEntityManager();
      transaction = manager.getTransaction();
      transaction.begin();
      t = executable.execute(manager);
      transaction.commit();
    } catch (HibernateException e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
    return t;
  }

  @Override
  public <T> Collection<T> excuteListResult(CollectionResultExecutable<T> executable) {
    EntityManager manager = null;
    EntityTransaction transaction = null;
    Collection<T> result = null;
    try {
      manager = FactoryHolder.getFactory().createEntityManager();
      transaction = manager.getTransaction();
      transaction.begin();
      result = executable.execute(manager);
      transaction.commit();
    } catch (HibernateException e) {
      e.printStackTrace();
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
    return result;
  }

}
