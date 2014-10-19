package tcc.conversores;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import tcc.dao.PerguntasDAO;
import tcc.dtos.TB_PERGUNTAS;

public class ConversorPergunta implements Converter {

	
	public ConversorPergunta() {
		// TODO Auto-generated constructor stub
	}
	
	PerguntasDAO perguntasDAO = new PerguntasDAO();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			return perguntasDAO.buscarPorID(Integer.parseInt(arg2));
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
		TB_PERGUNTAS pergunta = (TB_PERGUNTAS)arg2;
		return String.valueOf(pergunta.getID_PERGUNTA());
	}

}
