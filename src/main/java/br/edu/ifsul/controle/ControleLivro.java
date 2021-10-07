package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Lucas
 */
@Named(value = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable{
    
    @EJB
    private LivroDAO<Livro> dao;
    private Livro objeto;
    @EJB
    private IdiomaDAO<Idioma> daoIdioma;
    @EJB
    private FormatoDAO<Formato> daoFormato;
    @EJB
    protected CatalogoDAO<Catalogo> daoCatalogo;
    @EJB
    protected AutorDAO<Autor> daoAutor;
    protected Autor autor;
    
    public ControleLivro(){
        
    }
    
    public void imprimeLivros(){
        HashMap parametros = new HashMap(); 
        UtilRelatorios.imprimeRelatorio("relatorioLivros", parametros, dao.getListaTodos());
    }
    
    public void removerAutor(Autor obj) {
        objeto.getAutores().remove(obj);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }
    
    public void adicionarAutor() {
        if (!objeto.getAutores().contains(autor)) {
            if (autor != null) {
                objeto.getAutores().add(autor);
                Util.mensagemInformacao("Autor adicionado com sucesso!");
            } else {
                Util.mensagemErro("Selecione o Autor");
            }
        } else {
            Util.mensagemErro("O Livro já possui este autor");
        }
    }
    
    public String listar(){
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Livro());
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
            if (objeto.getISBN() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
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

    public CatalogoDAO<Catalogo> getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO<Catalogo> daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public Autor getAutor() {
        return autor;
    }
    
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
