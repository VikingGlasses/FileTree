package org.benjamin.filetree.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

import org.benjamin.filetree.controller.ComponentEnum;

public class FileTree implements FileTreeModel {
  
  private Deque<History<Integer>> back = new ArrayDeque<>();
  private Deque<History<Integer>> forward = new ArrayDeque<>();
  private History<Integer> current;
  private Set<TreeComponent> currentComponents = new TreeSet<>();
  
  private RepositoryHelper repoHelper = new MyRepositoryHelper();
  
  public FileTree() {
    current = createHistory(1);
    updateCurrentComponents();
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
  public void remove(ComponentEnum type, int identifier) {
    switch (type) {
      case BRANCH:
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
  public void rename(ComponentEnum type, int identifier, String newName) {
    switch (type) {
      case BRANCH:
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
    /* equivalent to above if statement
     if (current.isSearch()) {
      if (!current.getText().equals(text)) {
        back.push(current);
        current = new History<Integer>(current.getId(), text);
        currentComponents = repoHelper.search(current.getId(), text);
      }
    } else {
      back.push(current);
      current = new History<Integer>(current.getId(), text);
      currentComponents = repoHelper.search(current.getId(), text);
    }
     */
  }

}
