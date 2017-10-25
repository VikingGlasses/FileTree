package org.benjamin.filetree.model.repository;

import java.util.Collection;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

/**
 * Repository interface for DB communication with the Branch entity.
 * 
 * @see Branch
 * @author benjamin
 *
 */
public interface BranchRepositoryI {
  
  /**
   * Returns the branch with the matching id or null.
   * @param id
   * @return
   */
  Branch get(int id);
  
  /**
   * Creates and returns a new branch with the given name and parent.
   * @param name
   * @param parentId The id of the branch the new branch should be attached to.
   * @return
   */
  Branch add(String name, int parentId);
  
  /**
   * Sets the branch with the matching id to the new name.
   * @param id
   * @param newName
   * @return True if name change was successful.
   */
  boolean changeName(int id, String newName);
  
  /**
   * Changes a branch parent to the branch with newParentId.
   * @param id
   * @param newParentId
   * @return True if successful.
   */
  boolean move(int id, int newParentId);
  
  /**
   * Deletes the branch with the given id.
   * @param id
   * @return True if successful.
   */
  boolean delete(int id);
  
  /**
   * Returns direct sub-branches from the branch with id.
   * @param id
   * @return
   */
  Collection<Branch> getSubBranchesFrom(int id);
  
  /**
   * Returns the leafs that are connected to the branch with id.
   * @param id
   * @return
   */
  Collection<Leaf> getLeafsFrom(int id);

  /**
   * Searching if the branch with id have any branches connected to it containing text.
   * @param id
   * @param text
   * @return An presumably unsorted collection of branches containing text.
   */
  Collection<Branch> search(int id, String text);

}
