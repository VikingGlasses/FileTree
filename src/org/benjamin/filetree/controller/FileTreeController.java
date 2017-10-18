package org.benjamin.filetree.controller;

import java.util.ArrayList;
import java.util.List;

import org.benjamin.filetree.model.TreeComponent;
import org.benjamin.filetree.model.FileTreeModel;
import org.benjamin.filetree.model.entity.Branch;
import org.benjamin.filetree.model.entity.Leaf;
import org.benjamin.filetree.model.repository.BranchRepositoryI;
import org.benjamin.filetree.model.repository.BranchRepositoryImpl;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FileTreeController {

  @FXML private Button backBtn;
  @FXML private Button forwardBtn;
  @FXML private TextField searchField;
  @FXML private FlowPane folderView;
  @FXML private Button addBranchBtn;
  @FXML private Button addLeafBtn;
  @FXML private Button renameBtn;
  @FXML private Button deleteBtn;
  
  private FileTreeModel model;
  
  @FXML
  public void initialize() {
    BranchRepositoryI repo = new BranchRepositoryImpl();
    Branch branch = repo.get(1);
    List<Label> labels = new ArrayList<>();
    for (Branch b : branch.getChildren()) {
      Label l = createLabel(b.getName(), "branch");
      labels.add(l);
    }
    for (Leaf leaf : branch.getLeafs()) {
      Label l = createLabel(leaf.getName(), "leaf");
      labels.add(l);
    }
    ObservableList<Node> children = folderView.getChildren();
    children.setAll(labels);
  }

  private Label createLabel(String name, String className) {
    Label label = new Label(name);
    label.getStyleClass().add(className);
    label.setFocusTraversable(true);
    return label;
  }

  @FXML 
  public void back() {
    model.back();
    if (model.backIsEmpty()) {
      backBtn.setDisable(true);
    }
  }

  @FXML 
  public void forward() {
    model.forward();
    if (model.forwardIsEmpty()) {
      forwardBtn.setDisable(true);
    }
  }

  @FXML 
  public void search(KeyEvent event) {
//    boolean b = event.getCode() == KeyCode.ENTER;
  }

  @FXML 
  public void addNewBranch() {
    TreeComponent c = model.createNewBranch();
    // TODO: Create node for component
    // add to folderView
    // select node
    // rename();
  }

  @FXML 
  public void AddNewLeaf() {
    TreeComponent c = model.createNewLeaf();
    // TODO: Create node for component
    // add to folderView
    // select node
    // rename();
  }

  @FXML 
  public void rename() {
    // TODO: implement rename method
    // get selected from folderView
    // start renaming
    // set new name on corresponding object
  }

  @FXML 
  public void delete() {
    // TODO: implement delete method
    // get selected from folderView
    // 
  }
  
}
