package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PERGUNTAS;

public class PerguntasDAO extends DatabaseUtil implements InterfaceDAO<TB_PERGUNTAS>{

	@Override
	public LinkedList<TB_PERGUNTAS> listarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<TB_PERGUNTAS> retorno = new LinkedList<TB_PERGUNTAS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PERGUNTAS");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_PERGUNTAS(PERGUNTA_TXT, MULTI_SELECAO) VALUES (?,?)");
		ps.setString(1, parametro.getPERGUNTA_TXT());
		ps.setBoolean(2, parametro.isMULTI_SELECAO());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_PERGUNTAS SET PERGUNTA_TXT=?, MULTI_SELECAO=? WHERE ID_PERGUNTA=?");
		ps.setString(1, parametro.getPERGUNTA_TXT());
		ps.setInt(2, parametro.getID_PERGUNTA());
		ps.setBoolean(3, parametro.isMULTI_SELECAO());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_PERGUNTAS WHERE ID_PERGUNTA=?");
		ps.setInt(1, parametro.getID_PERGUNTA());
		
		return ps.execute();
	}

	@Override
	public TB_PERGUNTAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_PERGUNTAS pergunta = new TB_PERGUNTAS();
		pergunta.setID_PERGUNTA(rs.getInt("ID_PERGUNTA"));
		pergunta.setPERGUNTA_TXT(rs.getString("PERGUNTA_TXT"));
		pergunta.setMULTI_SELECAO(rs.getBoolean("MULTI_SELECAO"));
		
		return pergunta;
	}

	@Override
	public TB_PERGUNTAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PERGUNTAS WHERE ID_PERGUNTA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}
	
	public int retornarUltimoID() throws ClassNotFoundException, SQLException{
		int retorno = 0;
		ResultSet rs = getStatement().executeQuery("SELECT ID_PERGUNTA FROM TB_PERGUNTAS");
		
		while(rs.next())
			retorno = rs.getInt("ID_PERGUNTA");
		
		return retorno;
		
	}

}
