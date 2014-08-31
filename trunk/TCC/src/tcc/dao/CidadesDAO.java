package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_CIDADES;

public class CidadesDAO extends DatabaseUtil implements InterfaceDAO<TB_CIDADES> {
	
	private UfDAO ufDAO = new UfDAO();
	
	@Override
	public LinkedList<TB_CIDADES> listarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<TB_CIDADES> retorno = new LinkedList<TB_CIDADES>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_CIDADES");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_CIDADES parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_CIDADES(NOME_CIDADE, ID_UF) VALUES (?, ?)");
		ps.setString(1, parametro.getNOME_CIDADE());
		ps.setInt(2, parametro.getUF().getID_UF());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_CIDADES parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_CIDADES SET NOME_CIDADE=? WHERE ID_CIDADE=?");
		ps.setString(1, parametro.getNOME_CIDADE());
		ps.setInt(2, parametro.getUF().getID_UF());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_CIDADES parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_CIDADES WHERE ID_CIDADE=?");
		ps.setInt(1, parametro.getID_CIDADE());
		
		return ps.execute();
	}

	@Override
	public TB_CIDADES popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_CIDADES cidade = new TB_CIDADES();
		cidade.setID_CIDADE(rs.getInt("ID_CIDADE"));
		cidade.setNOME_CIDADE("NOME_CIDADE");
		cidade.setUF(ufDAO.buscarPorID(rs.getInt("ID_UF")));
		return cidade;
	}

	@Override
	public TB_CIDADES buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_CIDADES WHERE ID_CIDADE="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
