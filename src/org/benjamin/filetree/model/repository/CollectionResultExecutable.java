package org.benjamin.filetree.model.repository;

import java.util.Collection;

import javax.persistence.EntityManager;

public interface CollectionResultExecutable<T> {

  Collection<T> execute(EntityManager manager);

}
