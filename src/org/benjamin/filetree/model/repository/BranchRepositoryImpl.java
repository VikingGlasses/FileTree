package org.benjamin.filetree.model.repository;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

public class BranchRepositoryImpl implements BranchRepositoryI {

  @Override
  public Branch get(int id) {
    
    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
//    TypedQuery<Branch> q = manager.createQuery("FROM Branch WHERE id<=:id", Branch.class);
//    q.setParameter("id", id);
    Branch branch;
    try {
    branch = manager.find(Branch.class, id);
    } finally {
      manager.close();
    }
    return branch;
//    return q.getSingleResult();
  }

  @Override
  public Branch add(String name, int parentId) {
    Branch branch = new Branch();
    branch.setName(name);
    
    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    Branch parent = manager.getReference(Branch.class, parentId);
    branch.setParent(parent);
    manager.persist(branch);
    return branch;
  }

  @Override
  public boolean changeName(int id, String name) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    Branch branch = manager.find(Branch.class, id);
    branch.setName(name);
    
    return true;
  }

  @Override
  public void move(Branch branch, Branch newParent) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    branch.setParent(newParent);
  }

  @Override
  public boolean delete(int id) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    Branch branch = manager.find(Branch.class, id);
    manager.remove(branch);
    return true;
  }

  @Override
  public Set<Branch> getChildrenFrom(int id) {
    EntityManager manager = null;
    Set<Branch> children;
    try {
      EntityManagerFactory factory = FactoryHolder.getFactory();
      manager = factory.createEntityManager();
      manager.getTransaction().begin();
      Branch branch = manager.find(Branch.class, id);
      children = branch.getChildren();
      manager.getTransaction().commit();
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
    return children;
  }

  @Override
  public Set<Leaf> getLeafsFrom(int id) {
    EntityManager manager = null;
    Set<Leaf> children;
    try {
      EntityManagerFactory factory = FactoryHolder.getFactory();
      manager = factory.createEntityManager();
      manager.getTransaction().begin();
      Branch branch = manager.find(Branch.class, id);
      children = branch.getLeafs();
      manager.getTransaction().commit();
    } finally {
      if (manager != null) {
        manager.close();
      }
    }
    return children;
  }

  @Override
  public Set<Branch> search(int id, String text) {
    // TODO Auto-generated method stub
    return null;
  }

}
