/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.web.controle;

import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.model.bean.Cliente;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.web.commands.CommandAlterar;
import br.com.hibernate.web.commands.CommandConsultar;
import br.com.hibernate.web.commands.CommandDeletar;
import br.com.hibernate.web.commands.CommandListar;
import br.com.hibernate.web.commands.CommandSalvar;
import br.com.hibernate.web.commands.ICommand;
import br.com.hibernate.web.view.IViewHelper;
import br.com.hibernate.web.view.cliente.ViewCliente;
import br.com.hibernate.web.view.produto.ViewProduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bocao
 */
public class Control extends HttpServlet {

    private static Map<String, IViewHelper> viewHelper;
    private static Map<String, ICommand> commands;
    
    public Control(){
        
        commands = new HashMap<String, ICommand>();
        
        commands.put("salvar", new CommandSalvar());
        commands.put("listar", new CommandListar());
        commands.put("consultar", new CommandConsultar());
        commands.put("deletar", new CommandDeletar());
        commands.put("alterar", new CommandAlterar());
        
        viewHelper = new HashMap<String, IViewHelper>();
        //View Cliente
        viewHelper.put("/HibernateWeb/Clientes/SalvarClientes", new ViewCliente());
        viewHelper.put("/HibernateWeb/Clientes/ListarTodos", new ViewCliente());
        viewHelper.put("/HibernateWeb/Clientes/ConsultarCliente", new ViewCliente());
        viewHelper.put("/HibernateWeb/Clientes/DeletarCliente", new ViewCliente());
        viewHelper.put("/HibernateWeb/Clientes/AlterarCliente", new ViewCliente());
        
        //View Produto
        viewHelper.put("/HibernateWeb/Produtos/SalvarProdutos", new ViewProduto());
        
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obtem a uri que invocou a sevlet(o que foi definido no action do form html)
        String uri = request.getRequestURI();
        
        //Obtem a operacao que sera executada
        String acao = request.getParameter("acao");
        
        //Obtem um viewHelper indexado pela uri que invocou esta servlet
        IViewHelper view = viewHelper.get(uri);
        
        //O view retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade = null;
        
        if(view !=null){
            entidade = view.getEntidade(request);
        }
        
        //Obtem o cammand para executar a respectiva operacao
        ICommand command = commands.get(acao);
        
        /*
        *   Executa o command que chamara a fachada para executar a operacao requisitada
        *   o retorno e uma instancia da classe resultado que pode contem uma mensagem de
        *   erro ou entidade de retorno
        */
        
        Resultado resultado = null;
        
        if(entidade != null && command != null){    // evita erro quando as operacoes nao irem para fachada
            resultado = command.executar(entidade);
        }
        
        /*
        *   Executa o metodo setView do viewHelper especifico para definir como devera 
        *   ser apresentado o resultado para o usuario
        */
        
        viewHelper.get(uri).setEntidade(resultado, request, response);
       //init();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ICommand commandListar = new CommandListar();

        Resultado resultado = commandListar.executar(new Cliente());
        List<EntidadeDominio> clientes = resultado.getEntidades();
        getServletContext().setAttribute("resultado", clientes);
    }

    

}
