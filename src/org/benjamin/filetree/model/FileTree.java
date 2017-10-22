package org.benjamin.filetree.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.benjamin.filetree.controller.ComponentEnum;

public class FileTree implements FileTreeModel {
  
  private Deque<Integer> back = new ArrayDeque<>();
  private Deque<Integer> forward = new ArrayDeque<>();
  private int current;
  private List<TreeComponent> currentComponents;

  private void updateCurrentComponents() {
    // TODO set current components
  }
  
  @Override
  public void back() {
    if (!back.isEmpty()) {
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
    // TODO create new branch
    return null;
  }

  @Override
  public TreeComponent createNewLeaf() {
    // TODO create new leaf
    return null;
  }

  @Override
  public void remove(ComponentEnum type, int identifier) {
    // TODO Implement remove method
  }

  @Override
  public List<TreeComponent> getCurrentTreeComponents() {
    return currentComponents;
  }

  @Override
  public void rename(ComponentEnum type, int identifier, String newName) {
    // TODO Implement rename method
  }

  @Override
  public void goTo(int identifier) {
    // TODO Implement go to method
    // TODO check so id is valid
    current = identifier;
    updateCurrentComponents();
  }

  @Override
  public void search(String text) {
    // TODO Implement search
  }

}
