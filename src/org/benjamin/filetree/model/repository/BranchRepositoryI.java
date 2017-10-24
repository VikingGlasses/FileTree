package org.benjamin.filetree.model.repository;

import java.util.Collection;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

public interface BranchRepositoryI {
  
  Branch get(int id);
  
  Branch add(String name, int parentId);
  
  boolean changeName(int id, String name);
  
  boolean move(int id, int newParentId);
  
  boolean delete(int id);
  
  Collection<Branch> getChildrenFrom(int id);
  
  Collection<Leaf> getLeafsFrom(int id);

  Collection<Branch> search(int id, String text);

}
