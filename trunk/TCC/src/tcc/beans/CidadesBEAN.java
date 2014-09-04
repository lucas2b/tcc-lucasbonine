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


	//---------- GETTERS E SETTERS --------
	
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

}