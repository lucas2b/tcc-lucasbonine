package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import tcc.dao.CidadesDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.UfDAO;
import tcc.dtos.TB_CIDADES;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_UF;

public class EmpresasBEAN {
	
	//DAOs reus�veis
	private UfDAO ufDAO = new UfDAO();
	private CidadesDAO cidadeDAO = new CidadesDAO();
	private EmpresasDAO empresaDAO = new EmpresasDAO();

	//Atributos de tela
	private TB_UF estadoSelecionado;
	private TB_EMPRESAS empresaSelecionada = new TB_EMPRESAS();
	private TB_EMPRESAS empresaSelecionadaDetalhes = new TB_EMPRESAS();
	
	//Flags
	private boolean flagEstadoSelecionado = false;
	
	//Componentes de tela
	private HtmlSelectOneMenu comboCidades;

	//------------------------------ GETTERS AND SETTERS --------------------------------
	public HtmlSelectOneMenu getComboCidades() {
		return comboCidades;
	}

	public void setComboCidades(HtmlSelectOneMenu comboCidades) {
		this.comboCidades = comboCidades;
	}

	public TB_EMPRESAS getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(TB_EMPRESAS empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	public TB_UF getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(TB_UF estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	
	public TB_EMPRESAS getEmpresaSelecionadaDetalhes() {
		return empresaSelecionadaDetalhes;
	}

	public void setEmpresaSelecionadaDetalhes(TB_EMPRESAS empresaSelecionadaDetalhes) {
		this.empresaSelecionadaDetalhes = empresaSelecionadaDetalhes;
	}

	//------------------------------ ADICIONAR EMPRESA ---------------------------------

	//Combo de listagem dos estados que lista todos estados
	public List<SelectItem> getEstados() throws ClassNotFoundException, SQLException{
		List<SelectItem> retorno = new LinkedList<SelectItem>();
	
		for(TB_UF estado : ufDAO.listarTodos()){
			retorno.add(new SelectItem(estado, estado.getNOME_UF()));
		}
		return retorno;
	}
		
	//Action da mudan�a do Combo de estados
	public void comboEstadoMudou(ValueChangeEvent evt){
		flagEstadoSelecionado = true;
	}
		
	//Popula combo cidades de acordo com o estado selecionado
	public List<SelectItem> getCidades() throws ClassNotFoundException, SQLException{
		List<SelectItem> retorno = new LinkedList<SelectItem>();
	
		if(flagEstadoSelecionado){
			for(TB_CIDADES cidade : cidadeDAO.listarCidadesPorIdUF(estadoSelecionado.getID_UF()))
				retorno.add(new SelectItem(cidade, cidade.getNOME_CIDADE()));
		}else{
			//Procura o primeiro estado cadastrado para lista as cidades apropriadas
			int primeiroIdDosEstados = ufDAO.listarTodos().get(0).getID_UF();
				
			for(TB_CIDADES cidade : cidadeDAO.listarCidadesPorIdUF(primeiroIdDosEstados))
				retorno.add(new SelectItem(cidade, cidade.getNOME_CIDADE()));
		}
		
		return retorno;
	}
	
	public List<TB_EMPRESAS> getEmpresas() throws ClassNotFoundException, SQLException{
		return empresaDAO.listarTodos();
	}
		
	//------------------------------ BOT�ES TELA ADICIONAR ------------------------------
	
	public String acaoBotaoSalvar() throws ClassNotFoundException, SQLException{			
		if(comboCidades.getValue() != null){
			empresaDAO.adicionar(empresaSelecionada);
			empresaSelecionada = new TB_EMPRESAS();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastro realizado!", "Cadastro realizado!"));
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cidade n�o selecionada!", "Cidade n�o selecionada!"));
		
		return "listarEmpresas";
	}
	
	public String acaoBotaoCancelarAdicaoCidade(){
		empresaSelecionada = new TB_EMPRESAS(); //zera novamente o campo respons�vel pela persist�ncia para n�o dar pau
		return "retornarAdicaoEmpresa";
	}
	
	public String acaoBotaoCancelarAdicaoEmpresa(){
		empresaSelecionada = new TB_EMPRESAS(); //zera novamente o campo respons�vel pela persist�ncia para n�o dar pau
		return "listarEmpresas";
	}
	
	public String acaoBotaoVoltarDetalhes(){
		empresaSelecionada = new TB_EMPRESAS(); //zera novamente o campo respons�vel pela persist�ncia para n�o dar pau
		return "listarEmpresas";
	}
	
	//-------------------------- BOT�ES TELA LISTAR EMPRESAS -----------------------------
	
	public String visualizarDetalhes(){
		return "paginaDetalhesEmpresa";
	}
	
	
}
