/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.controle;

import br.com.hibernate.core.IDAO;
import br.com.hibernate.core.IFachada;
import br.com.hibernate.core.IStrategy;
import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.core.dao.cliente.ClienteDAO;
import br.com.hibernate.core.dao.produto.ProdutoDAO;
import br.com.hibernate.core.regras.cliente.ValidarCamposVaziosSalvarCliente;
import br.com.hibernate.core.regras.produto.ValidarCamposVaziosSalvarProduto;
import br.com.hibernate.model.bean.Cliente;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.model.bean.IEntidadeDominio;
import br.com.hibernate.model.produto.Produto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author bocao
 */
public class Fachada implements IFachada{
    
    private Resultado resultado = new Resultado();
    private Map<String, IDAO> dao;
    private StringBuilder sb = new StringBuilder();
    private Map<String, Map<String,List<IStrategy>>> rns;

    public Fachada() {
        
        dao = new HashMap<String, IDAO>();
        rns = new HashMap<String, Map<String,List<IStrategy>>>();

        dao.put(Cliente.class.getName(), new ClienteDAO());
        dao.put(Produto.class.getName(), new ProdutoDAO());
        
        //Regras do cliente
        List<IStrategy> RNClienteSalvar = new ArrayList<IStrategy>();
        
        // Listas das Regras de negocio do cliente
        RNClienteSalvar.add(new ValidarCamposVaziosSalvarCliente());
        
        // Regras de negocio do cliente
        Map<String, List<IStrategy>> regrasCliente = new HashMap<String, List<IStrategy>>();
                     
        // Regra salvar cliente
        regrasCliente.put("salvar", RNClienteSalvar);
        
        // Regras de negocio geral
      	rns.put(Cliente.class.getName(), regrasCliente);
        
        //Regras do produto
        List<IStrategy> RNProdutoSalvar = new ArrayList<IStrategy>();
        
        //Listas das regras de negocio do produto
        RNProdutoSalvar.add(new ValidarCamposVaziosSalvarProduto());
        
        //Regras de negocio do produto
        Map<String, List<IStrategy>> regrasProduto = new HashMap<String, List<IStrategy>>();
        
        //Regra salvar produto
        regrasProduto.put("salvar", RNProdutoSalvar);
        
        //Regras de negocio geral
        rns.put(Produto.class.getName(), regrasProduto);
    }
    
    
    
    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado.setEntidades(new ArrayList<EntidadeDominio>());
        regrasDeNegocio(entidade, "salvar");
        
        try {
            if(resultado.getMensagem().length() == 0) {
                dao.get(entidade.getClass().getName()).salvar(entidade);
                resultado.setAcao("inserir");
                resultado.setStatus(true);
                } else {
                resultado.setStatus(false);
                resultado.setAcao("falhaInserir");
                //resultado.setMensagem("falhaInserir");
                }	
        } catch(Exception e) {
            resultado.setStatus(false);
            resultado.setAcao("falhaInserir");
            //resultado.setMensagem("falhaInserir");
            e.printStackTrace();
        }
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado.setEntidades(new ArrayList<EntidadeDominio>());
        regrasDeNegocio(entidade, "alterar");
        
        try {
            if(resultado.getMensagem().length() == 0) {
                dao.get(entidade.getClass().getName()).alterar(entidade);
                resultado.setAcao("alterar");
                resultado.setStatus(true);
                } else {
                resultado.setStatus(false);
                resultado.setAcao("falhaAlterar");
                resultado.setMensagem("falhaAlterar");
                }	
        } catch(Exception e) {
            resultado.setStatus(false);
            resultado.setAcao("falhaAlterar");
            resultado.setMensagem("falhaAlterar");
            e.printStackTrace();
        }
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        entidades.add(entidade);
        resultado.setEntidades(entidades);
        return resultado;
    }

    @Override
    public Resultado deletar(EntidadeDominio entidade) {
        resultado.setEntidades(new ArrayList<EntidadeDominio>());
        
        try {

            dao.get(entidade.getClass().getName()).deletar(entidade);
            resultado.setAcao("deletar");
            resultado.setStatus(true);

        } catch(Exception e) {
            resultado.setStatus(false);
            resultado.setAcao("falhaDeletar");
            resultado.setMensagem("falhaDeletar");
            e.printStackTrace();
        }
        
        return resultado;
    }

    @Override
    public Resultado listar(EntidadeDominio entidade) {
               
        resultado.setEntidades(new ArrayList<EntidadeDominio>());
         
        try {
//            List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
//            
//            entidades.add((EntidadeDominio) dao.get(entidade.getClass().getName()).litarTudo());
            resultado.setEntidades(dao.get(entidade.getClass().getName()).litarTudo());
            resultado.setAcao("listar");
            System.out.println(resultado.getEntidades().toArray());
            resultado.setStatus(true);

        } catch(Exception e) {
            resultado.setStatus(false);
            resultado.setMensagem("Erro-ao-listar");
            resultado.setAcao("Erro-ao-listar");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        
        resultado.setEntidades(new ArrayList<EntidadeDominio>());
         
        try {
            List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
            
            entidades.add(dao.get(entidade.getClass().getName()).listarPorId(entidade));
            resultado.setEntidades(entidades);
            resultado.setAcao("consultar");
            resultado.setStatus(true);
            

        } catch(Exception e) {
            resultado.setStatus(false);
            resultado.setMensagem("Erro-ao-consultar");
            resultado.setAcao("Erro-ao-consultar");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public void regrasDeNegocio(EntidadeDominio entidade, String operacao) {
        String nomeClasse = entidade.getClass().getName();
	StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nomeClasse);

        String resposta = "";
        if(regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operacao);
            if(regras != null){
                for(IStrategy r:regras) {
                    if(r != null) {
                        resposta += r.validar(entidade);
                        resultado.setMensagem(resposta);
                    }
                }
            }else
                resultado.setMensagem(new String());
        } else {
            resultado.setMensagem(new String());
        }
    }
    
}
