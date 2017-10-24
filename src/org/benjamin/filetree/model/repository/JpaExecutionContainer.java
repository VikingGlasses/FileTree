package org.benjamin.filetree.model.repository;

import java.util.List;
import java.util.Set;

public interface JpaExecutionContainer {

  <T> T execute(SingleResultExecutable<T> executable);

  <T> List<T> excuteListResult(ListResultExecutable<T> executable);

  <T> Set<T> excuteSetResult(SetResultExecutable<T> executable);

}
