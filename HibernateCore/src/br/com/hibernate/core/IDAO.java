/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.core;

import br.com.hibernate.model.bean.EntidadeDominio;
import java.util.List;

/**
 *
 * @author bocao
 */
public interface IDAO {
    
    public void salvar(EntidadeDominio entidade);
    public void alterar(EntidadeDominio entidade);
    public void deletar(EntidadeDominio entidade);
    public EntidadeDominio listarPorId(EntidadeDominio entidade);
    public abstract List<EntidadeDominio> litarTudo();
}
