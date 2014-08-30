package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PERGUNTAS;

public class PerguntasDAO extends DatabaseUtil implements InterfaceDAO<TB_PERGUNTAS>{

	@Override
	public LinkedList<TB_PERGUNTAS> listarTodos()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_PERGUNTAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_PERGUNTAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
