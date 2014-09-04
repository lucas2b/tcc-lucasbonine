package tcc.conversores;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import tcc.dao.CidadesDAO;
import tcc.dtos.TB_CIDADES;

public class ConversorCidade implements Converter {

	CidadesDAO cidadeDAO = new CidadesDAO();
	
	public ConversorCidade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			return cidadeDAO.buscarPorID(Integer.valueOf(arg2));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		TB_CIDADES cidade = new TB_CIDADES();
		return String.valueOf(cidade.getID_CIDADE());
	}

}
