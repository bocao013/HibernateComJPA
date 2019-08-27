/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.web.commands;

import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.model.bean.EntidadeDominio;

/**
 *
 * @author bocao
 */
public class CommandDeletar extends AbstractCommand{

    @Override
    public Resultado executar(EntidadeDominio entidade) {
        return fachada.deletar(entidade);
    }
    
    
}
