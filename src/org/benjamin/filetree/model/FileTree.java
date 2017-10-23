package org.benjamin.filetree.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

import org.benjamin.filetree.controller.ComponentEnum;

public class FileTree implements FileTreeModel {
  
  private Deque<Integer> back = new ArrayDeque<>();
  private Deque<Integer> forward = new ArrayDeque<>();
  private int current;
  private Set<TreeComponent> currentComponents = new TreeSet<>();
  
  private RepositoryHelper repoHelper = new MyRepositoryHelper();
  
  public FileTree() {
    current = 1;
    updateCurrentComponents();
  }

  private void updateCurrentComponents() {
    // TODO set current components
    currentComponents = repoHelper.getChildrenFrom(current);
  }
  
  @Override
  public void back() {
    if (!back.isEmpty()) {
      // TODO fix pot. bug pushing current
      forward.push(current);
      goTo(back.pop());
    }
  }

  @Override
  public boolean backIsEmpty() {
    return back.isEmpty();
  }

  @Override
  public void forward() {
    if (!forward.isEmpty()) {
      // TODO fix pot. bug pushing current
      back.push(current);
      goTo(forward.pop());
    }
  }

  @Override
  public boolean forwardIsEmpty() {
    return forward.isEmpty();
  }

  @Override
  public TreeComponent createNewBranch() {
    TreeComponent newBranch = repoHelper.createNewBranch(current);
    updateCurrentComponents();
    return newBranch;
  }

  @Override
  public TreeComponent createNewLeaf() {
    TreeComponent newLeaf = repoHelper.createNewLeaf(current);
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
        // TODO do something?
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
        // TODO do something?
        break;
    }
    updateCurrentComponents();
  }

  @Override
  public void goTo(int identifier) {
    // TODO check so id is valid
    back.push(current);
    current = identifier;
    updateCurrentComponents();
  }

  @Override
  public void search(String text) {
    currentComponents = repoHelper.search(current, text);
  }

}
