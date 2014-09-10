package tcc.conversores;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import tcc.dao.AlternativasDAO;
import tcc.dtos.TB_ALTERNATIVAS;

public class ConversorAlternativas implements Converter {

	public ConversorAlternativas() {
		// TODO Auto-generated constructor stub
	}
	AlternativasDAO alt = new AlternativasDAO();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			return alt.buscarPorID(Integer.valueOf(arg2));
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
		// TODO Auto-generated method stub
		TB_ALTERNATIVAS alter = ((TB_ALTERNATIVAS)arg2);
		return String.valueOf(alter.getID_ALTERNATIVA());
	}

}
