package org.benjamin.filetree.model.repository;

import java.util.Collection;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;


public class BranchRepositoryImpl implements BranchRepositoryI {
  
  JpaExecutionContainer container;

  public BranchRepositoryImpl(JpaExecutionContainer container) {
    this.container = container;
  }

  @Override
  public Branch get(int id) {
    return container.execute(manager -> {
      return manager.find(Branch.class, id);
    });
  }

  @Override
  public Branch add(String name, int parentId) {
    Branch branch = new Branch();
    branch.setName(name);
    
    return container.execute(manager -> {
      Branch parent = manager.find(Branch.class, parentId);
      branch.setParent(parent);
      manager.persist(branch);
      return branch;
    });
  }

  @Override
  public boolean changeName(int id, String newName) {
    return container.execute(manager -> {
      Branch branch = manager.find(Branch.class, id);
      branch.setName(newName);
      return true;
    });
  }

  @Override
  public boolean move(int id, int newParentId) {
    return container.execute(manager -> {
      Branch branch = manager.find(Branch.class, id);
      Branch parent = manager.find(Branch.class, newParentId);
      branch.setParent(parent);
      return true;
    });
  }

  @Override
  public boolean delete(int id) {
    return container.execute(manager -> {
      Branch branch = manager.find(Branch.class, id);
      manager.remove(branch);
      return true;
    });
  }

  @Override
  public Collection<Branch> getChildrenFrom(int id) {
    return container.excuteListResult(manager -> {
      Branch branch = manager.find(Branch.class, id);
      return branch.getChildren();
    });
  }

  @Override
  public Collection<Leaf> getLeafsFrom(int id) {
    return container.excuteListResult(manager -> {
      Branch branch = manager.find(Branch.class, id);
      return branch.getLeafs();
    });
  }

  @Override
  public Collection<Branch> search(int id, String text) {
    String query = "FROM Branch as b WHERE b.parent.id=:id AND b.name like '%" + text + "%'";
    return container.excuteListResult(manager -> {
      return manager.createQuery(query, Branch.class)
                    .setParameter("id", id)
                    .getResultList();
    });
  }

}
