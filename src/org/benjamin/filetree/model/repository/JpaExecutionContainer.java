package org.benjamin.filetree.model.repository;

import java.util.Collection;

public interface JpaExecutionContainer {

  <T> T execute(SingleResultExecutable<T> executable);

  <T> Collection<T> excuteListResult(CollectionResultExecutable<T> executable);

}
