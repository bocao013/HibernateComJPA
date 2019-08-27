/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.regras.produto;

import br.com.hibernate.core.regras.AbstractStrategy;
import br.com.hibernate.model.bean.EntidadeDominio;
import br.com.hibernate.model.produto.Produto;

/**
 *
 * @author bocao
 */
public class ValidarCamposVaziosSalvarProduto extends AbstractStrategy{
    
    @Override
    public String validar(EntidadeDominio entidade) {
        Produto produto = (Produto) entidade;
        String str= "";
        
        if(produto.getNome() == null || produto.getNome().trim().length() == 0 ||
                produto.getDescricao() == null || produto.getDescricao().trim().length() == 0 ||
                produto.getQuantidade() == null || produto.getQuantidade().equals(0)) {
            str += "<li>Os campos de cadastro de produto são obrigatórios</li>";
        }
        return str;
    }
    
}
