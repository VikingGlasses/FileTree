package org.benjamin.filetree.model.repository;

import java.util.Collection;

import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;

/**
 * Repository interface for DB communication with the Leaf entity.
 * 
 * @see Leaf
 * @author benjamin
 *
 */
public interface LeafRepositoryI {

  /**
   * Adds new leaf with the given name and attached to parent (Branch).
   * @param name
   * @param parentId The id of the branch the new leaf should be attached to.
   * @return The new leaf
   * @see Branch
   */
  Leaf add(String name, int parentId);
  
  /**
   * Returns the leaf with the matching id.
   * @param id
   * @return
   */
  Leaf get(int id);

  /**
   * Sets the leaf with the matching id to the new name.
   * @param id
   * @param newName
   * @return True if successful.
   */
  boolean changeName(int id, String newName);
  
  /**
   * Deletes the leaf with the matching id.
   * @param id
   * @return True if Successful.
   */
  boolean delete(int id);

  /**
   * Searches for leafs attached to parent and contains text.
   * @param parentId
   * @param text
   * @return An presumably unsorted collection of leafs containing text.
   */
  Collection<Leaf> search(int parentId, String text);

}
