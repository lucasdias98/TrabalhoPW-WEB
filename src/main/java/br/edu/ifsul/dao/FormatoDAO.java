package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Formato;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Lucas
 */
@Stateful
public class FormatoDAO<TIPO> extends DAOGenerico<Formato> implements Serializable {
    public FormatoDAO(){
        super();
        classePersistente = Formato.class;
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
}
