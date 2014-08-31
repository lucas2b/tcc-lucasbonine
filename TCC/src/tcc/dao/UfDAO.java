package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_UF;

public class UfDAO extends DatabaseUtil implements InterfaceDAO<TB_UF>{

	@Override
	public LinkedList<TB_UF> listarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<TB_UF> retorno = new LinkedList<TB_UF>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_UF");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_UF (NOME_UF) VALUES (?)");
		ps.setString(1, parametro.getNOME_UF());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_UF SET NOME_UF=? WHERE ID_UF=?");
		ps.setString(1, parametro.getNOME_UF());
		ps.setInt(2, parametro.getID_UF());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_UF WHERE ID_UF=?");
		ps.setInt(1, parametro.getID_UF());
		
		return ps.execute();
	}

	@Override
	public TB_UF popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_UF uf = new TB_UF();
		uf.setID_UF(rs.getInt("ID_UF"));
		uf.setNOME_UF(rs.getString("NOME_UF"));
		
		return uf;
	}

	@Override
	public TB_UF buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_UF WHERE ID_UF="+id);
		
		if(rs.next()){
			return popular(rs);
		}else{			
			return null;
		}
	}

}