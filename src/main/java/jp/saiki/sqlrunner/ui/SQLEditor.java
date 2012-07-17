/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import jp.saiki.sqlrunner.sql.ConnectionManager;

/**
 *
 * @author saiki
 */
public class SQLEditor implements Initializable {

  @FXML
  private TextArea textArea;

  @FXML
  private TableView result;

  @FXML
  private void handleRunAction(ActionEvent event) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      con = ConnectionManager.getConnection();
      stmt = con.createStatement();
      rs = stmt.executeQuery(textArea.getText());
      final ResultSetMetaData meta = rs.getMetaData();
      // メタデータからカラム情報をセット
      for (int i = 0; i < meta.getColumnCount(); i++) {
        TableColumn column = new TableColumn(meta.getColumnName(i));
      }
    } catch (SQLException ex) {
      Logger.getLogger(SQLEditor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(SQLEditor.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (con != null) {
          con.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException ex) {
        Logger.getLogger(SQLEditor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
