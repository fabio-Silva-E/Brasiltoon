package Dao;
import Connection.Conexao;
import Vo.Historias;
import Vo.MinhaBiblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BibliotecaDao {

 
    private Map<String, ArrayList<MinhaBiblioteca>> cacheHistoriasPorTitulo = new ConcurrentHashMap<>();

    public boolean Favoritar(MinhaBiblioteca e) {
    Connection con = new Conexao().conectar();
    
    if (con != null) {
        try {
            // Verifique se a história já foi favoritada pelo usuário
            if (!historiaJaFavoritada(e.getIdUsuario(), e.getIdHistoria())) {
                PreparedStatement ps;
                String sql = "INSERT INTO usuario_historia (id_usuario, id_historia, titulo, genero, autor,capa) VALUES (?, ?, (select titulo from historias where id = ?), (select genero from historias where id = ?), (select autorid from historias where id = ?),(select capa from historias where id = ?))";
                ps = con.prepareStatement(sql);
                ps.setString(1, e.getIdUsuario());
                ps.setInt(2, e.getIdHistoria());
                ps.setInt(3, e.getIdHistoria());
                ps.setInt(4, e.getIdHistoria());
                ps.setInt(5, e.getIdHistoria());
                ps.setInt(6, e.getIdHistoria());
                if (ps.executeUpdate() != 0) {
                    con.close();
                    return true;
                } else {
                    return false;
                }
            } else {
                // A história já foi favoritada, não é necessário inserir novamente
                return true;
            }
        } catch (SQLException erro) {
            // Trate adequadamente as exceções de SQL
            erro.printStackTrace();
            return false;
        }
    } else {
        return false;
    }
}

// Função para verificar se a história já foi favoritada pelo usuário
public boolean historiaJaFavoritada(String idUsuario, int idHistoria) {
    Connection con = new Conexao().conectar();
    PreparedStatement ps = null;
    ResultSet rs = null;

    if (con != null) {
        try {
            // Consulta para verificar se a história já foi favoritada
            String sql = "SELECT id FROM usuario_historia WHERE id_usuario = ? AND id_historia = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, idUsuario);
            ps.setInt(2, idHistoria);
            rs = ps.executeQuery();

            // Se a consulta retornar algum resultado, significa que a história já foi favoritada
            return rs.next();
        } catch (SQLException erro) {
            // Trate adequadamente as exceções de SQL
            erro.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return false;
}

    public ArrayList<MinhaBiblioteca> buscarHistoriaPorTitulo(String gener,String usuario, String letrasIniciais) {
        // Verifique se o resultado da consulta está em cache
        String cacheKey = usuario + "_" + letrasIniciais;
        if (cacheHistoriasPorTitulo.containsKey(cacheKey)) {
            return cacheHistoriasPorTitulo.get(cacheKey);
        }

        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "SELECT * FROM usuario_historia WHERE titulo LIKE ? and genero like ? and id_usuario = ?";
                 ps = con.prepareStatement(sql);
                 ps.setString(1, letrasIniciais + "%");
                 ps.setString(2, gener + "%");
                 ps.setString(3, usuario);
                rs = ps.executeQuery();
                ArrayList<MinhaBiblioteca> lista = new ArrayList<>();
                while (rs.next()) {
                    MinhaBiblioteca p = new MinhaBiblioteca();
                    p.setId(rs.getInt("id"));
                    p.setIdautor(rs.getString("autor"));
                    p.setTitulo(rs.getString("titulo"));
                    p.setGenero(rs.getString("genero"));
                    p.setIdHistoria(rs.getInt("id_historia"));
                    p.setCapa(rs.getString("capa"));
                    lista.add(p);
                }

                // Armazene o resultado em cache
                cacheHistoriasPorTitulo.put(cacheKey, lista);

                con.close();
                return lista;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
            return null;
        }
    }
    public boolean excluirFavorito(int id){
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from usuario_historia where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);             
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
       public ArrayList<Historias> buscarMinhasHistoriaPorTitulo(String gener, String letrasIniciais ,String idAutor) {
    PreparedStatement ps; // estrutura os SQL
    ResultSet rs; // armazenarão o resultado do BD
    Connection con; // conexão com o BD

    try {
        con = new Conexao().conectar();
        if (con != null) {
            // Passo 1: Criar uma tabela temporária para as histórias do autor
            

            // Passo 2: Buscar as histórias com base nas letras iniciais na tabela temporária
            String searchSQL = "SELECT * FROM historias WHERE titulo LIKE ?and genero like ?  and autorid = ?";
            ps = con.prepareStatement(searchSQL);
      
            ps.setString(1, letrasIniciais + "%");
                   ps.setString(2, gener + "%");
               ps.setString(3, idAutor);
               
            rs = ps.executeQuery();
            ArrayList<Historias> lista = new ArrayList<>();
            while (rs.next()) {
                Historias p = new Historias();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setAutorid(rs.getString("autorid"));
                p.setGenero(rs.getString("genero"));
                  p.setCapa(rs.getString("capa"));
                lista.add(p);
            }

            

            con.close();
            return lista;
        } else {
            return null;
        }
    } catch (SQLException erro) {
        System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
        return null;
    }
}
       public boolean excluirHistoria(int id){
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from historias where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);             
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

}
