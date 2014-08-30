package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PRODUTOS;

public class ProdutosDAO extends DatabaseUtil implements InterfaceDAO<TB_PRODUTOS>{

	@Override
	public LinkedList<TB_PRODUTOS> listarTodos() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_PRODUTOS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_PRODUTOS parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_PRODUTOS parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_PRODUTOS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_PRODUTOS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
