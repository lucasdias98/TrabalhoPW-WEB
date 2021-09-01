/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class CatalogoDAO<TIPO> extends DAOGenerico<Catalogo> implements Serializable {
    
    public CatalogoDAO(){
        super();
        classePersistente = Catalogo.class;
        //definição da lista de ordenações
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // pega o segundo da lista de ordens
        //criando a instancia do conversor
        converterOrdem = new ConverterOrdem();  
        //associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Catalogo localizar(Object id) throws Exception {
        Catalogo objeto = em.find(Catalogo.class, id);
        // Deve-se inicializar a coleção ou coleçoes do objeto para não
        // dar um erro de lazy inicialization exception
        objeto.getLivros().size();        
        return objeto;
    }
    
}
