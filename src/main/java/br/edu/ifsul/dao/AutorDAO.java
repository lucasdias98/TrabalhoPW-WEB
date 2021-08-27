package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Lucas
 */
@Stateful
public class AutorDAO<TIPO> extends DAOGenerico<Autor> implements Serializable {
    public AutorDAO(){
        super();
        classePersistente = Autor.class;
        //definição da lista de ordenações
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
                listaOrdem.add(new Ordem("bibliografia", "Bibliografia", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // pega o segundo da lista de ordens
        //criando a instancia do conversor
        converterOrdem = new ConverterOrdem();  
        //associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }
}