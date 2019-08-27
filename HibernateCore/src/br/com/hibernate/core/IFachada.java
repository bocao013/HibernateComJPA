/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core;

import br.com.hibernate.core.aplicacao.Resultado;
import br.com.hibernate.model.bean.EntidadeDominio;

/**
 *
 * @author bocao
 */
public interface IFachada {
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado alterar(EntidadeDominio entidade);
    public Resultado deletar(EntidadeDominio entidade);
    public Resultado listar(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
    public void regrasDeNegocio(EntidadeDominio entidade, String operacao);
    
}
