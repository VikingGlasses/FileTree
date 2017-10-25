package org.benjamin.filetree;

import org.benjamin.filetree.model.repository.FactoryHolder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main, Application start point.
 * @author benjamin
 *
 */
public class FileTreeApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FactoryHolder.getFactory();
    primaryStage.setOnCloseRequest(event -> FactoryHolder.getFactory().close());
    
    Parent root = FXMLLoader.load(getClass().getResource("FileTree.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("FileTree.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
