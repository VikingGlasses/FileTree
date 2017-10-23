package org.benjamin.filetree.model.repository;

import javax.persistence.EntityManager;

public interface BooleanResultExecutable {

  boolean execute(EntityManager manager);

}
