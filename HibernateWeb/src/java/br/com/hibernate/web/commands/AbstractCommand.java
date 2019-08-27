/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.web.commands;

import br.com.hibernate.core.IFachada;
import br.com.hibernate.core.controle.Fachada;

/**
 *
 * @author bocao
 */
public abstract class AbstractCommand implements ICommand{
    protected IFachada fachada = new Fachada();
}
