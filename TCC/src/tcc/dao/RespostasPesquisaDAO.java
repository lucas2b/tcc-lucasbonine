package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class RespostasPesquisaDAO extends DatabaseUtil implements InterfaceDAO<TB_RESPOSTAS_PESQUISA>{

	@Override
	public LinkedList<TB_RESPOSTAS_PESQUISA> listarTodos()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adicionar(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TB_RESPOSTAS_PESQUISA popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TB_RESPOSTAS_PESQUISA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
