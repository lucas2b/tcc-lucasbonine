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

	private TB_UF estadoSelecionado;
	private UfDAO ufDAO = new UfDAO();
	private CidadesDAO cidadeDAO = new CidadesDAO();
	private int idEstadoSelecionado;
	private boolean adicionarCidade = false;
	private boolean editarCidade = false;


	//---------- GETTERS E SETTERS --------
	
	public boolean isEditarCidade() {
		return editarCidade;
	}

	public void setEditarCidade(boolean editarCidade) {
		this.editarCidade = editarCidade;
	}

	public boolean isAdicionarCidade() {
		return adicionarCidade;
	}

	public void setAdicionarCidade(boolean adicionarCidade) {
		this.adicionarCidade = adicionarCidade;
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
	
	public String startAdicionarCidade(){
		adicionarCidade = true;
		return "refresh";
	}
	
	public String startEditarCidade(){
		System.out.println("chegou no método Start Adicionar Cidade");
		editarCidade = true;
		return "refresh";
	}
	
	public String finishAdicionarCidade(){
		adicionarCidade = false;
		return null;
	}
	
	public String finishEditarCidade(){
		return null;
	}

}