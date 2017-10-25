package org.benjamin.filetree.controller;

import org.benjamin.filetree.model.TreeComponent;
import org.benjamin.filetree.model.TreeComponentNode;

/**
 * Simple factory for creating TreeComponentNodes from TreeComponents
 * @see TreeComponentNode
 * @see TreeComponent
 * @author benjamin
 *
 */
public class TreeComponentNodeFactory {

  /**
   * Creates a new TreeComponentNode from a TreeComponent.
   * @param treeComponent
   * @return
   */
  public TreeComponentNode createNode(TreeComponent tc) {
    TreeComponentNode node = new TreeComponentNode();
    node.setIdentifier(tc.getIdentifier());
    node.setText(tc.getText());
    node.setType(tc.getComponentType());
    return node;
  }
  
}
