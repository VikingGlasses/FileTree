package org.benjamin.filetree.model.repository;

import java.util.Collection;

/**
 * JPA Execution container is designed for executing JPA code within a try-catch-finally block. 
 * TODO add callback functionality, possible as new interface.
 * @author benjamin
 *
 */
public interface JpaExecutionContainer {

  /**
   * Executes the single result executable within a try catch and returns a single result.
   * 
   * @param executable 
   * @return
   */
  <T> T execute(SingleResultExecutable<T> executable);

  /**
   * Executes the single result executable within a try catch and returns a collection of results.
   * 
   * @param executable
   * @return
   */
  <T> Collection<T> excuteListResult(CollectionResultExecutable<T> executable);

}
