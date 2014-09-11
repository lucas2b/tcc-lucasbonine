package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_PERGUNTAS;

public class AlternativasDAO extends DatabaseUtil implements InterfaceDAO<TB_ALTERNATIVAS>{

	PerguntasDAO perguntasDAO = new PerguntasDAO();
	
	@Override
	public LinkedList<TB_ALTERNATIVAS> listarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<TB_ALTERNATIVAS> retorno = new LinkedList<TB_ALTERNATIVAS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_ALTERNATIVAS");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_ALTERNATIVAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_ALTERNATIVAS(ID_PERGUNTA, COD_ALTERNATIVA, ALTERNATIVA_TXT) VALUES (?,?,?)");
		ps.setInt(1, parametro.getPERGUNTA().getID_PERGUNTA());
		ps.setString(2, parametro.getCOD_ALTERNATIVA());
		ps.setString(3, parametro.getALTERNATIVA_TXT());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_ALTERNATIVAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_ALTERNATIVAS SET ID_PERGUNTA=?, COD_ALTERNATIVA=?, ALTERNATIVA_TXT=? WHERE ID_ALTERNATIVA=?");
		ps.setInt(1, parametro.getPERGUNTA().getID_PERGUNTA());
		ps.setString(2, parametro.getCOD_ALTERNATIVA());
		ps.setString(3, parametro.getALTERNATIVA_TXT());
		ps.setInt(4, parametro.getID_ALTERNATIVA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_ALTERNATIVAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_ALTERNATIVAS WHERE ID_ALTERNATIVA=?");
		ps.setInt(1, parametro.getID_ALTERNATIVA());
		
		return ps.execute();
	}

	@Override
	public TB_ALTERNATIVAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_ALTERNATIVAS alternativa = new TB_ALTERNATIVAS();
		alternativa.setID_ALTERNATIVA(rs.getInt("ID_ALTERNATIVA"));
		alternativa.setPERGUNTA(perguntasDAO.buscarPorID(rs.getInt("ID_PERGUNTA")));
		alternativa.setCOD_ALTERNATIVA(rs.getString("COD_ALTERNATIVA"));
		alternativa.setALTERNATIVA_TXT(rs.getString("ALTERNATIVA_TXT"));
		
		return alternativa;
	}

	@Override
	public TB_ALTERNATIVAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_ALTERNATIVAS WHERE ID_ALTERNATIVA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}
	
	//-------------------------------- MÉTODOS ADICIONAIS -------------------------------
	
	public List<TB_ALTERNATIVAS> buscarAlternativasPorPergunta(TB_PERGUNTAS pergunta) throws ClassNotFoundException, SQLException{
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_ALTERNATIVAS WHERE ID_PERGUNTA="+pergunta.getID_PERGUNTA());
		
		LinkedList<TB_ALTERNATIVAS> retorno = new LinkedList<TB_ALTERNATIVAS>();
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
			
	}
	
	//Retorna uma lista de listas contendo cada uma um pacote de alternativas
	public List<List<TB_ALTERNATIVAS>> retonarPacotesDeAlternativas() throws ClassNotFoundException, SQLException{
		
		List<List<TB_ALTERNATIVAS>> retorno = new LinkedList<List<TB_ALTERNATIVAS>>();
		
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			retorno.add(buscarAlternativasPorPergunta(pergunta));
		}
		
		return retorno;
		
		/*
		 * Retorna uma lista com várias listas internas.
		 * Cada lista interna contém várias instâncias de TB_ALTERNATIVAS,
		 * todas referentes a uma questão em particular.
		 * A próxima lista contém as alternativas de outra questão em particular.
		 * 
		 * Para saber a qual questão cada pacote de alternativas se refere, basta
		 * consultar a propriedade PERGUNTA.getIdPergunta() de alguma delas.
		 */
		
	}
	
	

}
