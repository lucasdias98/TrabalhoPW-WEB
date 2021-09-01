package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas
 */
@Named(value = "controleLivro")
@ViewScoped
public class ControleCatalogo implements Serializable{
    
    @EJB
    private CatalogoDAO<Catalogo> dao;
    private Catalogo objeto;
    protected Livro livro;
    protected Boolean novoLivro;
    @EJB
    protected IdiomaDAO<Idioma> daoIdioma;
    @EJB
    protected FormatoDAO<Formato> daoFormato;

    public ControleCatalogo(){
        
    }
    
    public void novoLivro(){
        novoLivro = true;
        livro = new Livro();
    }
    
    public void alterarLivro(int index){
        livro = objeto.getLivros().get(index);
        novoLivro = false;
    }
    
    public void salvarLivro(){
        if (novoLivro){
            objeto.adicionarLivro(livro);
        }
        Util.mensagemInformacao("Livro adicionado ou atualizado com sucesso");
    }
    
    public void removerLivro(int index){
        objeto.removerLivro(index);
        Util.mensagemInformacao("Livro removido com sucesso!");
    }
    
    public String listar(){
        return "/privado/catalogo/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Catalogo());
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public CatalogoDAO<Catalogo> getDao() {
        return dao;
    }

    public void setDao(CatalogoDAO<Catalogo> dao) {
        this.dao = dao;
    }

    public Catalogo getObjeto() {
        return objeto;
    }

    public void setObjeto(Catalogo objeto) {
        this.objeto = objeto;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Boolean getNovoLivro() {
        return novoLivro;
    }

    public void setNovoLivro(Boolean novoLivro) {
        this.novoLivro = novoLivro;
    }

    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
    }
    
}
