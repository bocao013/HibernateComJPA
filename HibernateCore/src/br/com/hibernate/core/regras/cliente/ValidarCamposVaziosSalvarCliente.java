/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core.regras.cliente;

import br.com.hibernate.core.regras.AbstractStrategy;
import br.com.hibernate.model.bean.Cliente;
import br.com.hibernate.model.bean.EntidadeDominio;

/**
 *
 * @author bocao
 */
public class ValidarCamposVaziosSalvarCliente extends AbstractStrategy{

    @Override
    public String validar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        String str= "";
        
        if(cliente.getNome() == null || cliente.getNome().trim().length() == 0) {
            str += "<li>Campo nome obrigatório</li>";
        }
        return str;
    }
    
}
