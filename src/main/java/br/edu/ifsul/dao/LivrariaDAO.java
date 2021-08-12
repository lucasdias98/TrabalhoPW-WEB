package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Livraria;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Lucas
 */
@Stateful
public class LivrariaDAO<TIPO> extends DAOGenerico<Livraria> implements Serializable {
    public LivrariaDAO(){
        super();
        classePersistente = Livraria.class;
    }
}
