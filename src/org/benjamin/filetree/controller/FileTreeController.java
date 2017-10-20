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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class FileTreeController {

  @FXML private Button backBtn;
  @FXML private Button forwardBtn;
  @FXML private TextField searchField;
  @FXML private FlowPane folderView;
  @FXML private Button addBranchBtn;
  @FXML private Button addLeafBtn;
  @FXML private Button renameBtn;
  @FXML private Button deleteBtn;
  @FXML private TextField renameField;
  
  private FileTreeModel model;
  private TreeComponentNodeFactory nodeFactory;
  private SelectionModel<TreeComponentNode> selection = new MySelectionModel<>();
  private EventHandler<? super MouseEvent> treeNodeClickHandler = this::treeNodeClicked;
  
  public FileTreeController() {
    // TODO init fields
  }
  
  @FXML
  public void initialize() {
    // TODO: remove
    BranchRepositoryI repo = new BranchRepositoryImpl();
    Branch branch = repo.get(1);
    List<Label> labels = new ArrayList<>();
    for (Branch b : branch.getChildren()) {
      Label l = createLabel(b.getName(), "branch");
      labels.add(l);
      l.setOnMouseClicked(treeNodeClickHandler);
    }
    for (Leaf leaf : branch.getLeafs()) {
      Label l = createLabel(leaf.getName(), "leaf");
      labels.add(l);
      l.setOnMouseClicked(treeNodeClickHandler);
    }
    ObservableList<Node> children = folderView.getChildren();
    children.setAll(labels);
//    updateFolderView();
    renameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      // if renameField loses focus
      if (!newValue.booleanValue()) {
        commitRenaming();
      }
    });
  }
  
  public void treeNodeClicked(MouseEvent event) {
    TreeComponentNode source = (TreeComponentNode) event.getSource();
    selection.SetSelected(source);
    MouseButton button = event.getButton();
    int clickCount = event.getClickCount();
    if (button == MouseButton.PRIMARY && clickCount == 2 && source.getType() == ComponentEnum.COMPOSITE) {
      model.goTo(source.getIdentifier());
      updateFolderView();
    }
    System.out.println(String.format("Mouse button: %s, clickcount: %d", button.toString(), clickCount));
    event.consume();
  }

  private void updateFolderView() {
    List<TreeComponent> models = model.getTreeComponents();
    List<TreeComponentNode> nodes = new ArrayList<>(models.size());
    for (TreeComponent treeComponent : models) {
      nodes.add(nodeFactory.createNode(treeComponent));
    }
    folderView.getChildren().setAll(nodes);
  }

  @Deprecated
  private Label createLabel(String name, String className) {
    Label label = new Label(name);
    label.getStyleClass().add(className);
    label.setFocusTraversable(true);
    return label;
  }

  @FXML 
  public void back() {
    model.back();
    updateFolderView();
    backBtn.setDisable(model.backIsEmpty());
  }

  @FXML 
  public void forward() {
    model.forward();
    updateFolderView();
    forwardBtn.setDisable(model.forwardIsEmpty());
  }
  
  @FXML
  public void search() {
    System.out.println("search action");
  }

  @FXML 
  public void addNewBranch() {
    TreeComponent c = model.createNewBranch();
    updateFolderView();
    TreeComponentNode node = findMatchingNode(c);
    selection.SetSelected(node);
    rename();
  }

  @FXML 
  public void AddNewLeaf() {
    TreeComponent c = model.createNewLeaf();
    updateFolderView();
    TreeComponentNode node = findMatchingNode(c);
    selection.SetSelected(node);
    rename();
  }

  private TreeComponentNode findMatchingNode(TreeComponent c) {
    TreeComponentNode match = null;
    ObservableList<Node> children = folderView.getChildren();
    for (Node node : children) {
      match = (TreeComponentNode) node;
      if (c.getType() == match.getType() && c.getId() == match.getIdentifier()) {
        break;
      }
    }
    return match;
  }

  @FXML 
  public void rename() {
    if (!selection.isEmpty()) {
      TreeComponentNode node = selection.getSelected();
      // show rename textField
      renameField.setVisible(true);
      // set TextField text to node text
      renameField.setText(node.getText());
      // select all in TextField
      renameField.requestFocus();
      // TODO check selection
      renameField.selectAll();
    }
  }

  @FXML 
  public void delete() {
    if (!selection.isEmpty()) {
      TreeComponentNode node = selection.getSelected();
      model.remove(node.getType(), node.getIdentifier());
      folderView.getChildren().remove(node);
    }
  }

  @FXML 
  public void commitRenaming() {
    TreeComponentNode node = selection.getSelected();
    // set new name on corresponding object
    model.rename(node.getType(), node.getIdentifier(), renameField.getText());
    renameField.setVisible(false);
  }
  
}
