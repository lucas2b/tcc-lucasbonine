package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import tcc.dao.CidadesDAO;
import tcc.dao.UfDAO;
import tcc.dtos.TB_CIDADES;
import tcc.dtos.TB_UF;


public class CidadesBEAN {

	//Atributos
	private TB_UF estadoSelecionado;
	private TB_CIDADES cidadeSelecionada = new TB_CIDADES();
	private int idEstadoSelecionado;


	//DAOs
	private UfDAO ufDAO = new UfDAO();
	private CidadesDAO cidadeDAO = new CidadesDAO();


	//---------- GETTERS E SETTERS --------
	
	public TB_CIDADES getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(TB_CIDADES cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public TB_UF getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(TB_UF estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
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
	
	public String adicionarCidade() throws ClassNotFoundException, SQLException{
		cidadeSelecionada.setUF(estadoSelecionado);
		cidadeDAO.adicionar(cidadeSelecionada);
		
		return "refresh";
	}
	
	public String startEditarCidade(){
		System.out.println("chegou no método Start Adicionar Cidade");
		return "refresh";
	}
	
	
	public String finishEditarCidade(){
		return null;
	}

}