package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PRODUCAO_EMPRESA;

public class ProducaoEmpresaDAO extends DatabaseUtil implements InterfaceDAO<TB_PRODUCAO_EMPRESA>{

	@Override
	public LinkedList<TB_PRODUCAO_EMPRESA> listarTodos()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_PRODUCAO_EMPRESA popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_PRODUCAO_EMPRESA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
