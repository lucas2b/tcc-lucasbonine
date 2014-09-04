package tcc.conversores;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import tcc.dao.UfDAO;
import tcc.dtos.TB_UF;

public class ConversorEstado implements Converter {
	
	UfDAO ufDAO = new UfDAO();
	
	public ConversorEstado() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		try {
			return ufDAO.buscarPorID(Integer.parseInt(arg2));
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
		TB_UF uf = (TB_UF)arg2;
		return String.valueOf(uf.getID_UF());
	}
}
