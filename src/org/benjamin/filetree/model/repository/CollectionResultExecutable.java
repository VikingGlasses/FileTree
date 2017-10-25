package org.benjamin.filetree.model.repository;

import java.util.Collection;

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
public interface CollectionResultExecutable<T> {

  /**
   * Returns a collection result.
   * @param manager
   * @return
   */
  Collection<T> execute(EntityManager manager);

}
