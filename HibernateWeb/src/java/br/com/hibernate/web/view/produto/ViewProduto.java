/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.web.view.produto;

import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.model.produto.Produto;
import br.com.hibernate.web.view.IViewHelper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bocao
 */
public class ViewProduto implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Produto produto = new Produto();
        
        try{
            String acao = request.getParameter("acao");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String qtde = request.getParameter("quantidade");
            
            if(nome != null && !nome.trim().equals("")){
                produto.setNome(nome);
            }
            if(descricao != null && !descricao.trim().equals("")){
                produto.setDescricao(descricao);
            }
            if(qtde != null && !qtde.trim().equals("")){
                produto.setQuantidade(Integer.valueOf(qtde));
            }
            
        }catch(Throwable e){
            e.printStackTrace();
        }
        return produto;
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
                        request.getRequestDispatcher("cadastro_produto.jsp").forward(request, response);
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
