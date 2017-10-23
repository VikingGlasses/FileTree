package org.benjamin.filetree.model.repository;

import java.util.List;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;


public class LeafRepositoryImpl implements LeafRepositoryI {

  TestExe exe = new TestExe();
  
  @Override
  public Leaf add(String name, int parentId) {
    Leaf leaf = new Leaf();
    leaf.setName(name);
    return exe.execute(manager -> {
      Branch parent = manager.find(Branch.class, parentId);
      leaf.setParent(parent);
      manager.persist(leaf);
      return leaf;
    });
  }

  @Override
  public Leaf get(int id) {
    return exe.execute(manager -> manager.find(Leaf.class, id));
  }

  @Override
  public boolean changeName(int id, String newName) {
    return exe.executeBooleanResult(manager -> {
      Leaf leaf = manager.find(Leaf.class, id);
      leaf.setName(newName);
      return true;
    });
  }

  @Override
  public boolean delete(int id) {
    return exe.executeBooleanResult(manager -> {
      Leaf leaf = manager.find(Leaf.class, id);
      manager.remove(leaf);
      return true;
    });
  }

  @Override
  public List<Leaf> search(int id, String text) {
    String query = "FROM Leaf as l WHERE l.parent.id=:id AND l.name like '%" + text + "%'";
    return exe.excuteListResult(manager -> {
      return manager.createQuery(query, Leaf.class)
                    .setParameter("id", id)
                    .getResultList();
    });
  }

}
