/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core;

import br.com.hibernate.model.bean.EntidadeDominio;

/**
 *
 * @author bocao
 */
public interface IStrategy {
    public String validar(EntidadeDominio entidade);
}
