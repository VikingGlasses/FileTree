package org.benjamin.filetree.model.repository;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;
import org.hibernate.Hibernate;

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
  public Branch add(String name, Branch parent) {
    Branch branch = new Branch();
    branch.setName(name);
    branch.setParent(parent);
    
    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    manager.persist(branch);
    return branch;
  }

  @Override
  public void changeName(int id, String name) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    Branch branch = manager.find(Branch.class, id);
    branch.setName(name);
  }

  @Override
  public void move(Branch branch, Branch newParent) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    branch.setParent(newParent);
  }

  @Override
  public void delete(int id) {

    EntityManagerFactory factory = FactoryHolder.getFactory();
    EntityManager manager = factory.createEntityManager();
    
    Branch branch = manager.find(Branch.class, id);
    manager.remove(branch);
  }

  @Override
  public Set<Branch> getChildrenFrom(Branch branch) {
    EntityManager manager = null;
    Set<Branch> children;
    try {
      EntityManagerFactory factory = FactoryHolder.getFactory();
      manager = factory.createEntityManager();
      manager.getTransaction().begin();
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
  public Set<Leaf> getLeafsFrom(Branch branch) {
    // TODO Auto-generated method stub
    return null;
  }

}
