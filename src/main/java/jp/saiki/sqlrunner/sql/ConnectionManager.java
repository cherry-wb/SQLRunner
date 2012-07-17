/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.sql;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jp.saiki.sqlrunner.ui.ConfigurationView;

/**
 *
 * @author saiki
 */
public class ConnectionManager {

  /**
   * 唯一のインスタンス
   */
  private static ConnectionManager instance;

  /**
   * 接続設定
   */
  private Configuration config;

  private Configuration getConfig() {
    return this.config;
  }

  public static Connection getConnection() throws IOException {
    if (instance.getConfig() == null) {
      instance.newConfig();
    }
    Connection con = null;
    return con;
  }

  private ConnectionManager getInstance() {
    if (ConnectionManager.instance == null) {
      ConnectionManager.instance = new ConnectionManager();
    }
    return ConnectionManager.instance;
  }

  private void newConfig() throws IOException {

    Stage child = new Stage();
    child.initModality(Modality.APPLICATION_MODAL);
    child.initOwner( null );
    child.setTitle("接続先設定");
    FXMLLoader loader = new FXMLLoader();

    child.setScene(SceneBuilder.create().root((Parent)loader.load(getClass().getResource("ui/ConfigurationView.fxml").openStream())).build());
    final ConfigurationView controller = (ConfigurationView) loader.getController();

    child.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>(){

      public void handle(WindowEvent t) {
        config = controller.getConfig();
        try {
          initialize();
        } catch (SQLException ex) {
          config = null;
          Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
          config = null;
          Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
          config = null;
          Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
          config = null;
          Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          if (config == null) {
            // メッセージの表示を行う
          }
        }
      }
    });
    child.show();
  }


  /**
   * create class loader
   * @return
   */
  private ClassLoader getURLClassLoader() {
    return new URLClassLoader( new URL[]{ config.getJdbcDriverURL() }, ClassLoader.getSystemClassLoader() );
  }

  /**
   * load jar file and
   *
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws SQLException
   */
  private Driver loadDriver() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    @SuppressWarnings("unchecked")
    Class<Driver> driver = (Class<Driver>)getURLClassLoader().loadClass(config.getDriverClassName());
    return driver.newInstance();
  }

  /**
   * jdbc driver の登録をおこなう
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
      DriverManager.registerDriver(loadDriver());
  }



  /**
   * コンスタラクタは潰す
   */
  private ConnectionManager() { 
  }
}
