package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;






import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import tcc.dao.CidadesDAO;
import tcc.dao.UfDAO;
import tcc.dtos.TB_CIDADES;
import tcc.dtos.TB_UF;


public class CidadesBEAN {

	//Atributos
	private TB_CIDADES cidadeSelecionada = new TB_CIDADES();
	private int idEstadoSelecionado;
	private HtmlInputText txtNomeCidade;
	
	//Flags
	private boolean novoRegistro = true;
	private boolean cancelarEdicao = false;

	//DAOs
	private UfDAO ufDAO = new UfDAO();
	private CidadesDAO cidadeDAO = new CidadesDAO();


	//---------- GETTERS E SETTERS --------
	
	public boolean isCancelarEdicao() {
		return cancelarEdicao;
	}

	public void setCancelarEdicao(boolean cancelarEdicao) {
		this.cancelarEdicao = cancelarEdicao;
	}

	public HtmlInputText getTxtNomeCidade() {
		return txtNomeCidade;
	}

	public void setTxtNomeCidade(HtmlInputText txtNomeCidade) {
		this.txtNomeCidade = txtNomeCidade;
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}

	public void setNovoRegistro(boolean novoRegistro) {
		this.novoRegistro = novoRegistro;
	}

	public TB_CIDADES getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(TB_CIDADES cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	
	//---------- MÉTODOS -------------
	
	//Combo de listagem dos estados
	public List<SelectItem> getEstados() throws ClassNotFoundException, SQLException{
		List<SelectItem> retorno = new LinkedList<SelectItem>();
		
		for(TB_UF estado : ufDAO.listarTodos()){
			retorno.add(new SelectItem(estado, estado.getNOME_UF()));
		}
		return retorno;
	}
	
	//Popula a table com cidades por estado selecionado no Combo
	public List<TB_CIDADES> getCidades() throws ClassNotFoundException, SQLException{
		return cidadeDAO.listarCidadesPorIdUF(idEstadoSelecionado);
	}
	
	//Action da mudança do Combo de estados
	public void comboEstadoMudou(ValueChangeEvent evt){
		idEstadoSelecionado = ((TB_UF)evt.getNewValue()).getID_UF();

	}
	
	//salva tanto um novo registro quanto uma edição
	public String acaoBotaoSalvar() throws ClassNotFoundException, SQLException{
		if(novoRegistro){
			txtNomeCidade.setValue("");
			cidadeDAO.adicionar(cidadeSelecionada);
		}else{
			cidadeDAO.editar(cidadeSelecionada);
		}
		
		FacesContext.getCurrentInstance().renderResponse();
		return "refresh";
	}
	
	//Traz o nome da tabela para o campo texto para editar
	public String acaoBotaoEditar(){
		txtNomeCidade.setValue(cidadeSelecionada.getNOME_CIDADE());
		cancelarEdicao = true;
		FacesContext.getCurrentInstance().renderResponse();
		return "refresh";
	}
	
	//cancela a operação de edição e torna uma operação de adição
	public String botaoCancelarEdicao(){
		txtNomeCidade.setValue("");
		novoRegistro = true;
		cancelarEdicao = false;
		FacesContext.getCurrentInstance().renderResponse();
		return "refresh";
	}

}