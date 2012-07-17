/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.ui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.WindowEvent;
import jp.saiki.sqlrunner.sql.Configuration;
import jp.saiki.sqlrunner.sql.ConnectionManager;

/**
 *
 * @author saiki
 */
public class ConfigurationView implements Initializable {

  @FXML
  private ComboBox<String> name;
  
  @FXML
  private TextField jarFilePath;
  
  @FXML
  private Button select;
  
  @FXML
  private TextField driverClassName;
  
  @FXML
  private TextField connectionString;
  
  @FXML
  private TextField user;
  
  @FXML
  private PasswordField password;
  
  @FXML
  private Button ok;
  
  @FXML
  private Button cancel;

  private Configuration config;

  public Configuration getConfig() {
    return this.config;
  }
  
  @SuppressWarnings("unused")
  @FXML
  private void handleOKAction(ActionEvent event) throws MalformedURLException {
    setConfig();
    ok.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
  }

  @SuppressWarnings("unused")
  @FXML
  private void handleCancelAction(ActionEvent event) {
    cancel.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
  }
  
  @SuppressWarnings("unused")
  @FXML
  private void handleSelectAction(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
    chooser.setTitle("Driver Jar File.");
    chooser.getExtensionFilters().add(new ExtensionFilter("JAR", "*.jar"));
    File selected = chooser.showOpenDialog( null );
    if ( selected != null ) {
      this.jarFilePath.setText(selected.getAbsolutePath());
    }
  }

  @FXML
  private void handleTestAction(ActionEvent event) {
    Connection con = null;
    try {
      setConfig();
      con = ConnectionManager.getConnection();
      if (con != null) {
        // TODO 成功メッセージの表示
      } else {
        // TODO エラーメッセージの表示
      }
    } catch (IOException ex) {
      Logger.getLogger(ConfigurationView.class.getName()).log(Level.SEVERE, null, ex);
      // TODO エラーメッセージの表示
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ex) {
          Logger.getLogger(ConfigurationView.class.getName()).log(Level.SEVERE, null, ex);
          // TODO エラーメッセージの表示
        }
      }
    }

  }

  private void setConfig() throws MalformedURLException {
    config = new Configuration();
    config.setJdbcDriverURL(new File(this.jarFilePath.getText()).toURI().toURL());
    config.setConnectionURL(this.connectionString.getText());
    config.setUser(this.user.getText());
    config.setPassword(this.password.getText());
  }
  
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
