package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;


public class ConexaoDB {
  private static final Dotenv dotenv = Dotenv.configure().directory("./orm").load();

  public static Connection conexaoDB() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");

    String db_name = dotenv.get("DB_NAME");
    String db_username = dotenv.get("DB_USERNAME");
    String db_url = dotenv.get("DB_URL");
    String db_password = dotenv.get("DB_PASSWORD");

    Connection conexaoDB = DriverManager.getConnection(db_url.concat(db_name), db_username, db_password);

    if (conexaoDB != null) {
        System.out.println("Conexão com o banco iniciada com sucesso!");
        return conexaoDB;
    } else {
        throw new RuntimeException("Erro ao tentar conectar com o banco de dados!");
    }
  }

  public static PreparedStatement prepararSQL(String sql) throws SQLException, ClassNotFoundException {
    return conexaoDB().prepareStatement(sql);
  }

  public static PreparedStatement prepararSQL(String sql, int StatementType) throws SQLException, ClassNotFoundException {
      return conexaoDB().prepareStatement(sql, StatementType);
  }

  public void printSQLException(SQLException ex) {
    for (Throwable e : ex) {
        if (e instanceof SQLException) {
            e.printStackTrace(System.err);
            System.err.println("Estado do SQL: " + ((SQLException) e).getSQLState());
            System.err.println("Codigo do ERRO: " + ((SQLException) e).getErrorCode());
            System.err.println("Mensagem: " + e.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.out.println("Causa: " + t);
                t = t.getCause();
            }
        }
    }
  }
}
