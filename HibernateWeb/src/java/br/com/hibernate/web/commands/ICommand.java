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
public interface ICommand {
    public Resultado executar(EntidadeDominio entidade);
}
