package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PRODUCAO_EMPRESA;

public class ProducaoEmpresaDAO extends DatabaseUtil implements InterfaceDAO<TB_PRODUCAO_EMPRESA>{

	EmpresasDAO empresaDAO = new EmpresasDAO();
	ProdutosDAO produtosDAO = new ProdutosDAO();
	
	@Override
	public LinkedList<TB_PRODUCAO_EMPRESA> listarTodos()
			throws ClassNotFoundException, SQLException {
		
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
		TB_PRODUCAO_EMPRESA producaoEmpresa = new TB_PRODUCAO_EMPRESA();
		producaoEmpresa.setID_PRODUCAO_EMPRESA(rs.getInt("ID_PRODUCAO_EMPRESA"));
		producaoEmpresa.setEMPRESA(empresaDAO.buscarPorID(rs.getInt("ID_EMPRESA")));
		producaoEmpresa.setPRODUTO(produtosDAO.buscarPorID(rs.getInt("ID_PRODUTO")));
		producaoEmpresa.setPESO_PRODUCAO(rs.getFloat("PESO_PRODUCAO"));
		
		return producaoEmpresa;
	}

	@Override
	public TB_PRODUCAO_EMPRESA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
