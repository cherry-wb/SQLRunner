/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.saiki.sqlrunner.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author saiki
 */
public class SQL {

  private String _sql;

  private boolean _hasMultipleResult;

  private ResultSet _result;

  public SQL(String sql) {
    this._sql = sql;
  }

  public boolean hasResultSet() {
    return _hasMultipleResult;
  }

  public void run() throws IOException, SQLException {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      con = ConnectionManager.getConnection();
      stmt = con.createStatement();
      _hasMultipleResult = stmt.execute(_sql);


    } finally {
      if (stmt != null && !stmt.isClosed()) {
        stmt.close();
      }
      if (con != null && !con.isClosed()) {
        con.close();
      }
    }


  }


  protected final void initialize() {
  }

}
