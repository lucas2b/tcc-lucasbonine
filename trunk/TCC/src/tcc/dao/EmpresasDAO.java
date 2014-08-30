package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_EMPRESAS;

public class EmpresasDAO extends DatabaseUtil implements InterfaceDAO<TB_EMPRESAS>{

	@Override
	public LinkedList<TB_EMPRESAS> listarTodos() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_EMPRESAS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_EMPRESAS parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_EMPRESAS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_EMPRESAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_EMPRESAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
