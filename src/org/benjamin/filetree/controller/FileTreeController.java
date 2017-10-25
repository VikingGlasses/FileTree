package org.benjamin.filetree.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.benjamin.filetree.model.TreeComponent;
import org.benjamin.filetree.model.TreeComponentNode;
import org.benjamin.filetree.model.FileTree;
import org.benjamin.filetree.model.FileTreeModel;
import org.benjamin.filetree.model.MyRepositoryHelper;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * The FXML Controller implementation
 * @author benjamin
 *
 */
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
  
  private FileTreeModel model = new FileTree(new MyRepositoryHelper(), 1);
  private TreeComponentNodeFactory nodeFactory = new TreeComponentNodeFactory();
  private MySingleSelectionModel<TreeComponentNode> selection =  new MySingleSelectionModel<>();
  private EventHandler<? super MouseEvent> treeNodeClickHandler = this::treeNodeClicked;
  
  public FileTreeController() {}

  /**
   * Initializes view content.
   */
  @FXML
  public void initialize() {
    renameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      // if renameField loses focus
      if (!newValue.booleanValue()) {
        commitRenaming();
      }
    });
    folderView.getStyleClass().add("folderView");
    updateFolderView();
    updateSelectionBasedFunctionallity();
    updateBackForwardDisabled();
  }

  /**
   * Displays the previous set of nodes in your history.
   */
  @FXML 
  public void back() {
    model.back();
    updateFolderView();
    updateBackForwardDisabled();
    selection.clearSelection();
    updateSelectionBasedFunctionallity();
  }

  /**
   * Displays the next set of node in your history.
   */
  @FXML 
  public void forward() {
    model.forward();
    updateFolderView();
    updateBackForwardDisabled();
    selection.clearSelection();
    updateSelectionBasedFunctionallity();
  }
  
  /**
   * Performs and displays a search.
   */
  @FXML
  public void search() {
    model.search(searchField.getText());
    selection.clearSelection();
    updateFolderView();
    updateBackForwardDisabled();
    updateSelectionBasedFunctionallity();
  }

  /**
   * Creates a new branch.
   */
  @FXML 
  public void createNewBranch() {
    selection.clearSelection();
    TreeComponent c = model.createNewBranch();
    updateFolderView();
    TreeComponentNode node = findNode(c);
    selection.select(node);
    updateSelectionBasedFunctionallity();
    rename();
  }

  /**
   * Creates a new leaf.
   */
  @FXML 
  public void createNewLeaf() {
    selection.clearSelection();
    TreeComponent c = model.createNewLeaf();
    updateFolderView();
    TreeComponentNode node = findNode(c);
    selection.select(node);
    updateSelectionBasedFunctionallity();
    rename();
  }

  /**
   * Starts a renaming event/process for the selected node, 
   * which makes it possible to edit a nodes name.
   */
  @FXML 
  public void rename() {
    if (!selection.isEmpty()) {
      TreeComponentNode node = selection.getSelectedItem();
      renameField.setVisible(true);
      renameField.setText(node.getText());
      renameField.selectAll();
      renameField.requestFocus();
    }
  }

  /**
   * Deletes the selected node.
   */
  @FXML 
  public void delete() {
    if (!selection.isEmpty()) {
      TreeComponentNode node = selection.getSelectedItem();
      model.remove(node.getType(), node.getIdentifier());
      folderView.getChildren().remove(node);
      selection.clearSelection();
      updateSelectionBasedFunctionallity();
    }
  }

  /**
   * Commits a new name to a node. This ends the renaming process.
   */
  @FXML 
  public void commitRenaming() {
    TreeComponentNode node = selection.getSelectedItem();
    // set new name on corresponding object
    model.rename(node.getType(), node.getIdentifier(), renameField.getText());
    renameField.setVisible(false);
    updateFolderView();
  }

  private void treeNodeClicked(MouseEvent event) {
    // TODO type check
    TreeComponentNode source = (TreeComponentNode) event.getSource();
    selection.select(source);
    source.requestFocus();
    MouseButton button = event.getButton();
    int clickCount = event.getClickCount();
    if (button == MouseButton.PRIMARY && clickCount == 2 && source.getType() == ComponentTypeEnum.COMPOSITE) {
      model.goTo(source.getIdentifier());
      selection.clearSelection();
      updateFolderView();
      updateBackForwardDisabled();
    }
    updateSelectionBasedFunctionallity();
    event.consume();
  }

  private void updateFolderView() {
    Set<TreeComponent> models = model.getCurrentTreeComponents();
    List<TreeComponentNode> nodes = new ArrayList<>(models.size());
    for (TreeComponent treeComponent : models) {
      TreeComponentNode node = nodeFactory.createNode(treeComponent);
      node.getStyleClass().addAll("treeNode", node.getType().toString());
      node.setOnMouseClicked(treeNodeClickHandler);
      nodes.add(node);
    }
    folderView.getChildren().setAll(nodes);
  }

  private void updateSelectionBasedFunctionallity() {
    deleteBtn.setVisible(!selection.isEmpty());
    renameBtn.setVisible(!selection.isEmpty());
  }

  private void updateBackForwardDisabled() {
    forwardBtn.setDisable(model.forwardIsEmpty());
    backBtn.setDisable(model.backIsEmpty());
  }

  private TreeComponentNode findNode(TreeComponent c) {
    TreeComponentNode node = nodeFactory.createNode(c);
    ObservableList<Node> children = folderView.getChildrenUnmodifiable();
    int indexOf = children.indexOf(node);
    if (!(indexOf < 0)) {
      return (TreeComponentNode) children.get(indexOf);
    }
    return null;
  }
  
}
