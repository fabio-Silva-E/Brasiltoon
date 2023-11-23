/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vo;

/**
 *
 * @author fabio
 */
public class Capitulos {

    private int id;
    private String capa;
    private int historiaid;
    private int numeroCapitulo;
    private String titulo;
    private String caminhopdf;
    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
   
    public int getHistoriaid() {
        return historiaid;
    }

    public void setHistoriaid(int historiaid) {
        this.historiaid = historiaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaminhopdf() {
        return caminhopdf;
    }

    public void setCaminhopdf(String caminhopdf) {
        this.caminhopdf = caminhopdf;
    }
   
}
