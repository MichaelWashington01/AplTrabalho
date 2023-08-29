/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Despesa;
import br.com.curso.utils.SingleConnection;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maico
 */
public class DespesaDAO implements GenericDAO{

    
    private Connection conexao;
    
    public DespesaDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
        boolean retorno = false;
        if(oDespesa.getIdDespesa() ==0){
            retorno = inserir(oDespesa);
        }else{
        retorno = alterar(oDespesa);
    }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
            PreparedStatement stmt = null;
            String sql = "Insert Into Despesa (descricao, valorDespesa, valorPago,"
                          + "datadocumento, imagemdocumento) values (?,?,?,?,?)";
            try{
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, oDespesa.getDescricao());
                stmt.setDouble(2, oDespesa.getValorDespea());
                stmt.setDouble(3, oDespesa.getValorPago());
                stmt.setDate(4, new java.sql.Date(oDespesa.getDataDocumento().getTime()));
                stmt.setString(5, oDespesa.getImagemDocumento());
                stmt.execute();
                conexao.commit();
                return true;
                
            }catch (Exception e){
            try{
                System.out.println("Problemas ao cadastrar Despesa!Erro:" +e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            }catch (SQLException ex){
                System.out.println("Problemas a execultar rollback"+ ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }
    @Override
    public Boolean alterar(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
        PreparedStatement stmt = null;
        String sql = "update despesa set descricao=?, valorDespesa=?, valorPago=?,"
                      + " datadocumento=?, imagemdocumento=? where iddespesa=?";
        
        try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oDespesa.getDescricao());
         stmt.setDouble(2, oDespesa.getValorDespea());
         stmt.setDouble(3, oDespesa.getValorPago());
         stmt.setDate(4, new java.sql.Date(oDespesa.getDataDocumento().getTime()));
         stmt.setString(5, oDespesa.getImagemDocumento());
         stmt.setInt(6, oDespesa.getIdDespesa());
         stmt.execute();
         conexao.commit();
         return true;
        }catch (Exception e) {
        try{
            System.out.println("Problemas o Alterar Despesa!Errp: "+e.getMessage());
            e.printStackTrace();
            conexao.rollback();
        }catch (SQLException ex) {
                System.out.println("Problemas ao execultar rolback" +ex.getMessage());
                ex.printStackTrace();
            }
        return false;
        }        
        
    }

    @Override
    public Boolean excluir(int numero) {
        int idDespesa = numero;
        PreparedStatement stmt = null;
        String sql = "delete from despesa where iddespesa=?";
        try{
        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, idDespesa);
        stmt.execute();
        conexao.commit();
        return true;
        }catch (Exception e){
            try{
            System.out.println("Problemas ao excluir Despesa!Erro: " + e.getMessage());
            e.printStackTrace();
            conexao.rollback();
        }catch (SQLException ex){
            System.out.println("Problemas ao executar rollback" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
}
    @Override
    public Object carregar(int numero) {
        int idDespesa = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Despesa oDespesa = null;
        String sql = "Select * from despesa Where idDespesa = ?";
        try{
        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, idDespesa);
        rs = stmt.executeQuery();
            while (rs.next()) {
               oDespesa = new Despesa();
               oDespesa.setIdDespesa(rs.getInt("idDespesa"));
               oDespesa.setDescricao(rs.getString("descricao"));
               oDespesa.setValorDespea(rs.getDouble("valorDespesa"));
               oDespesa.setValorPago(rs.getDouble("valorPago"));
               oDespesa.setDataDocumento(rs.getDate("datadocumento"));
               oDespesa.setImagemDocumento(rs.getString("imagemdocumento"));
            }
        }catch (Exception e){
            System.out.println("Problemas ao carregar despesa!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        return oDespesa;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Despesa oDespesa = null;
        String sql = "select * from despesa";
        try{
        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
        oDespesa = new Despesa();
        oDespesa.setIdDespesa(rs.getInt("iddespesa"));
        oDespesa.setDescricao(rs.getString("descricao"));
        oDespesa.setValorDespea(rs.getDouble("valorDespesa"));
        oDespesa.setValorPago(rs.getDouble("valorPago"));
        oDespesa.setDataDocumento(rs.getDate("datadocumento"));
        oDespesa.setImagemDocumento(rs.getString("imagemdocumento"));
        resultado.add(oDespesa);       
        }
        }catch (Exception e) {
            System.out.println("Problemas ao Listar Despesa!Erro" + e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
    
}























