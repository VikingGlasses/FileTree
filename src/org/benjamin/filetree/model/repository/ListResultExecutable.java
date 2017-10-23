package org.benjamin.filetree.model.repository;

import java.util.List;

import javax.persistence.EntityManager;

public interface ListResultExecutable<T> {

  List<T> execute(EntityManager manager);

}
