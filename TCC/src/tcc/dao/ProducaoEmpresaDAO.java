package tcc.dao;

import java.sql.PreparedStatement;
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
		LinkedList<TB_PRODUCAO_EMPRESA> retorno = new LinkedList<TB_PRODUCAO_EMPRESA>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PRODUCAO_EMPRESA");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		return retorno;
	}

	@Override
	public boolean adicionar(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_PRODUCAO_EMPRESA(ID_EMPRESA, ID_PRODUTO, PESO_PRODUCAO) VALUES (?,?,?)");
		ps.setInt(1, parametro.getEMPRESA().getID_EMPRESA());
		ps.setInt(2, parametro.getPRODUTO().getID_PRODUTO());
		ps.setFloat(3, parametro.getPESO_PRODUCAO());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_PRODUCAO_EMPRESA SET ID_EMPRESA=?, ID_PRODUTO=?, PESO_PRODUCAO=? WHERE ID_PRODUCAO_EMPRESA=?");
		ps.setInt(1, parametro.getEMPRESA().getID_EMPRESA());
		ps.setInt(2, parametro.getPRODUTO().getID_PRODUTO());
		ps.setFloat(3, parametro.getPESO_PRODUCAO());
		ps.setInt(4, parametro.getID_PRODUCAO_EMPRESA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_PRODUCAO_EMPRESA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_PRODUCAO_EMPRESA WHERE ID_PRODUCAO_EMPRESA=?");
		ps.setInt(1, parametro.getID_PRODUCAO_EMPRESA());
		return ps.execute();
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
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PRODUCAO_EMPRESA WHERE ID_PRODUCAO_EMPRESA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
