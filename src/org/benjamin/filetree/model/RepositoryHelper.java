package org.benjamin.filetree.model;

import java.util.Set;

public interface RepositoryHelper {

  TreeComponent createNewBranch(int parentId);

  TreeComponent createNewLeaf(int parentId);

  boolean removeLeaf(int id);

  boolean removeBranch(int id);

  boolean renameBranch(int id, String newName);

  boolean renameLeaf(int id, String newName);

  Set<TreeComponent> search(int rootId, String text);

  Set<TreeComponent> getChildrenFrom(int id);

}
