/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.dao.produto;

import br.com.hibernate.core.connection.ConnectionFactory;
import br.com.hibernate.core.dao.AbstractDAO;
import br.com.hibernate.model.bean.EntidadeDominio;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bocao
 */
public class ProdutoDAO extends AbstractDAO {

    @Override
    public List<EntidadeDominio> litarTudo() {
       EntityManager em = new ConnectionFactory().getConnection();
        List<EntidadeDominio> entidades = null;
        
        try{
           
           entidades = em.createQuery("from Produto p").getResultList();
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            em.close();
        }
        return entidades;
    }
    
}
