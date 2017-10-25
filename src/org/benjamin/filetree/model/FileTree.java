package org.benjamin.filetree.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import org.benjamin.filetree.controller.ComponentTypeEnum;

/**
 * Implementation of FileTreeModel
 * 
 * Uses Deque's for back and forward with an inner class History to represent 
 * where you are and where you've been.
 * 
 * Depends on a implementation of RepesitoryHelper.
 *  
 * @see FileTreeModel
 * @see Deque
 * @see RepositoryHelper
 */
public class FileTree implements FileTreeModel {
  
  private class History<T> {

    private T id;
    private String text;
    private boolean isSearch;

    private History(T id) {
      this.id = id;
      isSearch = false;
    }
    
    private History(T id, String text) {
      this.id = id;
      this.text = text;
      isSearch = true;
    }

    private T getId() {
      return id;
    }

    private String getText() {
      return text;
    }

    private boolean isSearch() {
      return isSearch;
    }

  }
  
  private Deque<History<Integer>> back = new ArrayDeque<>();
  private Deque<History<Integer>> forward = new ArrayDeque<>();
  private History<Integer> current;
  private Set<TreeComponent> currentComponents;
  
  private RepositoryHelper repoHelper;
  
  /**
   * Initiates the current components to the roots children.
   */
  public FileTree(RepositoryHelper r, int rootId) {
    repoHelper = r;
    current = createHistory(rootId);
    updateCurrentComponents();
  }

  @Override
  public void back() {
    if (!back.isEmpty()) {
      forward.push(current);
      current = back.pop();
      updateCurrentComponents();
    }
  }

  @Override
  public boolean backIsEmpty() {
    return back.isEmpty();
  }

  @Override
  public void forward() {
    if (!forward.isEmpty()) {
      back.push(current);
      current = forward.pop();
      updateCurrentComponents();
    }
  }

  @Override
  public boolean forwardIsEmpty() {
    return forward.isEmpty();
  }

  @Override
  public TreeComponent createNewBranch() {
    TreeComponent newBranch = repoHelper.createNewBranch(current.getId());
    updateCurrentComponents();
    return newBranch;
  }

  @Override
  public TreeComponent createNewLeaf() {
    TreeComponent newLeaf = repoHelper.createNewLeaf(current.getId());
    updateCurrentComponents();
    return newLeaf;
  }

  @Override
  public void remove(ComponentTypeEnum type, int identifier) {
    switch (type) {
      case COMPOSITE:
        repoHelper.removeBranch(identifier);
        break;
      case LEAF:
        repoHelper.removeLeaf(identifier);
        break;
      default:
        break;
    }
    updateCurrentComponents();
  }

  @Override
  public Set<TreeComponent> getCurrentTreeComponents() {
    return currentComponents;
  }

  @Override
  public void rename(ComponentTypeEnum type, int identifier, String newName) {
    switch (type) {
      case COMPOSITE:
        repoHelper.renameBranch(identifier, newName);
        break;
      case LEAF:
        repoHelper.renameLeaf(identifier, newName);
        break;
      default:
        break;
    }
    updateCurrentComponents();
  }

  @Override
  public void goTo(int identifier) {
    back.push(current);
    forward.clear();
    current = createHistory(identifier);
    updateCurrentComponents();
  }

  @Override
  public void search(String text) {
    if (!(current.isSearch() && current.getText().equalsIgnoreCase(text))) {
      forward.clear();
      back.push(current);
      current = createHistory(text);
      currentComponents = repoHelper.search(current.getId(), text);
    }
  }

  private void updateCurrentComponents() {
    if (current.isSearch()) {
      currentComponents =  repoHelper.search(current.getId(), current.getText()); 
    } else {
      currentComponents = repoHelper.getChildrenFrom(current.getId());
    }
  }

  private History<Integer> createHistory(int identifier) {
    return new History<>(identifier);
  }

  private History<Integer> createHistory(String text) {
    return new History<Integer>(current.getId(), text);
  }

}
