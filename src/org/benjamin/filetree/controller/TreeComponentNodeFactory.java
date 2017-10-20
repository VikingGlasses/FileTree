package org.benjamin.filetree.controller;

import org.benjamin.filetree.model.TreeComponent;

public interface TreeComponentNodeFactory {

  TreeComponentNode createBranchNode(int id, String text);

  TreeComponentNode createLeafNode(int id, String text);

  TreeComponentNode createNode(TreeComponent treeComponent);

}
