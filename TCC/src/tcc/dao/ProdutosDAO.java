package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_PRODUTOS;

public class ProdutosDAO extends DatabaseUtil implements InterfaceDAO<TB_PRODUTOS>{

	@Override
	public LinkedList<TB_PRODUTOS> listarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<TB_PRODUTOS> retorno = new LinkedList<TB_PRODUTOS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PRODUTOS");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_PRODUTOS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_PRODUTOS(COD_ALTERNATIVA, PRODUTO_DESC) VALUES (?,?)");
		ps.setString(1, parametro.getCOD_ALTERNATIVA());
		ps.setString(2, parametro.getPRODUTO_DESC());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_PRODUTOS parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_PRODUTOS SET COD_ALTERNATIVA=?, PRODUTO_DESC=? WHERE ID_PRODUTO=?");
		ps.setString(1, parametro.getCOD_ALTERNATIVA());
		ps.setString(2, parametro.getPRODUTO_DESC());
		ps.setInt(3, parametro.getID_PRODUTO());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_PRODUTOS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_PRODUTOS WHERE ID_PRODUTO=?");
		ps.setInt(1, parametro.getID_PRODUTO());
		
		return ps.execute();
	}

	@Override
	public TB_PRODUTOS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_PRODUTOS produto = new TB_PRODUTOS();
		produto.setID_PRODUTO(rs.getInt("ID_PRODUTO"));
		produto.setCOD_ALTERNATIVA(rs.getString("COD_ALTERNATIVA"));
		produto.setPRODUTO_DESC(rs.getString("PRODUTO_DESC"));
		
		return produto;
	}

	@Override
	public TB_PRODUTOS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PRODUTOS WHERE ID_PRODUTO="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
