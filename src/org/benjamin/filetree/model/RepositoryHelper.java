package org.benjamin.filetree.model;

import java.util.Set;

/**
 * Separates the model from the underlying repositories.
 * @author benjamin
 *
 */
public interface RepositoryHelper {

  /**
   * Creates a new branch as a child to the given parent. 
   * 
   * @param parentId The new branch's parent.
   * @return The new branch as a TreeComponent.
   */
  TreeComponent createNewBranch(int parentId);

  /**
   * Creates a new leaf as a child to the given parent.
   * 
   * @param parentId The new leaf's parent.
   * @return The new leaf as a TreeComponent.
   */
  TreeComponent createNewLeaf(int parentId);

  /**
   * Removes the leaf with the given id.
   * 
   * @param id The leaf's id.
   * @return True if the leaf was removed.
   */
  boolean removeLeaf(int id);

  /**
   * Removes the branch with the given id.
   * 
   * @param id The branch's id.
   * @return True if the branch was removed.
   */
  boolean removeBranch(int id);

  /**
   * Sets a branch's name to the name.
   * 
   * @param id The branch's id.
   * @param newName The branch's new name.
   * @return True if the branch was renamed.
   */
  boolean renameBranch(int id, String newName);

  /**
   * Sets a leaf's name to the name.
   * 
   * @param id The leaf's id.
   * @param newName The leaf's new name.
   * @return True if the leaf was renamed.
   */
  boolean renameLeaf(int id, String newName);

  /**
   * Searches for any TreeComponent containing text that is a part of the subtree to the given root.
   * The root component is excluded from the search.
   * 
   * @param rootId The entry point of the search. (excluded)
   * @param text The text to search for.
   * @return A sorted set of TreeComponents that are a part of the subtree with the 
   *         root as rootId which contain text.
   */
  Set<TreeComponent> search(int rootId, String text);

  /**
   * Returns the children to a branch as a sorted set of TreeComponents.
   * 
   * @param id The branch's id.
   * @return The children to a branch as a sorted set of TreeComponents.
   */
  Set<TreeComponent> getChildrenFrom(int id);

}
