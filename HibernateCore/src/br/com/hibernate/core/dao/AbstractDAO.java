/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.dao;

import br.com.hibernate.core.IDAO;
import br.com.hibernate.core.connection.ConnectionFactory;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.model.bean.IEntidadeDominio;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bocao
 */
public abstract class AbstractDAO  implements IDAO{

    @Override
    public void salvar(EntidadeDominio entidade) {
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
            
        }catch(Exception e){
            
            System.out.println(e);
            em.getTransaction().rollback();
            
        }finally{
            em.close();
        }
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            Class cl = entidade.getClass();
            Field fl = cl.getDeclaredField("id");
            fl.setAccessible(true);
            Integer id = (Integer) fl.get (entidade);
           
            em.getTransaction().begin();
            
            if(id != null && !id.equals("")){
                em.merge(entidade);
                em.getTransaction().commit();
            }
        }catch(Exception e){
            System.out.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
    }

    @Override
    public void deletar(EntidadeDominio entidade) {
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            Class cl = entidade.getClass();
            Field fl = cl.getDeclaredField("id");
            fl.setAccessible(true);
            Integer id = (Integer) fl.get (entidade);
           
            em.getTransaction().begin();
            EntidadeDominio ent = em.find(entidade.getClass(), id);
                    
            if(id != null && !id.equals("") && ent != null){
                
                em.remove(ent);
                em.getTransaction().commit();
            }
        }catch(Exception e){
            System.out.println(e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    @Override
    public EntidadeDominio listarPorId(EntidadeDominio entidade) {
        EntityManager em = new ConnectionFactory().getConnection();
        EntidadeDominio ent = null;
        
        try{
            Class cl = entidade.getClass();
            Field fl = cl.getDeclaredField("id");
            fl.setAccessible(true);
            Integer id = (Integer) fl.get (entidade);
           
            em.getTransaction().begin();
            ent = em.find(entidade.getClass(), id);
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            em.close();
        }
        return ent;
    }

    @Override
    public abstract List<EntidadeDominio> litarTudo();
    
    
}
