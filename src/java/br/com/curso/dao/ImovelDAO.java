/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Imovel;
import br.com.curso.utils.SingleConnection;
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
public class ImovelDAO implements GenericDAO{
    private Connection conexao;
    
    public ImovelDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
          Imovel oImovel = (Imovel)objeto;
        boolean retorno=false;
        if(oImovel.getIdImovel()==0){
            retorno = this.inserir(oImovel);
        }else{
            retorno = this.alterar(oImovel);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
              Imovel oImovel = (Imovel)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into imovel (descricao,endereco,valoraluguel) values (?,?,?)";
        try{
          stmt = conexao.prepareStatement(sql);
          
          stmt.setString(1, oImovel.getDescricao());
          stmt.setString(2, oImovel.getEndereco());
          stmt.setDouble(3, oImovel.getValorAluguel());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a Imovel! Erro:" + ex.getMessage());
            ex.printStackTrace();
              conexao.rollback();
          }catch(SQLException e){
                System.out.println("Erro"+ e.getMessage());
                e.printStackTrace();
          }
            return false;
       }
    }

    @Override
    public Boolean alterar(Object objeto) {
         Imovel oImovel = (Imovel)objeto;
                
        PreparedStatement stmt = null;
              String sql = "update imovel set descricao=?,endereco=?,valoraluguel=? where idimovel =?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oImovel.getDescricao());
          stmt.setString(2, oImovel.getEndereco());
          stmt.setDouble(3, oImovel.getValorAluguel());
          stmt.setInt(4, oImovel.getIdImovel());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Imovel! Erro:" +ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:" +e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
                int idImovel = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from imovel where idimovel=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idImovel);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Imovel! Erro:" +ex.getMessage());
            try{
                conexao.rollback();
            }catch (Exception e){
                System.out.println("Erro rolback"+ e.getMessage());
                e.printStackTrace();
            }
            return  false;
        }
    }

    @Override
    public Object carregar(int numero) {
             int idEstado = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Imovel oImovel = null;
        String sql="select * from imovel where idImovel=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEstado);
            rs=stmt.executeQuery();
            while(rs.next()){
                oImovel = new Imovel();
                oImovel.setIdImovel(rs.getInt("idImovel"));
                oImovel.setDescricao(rs.getString("descricao"));
                oImovel.setEndereco(rs.getString("endereco"));
                oImovel.setValorAluguel(rs.getDouble("valorAluguel"));
            }
            return oImovel;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Imovel! Erro:" + ex.getMessage());
            return  false;
        } 
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM imovel ORDER BY idImovel";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Imovel oImovel = new Imovel();
                oImovel.setIdImovel(rs.getInt("idImovel"));
                oImovel.setDescricao(rs.getString("descricao"));
                oImovel.setEndereco(rs.getString("endereco"));
                oImovel.setValorAluguel(rs.getDouble("valoraluguel"));
                resultado.add(oImovel);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar imóveis! Erro: " + ex.getMessage());
        } 
        return resultado;
    
    }
}
    

