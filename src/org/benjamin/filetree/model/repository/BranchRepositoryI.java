package org.benjamin.filetree.model.repository;

import java.util.Set;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

public interface BranchRepositoryI {
  
  Branch get(int id);
  
  Branch add(String name, Branch parent);
  
  void changeName(int id, String name);
  
  void move(Branch branch, Branch newParent);
  
  void delete(int id);
  
  Set<Branch> getChildrenFrom(Branch branch);
  
  Set<Leaf> getLeafsFrom(Branch branch);

}
