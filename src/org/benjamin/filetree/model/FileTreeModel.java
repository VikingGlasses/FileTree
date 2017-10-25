package org.benjamin.filetree.model;

import java.util.Set;

import org.benjamin.filetree.controller.ComponentTypeEnum;

/**
 * Interface for separating the controller and model.
 * @author benjamin
 *
 */
public interface FileTreeModel {

  /**
   * Go to previous "node".
   */
  void back();

  /**
   * Check if it's possible to back.
   * @return True if it's possible to back.
   */
  boolean backIsEmpty();

  /**
   * Go to the "node" backed from, i.e. forward.
   */
  void forward();

  /**
   * Check if it's possible to go forward
   * @return True if it's possible to go forward.
   */
  boolean forwardIsEmpty();

  /**
   * Creates a new branch.
   * @return A TreeComponent with the new branch's properties.
   */
  TreeComponent createNewBranch();

  /**
   * Creates a new leaf.
   * @return A TreeComponent with the new leaf's properties.
   */
  TreeComponent createNewLeaf();

  /**
   * Removes the "node" of 'type' with 'identifier'.
   * @param type The nodes type.
   * @param identifier The nodes identifier.
   */
  void remove(ComponentTypeEnum type, int identifier);

  /**
   * Returns the current tree components.
   * @return Current tree components.
   */
  Set<TreeComponent> getCurrentTreeComponents();

  /**
   * Renames a "node".
   * @param type The nodes type.
   * @param identifier The nodes identifier.
   * @param newName The nodes new name.
   */
  void rename(ComponentTypeEnum type, int identifier, String newName);

  /**
   * Goes to the branch with the id 'identifier'.
   * @param identifier The branch id.
   */
  void goTo(int identifier);

  /**
   * Searches for 'nodes' containing text.
   * @param text The text to search for.
   */
  void search(String text);

}
