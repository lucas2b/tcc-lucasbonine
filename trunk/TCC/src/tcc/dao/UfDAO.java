package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_UF;

public class UfDAO extends DatabaseUtil implements InterfaceDAO<TB_UF>{

	@Override
	public LinkedList<TB_UF> listarTodos() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_UF parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_UF popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_UF buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
