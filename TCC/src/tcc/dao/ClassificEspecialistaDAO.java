package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_CLASSIFIC_ESPECIALISTA;

public class ClassificEspecialistaDAO extends DatabaseUtil implements InterfaceDAO<TB_CLASSIFIC_ESPECIALISTA>{

	@Override
	public LinkedList<TB_CLASSIFIC_ESPECIALISTA> listarTodos()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_CLASSIFIC_ESPECIALISTA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_CLASSIFIC_ESPECIALISTA popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_CLASSIFIC_ESPECIALISTA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
