package org.benjamin.filetree.model.repository;

import java.util.Set;

import org.benjamin.filetree.model.entity.Leaf;

public interface LeafRepositoryI {

  Leaf add(String name, int parentId);
  
  Leaf get(int id);

  boolean changeName(int id, String newName);
  
  boolean delete(int id);

  Set<Leaf> search(int id, String text);

}
