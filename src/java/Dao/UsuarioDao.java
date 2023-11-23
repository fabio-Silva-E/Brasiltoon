/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexao;
import Vo.Usuario;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class UsuarioDao {
   
    
    
    public boolean excluirCadastro(Usuario c){
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from usuario where id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1,c.getId()); 
                
                if(ps.executeUpdate()!=0){
                    con.close();
                    return true;
                }else{
                    return false;
                }               
            } catch (SQLException erro) {
                return false;
            }
        } else {
            return false;
        }
    }
    
   
    // Método para atualizar os dados do usuário
    public static boolean atualizarUsuario(Connection conn, String id, String nome, String email, String senha, long telefone) {
     
        
        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, telefone=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setLong(4, telefone);
            stmt.setString(5, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


   
   



    

public static boolean Cadastrar(Usuario p) {
    Connection con = new Conexao().conectar();

    if (con != null) {
        try {
            PreparedStatement ps;
            String sql = "INSERT INTO usuario (id, nome, telefone, caminho, email, senha) VALUES (UUID(), ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setLong(2, p.getTel());
            ps.setString(3, p.getCaminho());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getSenha());

            if (ps.executeUpdate() != 0) {
                con.close();
                return true; // Cadastro bem-sucedido
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Falha na operação
        }
    }
    return false; // Conexão nula ou falha ao conectar ao banco de dados
}


    public static  ArrayList<Usuario> buscarCadastro() {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select * from usuario";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<Usuario> usuarios = new ArrayList<>();
                while (rs.next()) {
                    //setar os valores dentro de um objeto (Produto)
                    //adicionar este objeto a uma list
                    Usuario c = new Usuario();   
                    c.setId  (rs.getString("id"));
                    c.setEmail(rs.getString("email"));
                    c.setSenha(rs.getString("senha"));
                    usuarios.add(c);
                }
                con.close();
                return usuarios;
            }else{
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
            return null;
        }
    }
         public static Usuario CadastroPublico(String id) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select id, email,nome,telefone,caminho from usuario where id = ? ";
                ps = con.prepareStatement(sql);
               
                 ps.setString(1, id);
                rs = ps.executeQuery();
                rs.next();
                //setar os valores dentro de um objeto ()
                Usuario c = new Usuario();
               c.setId(rs.getString("id"));
                c.setEmail(rs.getString("email"));
                 c.setNome(rs.getString("nome"));
                 Long telefone = rs.getLong("telefone");
            if (!rs.wasNull()) {
                c.setTel(telefone);
            }
                   c.setCaminho(rs.getString("caminho"));
                con.close();
                return c;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }
 public static Usuario localizarsenha(String email) {
    PreparedStatement ps; // estrutura o sql
    ResultSet rs; // armazenará o resultado do bd
    Connection con; // conexão com o bd

   
    try {
        con = new Conexao().conectar();

        if (con != null) {
            String sql = "SELECT senha FROM usuario WHERE email = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            rs.next();
                // Verifica se há resultados antes de acessar os dados
                Usuario c = new Usuario();
                c.setSenha(rs.getString("senha"));
                 

                              

                con.close();
                return c;
           
        } else {
            return null;
        }
    } catch (SQLException erro) {
        // Tratamento adequado da exceção (log, lançamento, etc.)
        System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
        return null;
    }
}
  public static Usuario localizarRegistroCadastro(String senha,String email) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select nome,telefone,email,senha,id from usuario where senha = ? and email=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, senha);
                ps.setString(2, email);

                rs = ps.executeQuery();
                rs.next();
                //setar os valores dentro de um objeto (cliente)
                Usuario c = new Usuario();
               
               c.setNome(rs.getString("nome"));
               c.setTel(rs.getLong("telefone"));
               c.setEmail(rs.getString("email"));
               c.setSenha(rs.getString("senha"));
               c.setId(rs.getString("id"));
                con.close();
                return c;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }
 
public boolean atualizarFotoPerfil(String id, String novoCaminhoFoto) {
    Connection con = new Conexao().conectar();
    PreparedStatement ps;

    try {
        if (con != null) {
            String sql = "UPDATE usuario SET caminho=? WHERE id=?";
            ps = con.prepareStatement(sql);

            // Verifica se o novo caminho da foto está em branco
            if (novoCaminhoFoto != null && !novoCaminhoFoto.trim().isEmpty()) {
                ps.setString(1, novoCaminhoFoto);
            } else {
                // Mantém o caminho original
                ps.setNull(1, java.sql.Types.VARCHAR);
            }

            ps.setString(2, id);

            return ps.executeUpdate() != 0;
        }
    } catch (SQLException erro) {
        erro.printStackTrace();
    } finally {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return false;
}

public String obterCaminhoFoto(String idUsuario) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = new Conexao().conectar();
        if (con != null) {
            String sql = "SELECT caminho FROM usuario WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("caminho");
            }
        }
    } catch (SQLException erro) {
        System.err.println("Exceção ao obter caminho da foto: " + erro.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Se algo der errado, retorne null ou um valor padrão
    return null;
}
public static Usuario Meucadastro(String id) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd
        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select id,email,nome,telefone,caminho from usuario where id = ? ";
                ps = con.prepareStatement(sql);
                 ps.setString(1, id);
                rs = ps.executeQuery();
                rs.next();
                //setar os valores dentro de um objeto ()
                Usuario c = new Usuario();
                   c.setId(rs.getString("id"));
                   c.setEmail(rs.getString("email"));
                   c.setNome(rs.getString("nome"));
                  Long telefone = rs.getLong("telefone");
            if (!rs.wasNull()) {
                c.setTel(telefone);
            }
                   c.setCaminho(rs.getString("caminho"));
                con.close();
                return c;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }
  public static Usuario buscarCadastroPorEmail(String email) {
    PreparedStatement ps; // estrutura o sql
    ResultSet rs; // armazenará o resultado do bd
    Connection con; // conexão com o bd

   
    try {
        con = new Conexao().conectar();

        if (con != null) {
            String sql = "SELECT id,senha,email FROM usuario WHERE email = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            rs.next();
                // Verifica se há resultados antes de acessar os dados
                Usuario c = new Usuario();
                c.setSenha(rs.getString("senha"));
                c.setEmail(rs.getString("email"));
                c.setId(rs.getString("id"));

                              

                con.close();
                return c;
           
        } else {
            return null;
        }
    } catch (SQLException erro) {
        // Tratamento adequado da exceção (log, lançamento, etc.)
        System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
        return null;
    }
}
}