package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_CLASSIFIC_ESPECIALISTA;

public class ClassificEspecialistaDAO extends DatabaseUtil implements InterfaceDAO<TB_CLASSIFIC_ESPECIALISTA>{

	@Override
	public LinkedList<TB_CLASSIFIC_ESPECIALISTA> listarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<TB_CLASSIFIC_ESPECIALISTA> retorno = new LinkedList<TB_CLASSIFIC_ESPECIALISTA>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_CLASSIFIC_ESPECIALISTA");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		return retorno;
	}

	@Override
	public boolean adicionar(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_CLASSIFIC_ESPECIALISTA(CATEGORIA_DE_CLASSIFIC, CONCEITO) VALUES (?,?)");
		ps.setString(1, parametro.getCATEGORIA_DE_CLASSIFIC());
		ps.setString(2, parametro.getCONCEITO());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_CLASSIFIC_ESPECIALISTA SET CATEGORIA_DE_CLASSIFIC=?, CONCEITO=? WHERE ID_CLASSIFIC_ESPECIALISTA=?");
		ps.setString(1, parametro.getCATEGORIA_DE_CLASSIFIC());
		ps.setString(2, parametro.getCONCEITO());
		ps.setInt(3, parametro.getID_CLASSIFIC_ESPECIALISTA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_CLASSIFIC_ESPECIALISTA WHERE ID_CLASSIFIC_ESPECIALISTA=?");
		ps.setInt(1, parametro.getID_CLASSIFIC_ESPECIALISTA());
		
		return ps.execute();
	}

	@Override
	public TB_CLASSIFIC_ESPECIALISTA popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_CLASSIFIC_ESPECIALISTA classific_especialista = new TB_CLASSIFIC_ESPECIALISTA();
		classific_especialista.setID_CLASSIFIC_ESPECIALISTA(rs.getInt("ID_CLASSIFIC_ESPECIALISTA"));
		classific_especialista.setCATEGORIA_DE_CLASSIFIC(rs.getString("CATEGORIA_DE_CLASSIFIC"));
		classific_especialista.setCONCEITO(rs.getString("CONCEITO"));
		
		return classific_especialista;
	}

	@Override
	public TB_CLASSIFIC_ESPECIALISTA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_CLASSIFIC_ESPECIALISTA WHERE ID_CLASSIFIC_ESPECIALISTA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
