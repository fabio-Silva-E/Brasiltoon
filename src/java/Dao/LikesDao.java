/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexao;
import Vo.Curtidas;
import Vo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class LikesDao {

    public long CurtidasparaAutor(String id) {
        long totalCurtidas = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Estabeleça a conexão com o banco de dados (substitua com suas configurações reais)
            conn = new Conexao().conectar(); // Supondo que você tenha uma classe de conexão
            // Consulta SQL para contar as curtidas para um autor específico
            String sql = "SELECT COUNT(id_autor) AS TotalCurtidas FROM comentarios WHERE id_autor = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCurtidas = rs.getLong("TotalCurtidas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalCurtidas;
    }

  /*  public boolean AutorJaCurtido(String idautor, String idusuario) {
        Connection con = new Conexao().conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                // Consulta para verificar se a história já foi favoritada
                String sql = "SELECT id_curtida FROM comentarios WHERE id_autor = ? AND id_usuario = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idautor);
                ps.setString(2, idusuario);
                rs = ps.executeQuery();
                // Se a consulta retornar algum resultado, significa que a história já foi favoritada
                return rs.next();
            } catch (SQLException erro) {
                // Trate adequadamente as exceções de SQL
                erro.printStackTrace();
                return false;
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }*/

    public boolean DescurtirAutor(String idautor, String idusuario) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from comentarios where id_autor = ? AND id_usuario = ?";
                ps = con.prepareStatement(sql);
             ps.setString(1, idautor);
                ps.setString(2, idusuario);
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean AutorJaCurtido(String idautor, String idusuario) {
        Connection con = new Conexao().conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                // Consulta para verificar se a história já foi favoritada
                String sql = "SELECT * FROM comentarios WHERE id_autor = ? AND id_usuario = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idautor);
                ps.setString(2, idusuario);
                rs = ps.executeQuery();
                // Se a consulta retornar algum resultado, significa que a história já foi favoritada
                return rs.next();
            } catch (SQLException erro) {
                // Trate adequadamente as exceções de SQL
                erro.printStackTrace();
                return false;
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean CurtirAutor(Curtidas c) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "INSERT INTO comentarios (  id_autor,id_usuario) VALUES (?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, c.getId_autor());
                ps.setString(2, c.getId_usuario());
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
    public ArrayList<Curtidas> Top5( ) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "SELECT u.caminho AS caminho_usuario, COUNT(c.id_autor) AS quantidade\n" +
"FROM comentarios c\n" +
"JOIN usuario u ON c.id_autor = u.id\n" +
"GROUP BY c.id_autor\n" +
"ORDER BY quantidade DESC\n" +
"LIMIT 5;" ;

                ps = con.prepareStatement(sql);
               
                rs = ps.executeQuery();
                ArrayList<Curtidas> lista = new ArrayList<>();
                while (rs.next()) {

                Curtidas p = new Curtidas();
                p.setId_autor(rs.getString("caminho_usuario"));
                p.setCurtidas(rs.getLong("quantidade"));
                lista.add(p);
                }
             con.close();
                return lista;
            }else{
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
            return null;
        }
 }

 public boolean CurtirHistoria(Curtidas c) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "INSERT INTO comentarios (id_historia,id_usuario) VALUES (?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, c.getId_historia());
                ps.setString(2, c.getId_usuario());
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
    

     public boolean HistoriaJaCurtida(int idhistoria, String idusuario)  {
        Connection con = new Conexao().conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                // Consulta para verificar se a história já foi favoritada
                String sql = "SELECT * FROM comentarios WHERE id_historia = ? AND id_usuario = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, idhistoria);
                ps.setString(2, idusuario);
                rs = ps.executeQuery();
                // Se a consulta retornar algum resultado, significa que a história já foi favoritada
                return rs.next();
            } catch (SQLException erro) {
                // Trate adequadamente as exceções de SQL
                erro.printStackTrace();
                return false;
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
      public boolean DescurtirHistoria(int idhistoria, String idusuario) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from comentarios where id_historia= ? and id_usuario=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, idhistoria);
                ps.setString(2,idusuario);
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
   public long CurtidasparaHistoria(int id) {
        long totalCurtidas = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Estabeleça a conexão com o banco de dados (substitua com suas configurações reais)
            conn = new Conexao().conectar(); // Supondo que você tenha uma classe de conexão
            // Consulta SQL para contar as curtidas para um autor específico
            String sql = "SELECT COUNT(id_historia) AS TotalCurtidas FROM comentarios WHERE id_historia = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCurtidas = rs.getInt("TotalCurtidas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalCurtidas;
    }

}
