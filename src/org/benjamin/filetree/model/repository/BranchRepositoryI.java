package org.benjamin.filetree.model.repository;

import java.util.List;
import java.util.Set;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

public interface BranchRepositoryI {
  
  Branch get(int id);
  
  Branch add(String name, int parentId);
  
  boolean changeName(int id, String name);
  
  void move(Branch branch, Branch newParent);
  
  boolean delete(int id);
  
  Set<Branch> getChildrenFrom(int id);
  
  Set<Leaf> getLeafsFrom(int id);

  List<Branch> search(int id, String text);

}
