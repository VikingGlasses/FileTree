package org.benjamin.filetree.model;

import java.util.List;

import org.benjamin.filetree.controller.ComponentEnum;

public interface FileTreeModel {

  void back();

  boolean backIsEmpty();

  void forward();

  boolean forwardIsEmpty();

  TreeComponent createNewBranch();

  TreeComponent createNewLeaf();

  void remove(ComponentEnum type, int identifier);

  List<TreeComponent> getTreeComponents();

  void rename(ComponentEnum type, int identifier, String text);

  void goTo(int identifier);

}
