package tcc.conversores;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import tcc.dao.EmpresasDAO;
import tcc.dtos.TB_EMPRESAS;

public class ConversorEmpresa implements Converter {

	public ConversorEmpresa() {
		// TODO Auto-generated constructor stub
	}

	EmpresasDAO empresaDAO = new EmpresasDAO();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			return empresaDAO.buscarPorID(Integer.valueOf(arg2));
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
		TB_EMPRESAS empresa = (TB_EMPRESAS)arg2;
		return String.valueOf(empresa.getID_EMPRESA());
	}

}
