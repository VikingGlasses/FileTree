package org.benjamin.filetree.model;

import java.util.Comparator;

public class TreeComponentComperator implements Comparator<TreeComponent> {

  @Override
  public int compare(TreeComponent o1, TreeComponent o2) {
    int i = o1.getComponentType().compareTo(o2.getComponentType());
    if (i == 0) {
      i = o1.getText().compareTo(o2.getText());
      if (i == 0) {
        i = Integer.compare(o1.getIdentifier(), o2.getIdentifier());
      }
    }
    return i;
  }

}
