package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import tcc.dao.UfDAO;
import tcc.dtos.TB_UF;


public class CidadesBEAN {

	UfDAO ufDAO = new UfDAO();
	
	//SelectOneList
	public List<SelectItem> getEstados() throws ClassNotFoundException, SQLException{
		List<SelectItem> retorno = new LinkedList<SelectItem>();
		
		for(TB_UF estado : ufDAO.listarTodos()){
			retorno.add(new SelectItem(estado, estado.getNOME_UF()));
		}
		return retorno;
	}

}