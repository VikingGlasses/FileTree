package org.benjamin.filetree.controller;

import org.benjamin.filetree.model.TreeComponent;

public class TreeComponentNodeFactoryImpl implements TreeComponentNodeFactory {

  private void setCssClass(TreeComponentNode node, ComponentEnum type) {
    node.getStyleClass().add(type.toString());
  }
  
  @Override
  public TreeComponentNode createBranchNode(int id, String text) {
    TreeComponentNode node = new TreeComponentNode();
    node.setIdentifier(id);
    node.setText(text);
    node.setType(ComponentEnum.BRANCH);
    setCssClass(node, node.getType());
    return node;
  }

  @Override
  public TreeComponentNode createLeafNode(int id, String text) {
    TreeComponentNode node = new TreeComponentNode();
    node.setIdentifier(id);
    node.setText(text);
    node.setType(ComponentEnum.LEAF);
    setCssClass(node, node.getType());
    return node;
  }

  @Override
  public TreeComponentNode createNode(TreeComponent tc) {
    TreeComponentNode node = new TreeComponentNode();
    node.setIdentifier(tc.getIdentifier());
    node.setText(tc.getText());
    node.setType(tc.getComponentType());
    setCssClass(node, node.getType());
    return node;
  }

}
