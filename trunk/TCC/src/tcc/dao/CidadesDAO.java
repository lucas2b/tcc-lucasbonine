package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_CIDADES;

public class CidadesDAO extends DatabaseUtil implements InterfaceDAO<TB_CIDADES> {

	@Override
	public LinkedList<TB_CIDADES> listarTodos() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_CIDADES parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_CIDADES parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_CIDADES parametro) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_CIDADES popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_CIDADES buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
