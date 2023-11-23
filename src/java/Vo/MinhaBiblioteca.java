/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vo;

/**
 *
 * @author fabio
 */
public class MinhaBiblioteca {
   private int id; 
   
   private String capa;
    private String idUsuario; 
     private int idHistoria; 
     private String titulo;
     private String idautor;
    
    public String getIdautor() {
        return idautor;
    }

    public void setIdautor(String idautor) {
        this.idautor = idautor;
    }

    
   
     private  String genero;
    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
   

    public int getId() {
        return id;
    }

   

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

  

    public void setId(int id) {
        this.id = id;
    }

   

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }
}
