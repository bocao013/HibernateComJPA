/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.web.view.cliente;

import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.model.bean.Cliente;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.web.view.IViewHelper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bocao
 */
public class ViewCliente implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        
        try{
            String acao = request.getParameter("acao");
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            
            if(id != null && !id.equals("")){
                cliente.setId(Integer.valueOf(id));
            }
            
            if(nome != null && !nome.equals("")){
                cliente.setNome(nome);
            }
            
            
            
        }catch(Throwable e){
            e.printStackTrace();
        }
        
        return cliente;
    }

    @Override
    public void setEntidade(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            if(resultado != null){
                request.setAttribute("resultado", resultado);
                
                if(resultado.getAcao() != null){
                    
                    if(resultado.getAcao().equals("inserir")){
                        request.setAttribute("acao", "salvar");
                        request.getRequestDispatcher("../sucesso.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("falhaInserir")){
                        request.getRequestDispatcher("cadastro_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("alterar")){
                        request.setAttribute("acao", "alteracao");
                        request.getRequestDispatcher("../sucesso.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("falhaAlterar")){
                        request.getRequestDispatcher("preAlterar_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("listar")){
                        request.getRequestDispatcher("listarTodos_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("Erro-ao-listar")){
                        request.getRequestDispatcher("listarTodos_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("consultar")){
                        request.getRequestDispatcher("preAlterar_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("Erro-ao-consultar")){
                        request.getRequestDispatcher("preAlterar_cliente.jsp").forward(request, response);
                        
                    }else if(resultado.getAcao().equals("deletar")){
                        request.setAttribute("acao", "exclusao");
                        request.getRequestDispatcher("../sucesso.jsp").forward(request, response);
                        
                    }else{
                        request.getRequestDispatcher("../index.html").forward(request, response);
                    }
                }else{
                    request.getRequestDispatcher("../index.html").forward(request, response);
                }
            }else{
                response.sendRedirect("../index.html");
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
    
}
