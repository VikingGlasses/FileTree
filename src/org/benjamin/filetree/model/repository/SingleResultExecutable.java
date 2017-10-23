package org.benjamin.filetree.model.repository;

import javax.persistence.EntityManager;

public interface SingleResultExecutable<T> {

  T execute(EntityManager manager);

}
