package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_USUARIOS;

public class UsuariosDAO extends DatabaseUtil implements InterfaceDAO<TB_USUARIOS>{

	@Override
	public LinkedList<TB_USUARIOS> listarTodos() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_USUARIOS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_USUARIOS parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_USUARIOS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_USUARIOS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_USUARIOS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
