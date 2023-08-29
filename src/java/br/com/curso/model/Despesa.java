/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

/**
 *
 * @author maico
 */
public class Despesa {
    
    private int idDespesa;
    private String descricao;
    private Date dataDocumento;
    private double valorDespea;
    private double valorPago;
    private String imagemDocumento;
    
    public Despesa(){
        idDespesa = 0;
        descricao = "";
        valorDespea = 0;
        valorPago = 0;
        dataDocumento = Conversao.dataAtual();
    }
   
    public Despesa(int iDespesa, String descricao, Date dataDocumento, double valorDespesa, 
                   double valorPago, String imagemDocumento){
        this.idDespesa = iDespesa;
        this.descricao = descricao;
        this.dataDocumento = dataDocumento;
        this.valorDespea = valorDespesa;
        this.valorPago = valorPago;
        this.imagemDocumento = imagemDocumento;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public double getValorDespea() {
        return valorDespea;
    }

    public void setValorDespea(double valorDespea) {
        this.valorDespea = valorDespea;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getImagemDocumento() {
        return imagemDocumento;
    }

    public void setImagemDocumento(String imagemDocumento) {
        this.imagemDocumento = imagemDocumento;
    }
 
}
