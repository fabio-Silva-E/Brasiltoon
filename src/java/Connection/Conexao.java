package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conexao = null;
    private final String bd = "brasiltoon"; // mudar o nome da base para cada projeto
    private final String usuario = "root";
    private final String senha = "";

  public Connection conectar() {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + this.bd + "?useUnicode=true&characterEncoding=UTF-8",
                this.usuario, this.senha);
        conexao.createStatement().execute("SET NAMES 'utf8mb4'");
    } catch (ClassNotFoundException e1) {
        System.out.println("Erro com o driver: " + e1.getMessage());
    } catch (SQLException e2) {
        System.out.println("Erro na tentativa de conexão: " + e2.getMessage());
    }
    return conexao;
}
}