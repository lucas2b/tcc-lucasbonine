package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_ALTERNATIVAS;

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

}
