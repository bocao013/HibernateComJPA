/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.dao;

import br.com.hibernate.core.dao.cliente.ClienteDAO;
import br.com.hibernate.model.bean.Cliente;
import br.com.hibernate.model.bean.EntidadeDominio;

/**
 *
 * @author bocao
 */
public class TesteCliente {
    
    public static void main(String[] args) {
        
        ClienteDAO dao = new ClienteDAO();
        Cliente cl = new Cliente();
//        
////        cl.setId(1);
        cl.setNome("Vera");
        dao.salvar(cl);
//        
//        EntidadeDominio cl1 = dao.listarPorId(cl);
//        Cliente cl2 = (Cliente) cl1;
//        System.out.println("Nome: " + cl2.getNome() + "\n" +
//                "ID: " + cl2.getId());
//        for (EntidadeDominio ent : dao.litarTudo()) {
//            Cliente cl = (Cliente) ent;
//            System.out.println("Nome: " + cl.getNome() + "\n" +
//                "ID: " + cl.getId());
//        }
    }
}
