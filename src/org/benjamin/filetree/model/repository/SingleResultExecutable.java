package org.benjamin.filetree.model.repository;

import javax.persistence.EntityManager;

/**
 * Intended to be used solely as a functional interface for JpaExecutionContainer.
 * 
 * @see JpaExecutionContainer
 * @author benjamin
 *
 * @param <T>
 */
@FunctionalInterface
public interface SingleResultExecutable<T> {

  /**
   * Returns a single result.
   * @param manager
   * @return
   */
  T execute(EntityManager manager);

}
