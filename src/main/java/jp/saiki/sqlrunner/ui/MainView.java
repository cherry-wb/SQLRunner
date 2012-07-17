/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabBuilder;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

/**
 *
 * @author saiki
 */
public class MainView implements Initializable {

  @FXML
  private MenuBar menus;

  @FXML
  private TabPane tabs;

  @FXML
  private void handleNewTabAction(ActionEvent event) {
    try {
      AnchorPane contents = FXMLLoader.load(this.getClass().getResource("SQLEditor.fxml"));
      Tab editorTab = TabBuilder.create().content( contents ).build();
      editorTab.setText( "SQLEditor" + String.valueOf(tabs.getTabs().size() + 1) );
      tabs.getTabs().add(editorTab);

    } catch (IOException ex) {
      Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void handleCloseAction(ActionEvent event) {
    menus.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
