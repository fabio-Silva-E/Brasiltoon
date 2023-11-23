/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexao;
import Vo.Capitulos;
import Vo.Curtidas;
import Vo.Historias;
import java.sql.Connection;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author fabio
 */
public class HistoriasDao {
 
 
 
  


   public boolean inserirHistoria(Historias h) {
    Connection con = new Conexao().conectar();
      if (con != null) {
      try{
          PreparedStatement ps;
            String sql = "INSERT INTO historias (titulo, autorid, genero,data_criacao,capa,descricao) VALUES (?,?, ?, ?, ?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, h.getTitulo());
            ps.setString(2, h.getAutorid());
            ps.setString(3, h.getGenero());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = sdf.format(new Date());
            ps.setString(4, dataFormatada);
             ps.setString(5, h.getCapa());
             ps.setString(6, h.getDescricao());

             if(ps.executeUpdate()!=0){
                    con.close();
                    return true;
                }else{
                    return false;
                }             
                   } catch (SQLException erro) {
                return false;}
            
        }else{
            return false;
        }
}
public boolean inserirCapitulo(Capitulos c) {
    Connection con = new Conexao().conectar();

    if (con != null) {
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO capitulos (historia_id, capa, numero_capitulo, titulo, caminhoPDF) VALUES (?, ?, ?, (select titulo from historias where id = ?), ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getHistoriaid());
            ps.setString(2, c.getCapa());
            ps.setInt(3, c.getNumeroCapitulo());
            ps.setInt(4, c.getHistoriaid());
            ps.setString(5, c.getCaminhopdf());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException erro) {
            // Registre ou imprima a exceção para fins de depuração
            erro.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // Lide com erros de fechamento de conexão, se necessário
                e.printStackTrace();
            }
        }
    } else {
        return false;
    }
}

public int obterIdHistoriaPorTituloEAutor(String titulo, String autorId) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int historiaId = -1; // Valor padrão indicando que não foi encontrado

    try {
        con = new Conexao().conectar();

        if (con != null) {
            String sql = "SELECT id FROM historias WHERE titulo = ? AND autorid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, autorId);

            rs = ps.executeQuery();

            if (rs.next()) {
                historiaId = rs.getInt("id");
            }
        }
    } catch (SQLException erro) {
        erro.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return historiaId;
}


     private Map<String, ArrayList<Historias>> cacheHistoria = new ConcurrentHashMap<>();

    public ArrayList<Historias> buscarHistoria() {
        // Verifique se o resultado da consulta está em cache
        String cacheKey = "buscarHistoria";
        if (cacheHistoria.containsKey(cacheKey)) {
            return cacheHistoria.get(cacheKey);
        }

        PreparedStatement ps;
        ResultSet rs;
        Connection con;

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select * from historias";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<Historias> lista = new ArrayList<>();
                while (rs.next()) {
                    Historias p = new Historias();
                    p.setId(rs.getInt("id"));
                    p.setTitulo(rs.getString("titulo"));
                    p.setGenero(rs.getString("genero"));
                    p.setAutorid(rs.getString("autorid"));
                      p.setCapa(rs.getString("capa"));
                    lista.add(p);
                }

                // Armazene o resultado em cache
                cacheHistoria.put(cacheKey, lista);

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
   
    
    public Historias localizarHistoria(int id) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select id,capa, titulo,genero,descricao from historias where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                
                Historias p = new Historias();
                p.setId(rs.getInt("id"));
                p.setCapa(rs.getString("capa"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setDescricao(rs.getString("descricao"));
                con.close();
                return p;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados para alteração: " + erro.getMessage());
            return null;
        }
    }

   public boolean Update(Historias p) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "update historias set capa=?, titulo=?,genero=?,descricao=? where id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, p.getCapa());
                ps.setString(2, p.getTitulo());
                ps.setString(3, p.getGenero());
                ps.setString(4, p.getDescricao());
                ps.setInt(5,p.getId());
                
                if (ps.executeUpdate() != 0) {
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
    
    
    public boolean DeleteHistoria(int idhistoria) {
        Connection con = new Conexao().conectar();
        if (con != null) {
            try {
                PreparedStatement ps;
                String sql = "delete from historias where id_historia= ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, idhistoria);
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
   
 public ArrayList<Capitulos> Historia(int id) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select capa,caminhoPDF,historia_id,numero_capitulo from capitulos where historia_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                ArrayList<Capitulos> lista = new ArrayList<>();
                while (rs.next()) {

                Capitulos p = new Capitulos();
                p.setCapa(rs.getString("capa"));
                p.setCaminhopdf(rs.getString("caminhoPDF"));
                p.setHistoriaid(rs.getInt("historia_id"));
               p.setNumeroCapitulo(rs.getInt("numero_capitulo"));
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


    private HashMap<String, ArrayList<Historias>> cache = new HashMap<>();

  public ArrayList<Capitulos> HistoriaPorId(int id) {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            con = new Conexao().conectar();
            if (con != null) {
                String sql = "select capa,caminhoPDF,historia_id,numero_capitulo from capitulos where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                ArrayList<Capitulos> lista = new ArrayList<>();
                while (rs.next()) {

                Capitulos p = new Capitulos();
                p.setCapa(rs.getString("capa"));
                p.setCaminhopdf(rs.getString("caminhoPDF"));
                p.setHistoriaid(rs.getInt("historia_id"));
               p.setNumeroCapitulo(rs.getInt("numero_capitulo"));
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
public ArrayList<Historias> buscarHistoriaPorTitulo2(String genero, String letrasIniciais) {
    String cacheKey = genero + "_" + letrasIniciais;

    // Remover a entrada de cache para esta chave antes de realizar a consulta
    cache.remove(cacheKey);

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    try {
        con = new Conexao().conectar();
        if (con != null) {
            String sql = "SELECT * FROM historias WHERE titulo LIKE ? and genero like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, letrasIniciais + "%");
            ps.setString(2, genero + "%");
            rs = ps.executeQuery();
            ArrayList<Historias> lista = new ArrayList<>();
            while (rs.next()) {
                Historias p = new Historias();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setAutorid(rs.getString("autorid"));
                p.setCapa(rs.getString("capa"));
                lista.add(p);
            }
            con.close();

            // Armazenar a nova lista no cache
            cache.put(cacheKey, lista);

            return lista;
        } else {
            return null;
        }
    } catch (SQLException erro) {
        System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
        return null;
    }
}

    
    public int getUltimoNumeroCapitulo(int historiaId) {
        int ultimoNumeroCapitulo = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Estabeleça a conexão com o banco de dados (substitua com suas configurações reais)
            conn = new Conexao().conectar(); // Supondo que você tenha uma classe de conexão

            // Consulta SQL para obter o último número de capítulo
            String sql = "SELECT MAX(numero_capitulo) FROM capitulos WHERE historia_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, historiaId);

            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoNumeroCapitulo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ultimoNumeroCapitulo;
    }


}