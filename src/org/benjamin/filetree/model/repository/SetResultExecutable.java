package org.benjamin.filetree.model.repository;

import java.util.Set;

import javax.persistence.EntityManager;

public interface SetResultExecutable<T> {

  Set<T> execute(EntityManager manager);

}
