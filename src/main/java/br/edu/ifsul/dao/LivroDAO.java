package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Livro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Lucas
 */
@Stateful
public class LivroDAO<TIPO> extends DAOGenerico<Livro> implements Serializable {
    public LivroDAO(){
        super();
        classePersistente = Livro.class;
        //definição da lista de ordenações
        listaOrdem.add(new Ordem("ISBN", "ISBN", "="));
        listaOrdem.add(new Ordem("titulo", "Titulo", "like"));
        // definição da ordem atual
        ordemAtual = listaOrdem.get(1); // pega o segundo da lista de ordens
        //criando a instancia do conversor
        converterOrdem = new ConverterOrdem();  
        //associando a lista de ordens ao conversor
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Livro localizar(Object id) throws Exception {
        Livro objeto = em.find(Livro.class, id);
        // Deve-se inicializar a coleção ou coleçoes do objeto para não
        // dar um erro de lazy inicialization exception
        objeto.getAutores().size();        
        return objeto;
    }
    
}
