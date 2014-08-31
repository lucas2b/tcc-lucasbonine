package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_USUARIOS;

public class UsuariosDAO extends DatabaseUtil implements InterfaceDAO<TB_USUARIOS>{

	@Override
	public LinkedList<TB_USUARIOS> listarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<TB_USUARIOS> retorno = new LinkedList<TB_USUARIOS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_USUARIO");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		return retorno;
	}

	@Override
	public boolean adicionar(TB_USUARIOS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_USUARIOS(NOME_USUARIO, SENHA_USUARIO, NIVEL_ACESSO) VALUES (?,?,?)");
		ps.setString(1, parametro.getNOME_USUARIO());
		ps.setString(2, parametro.getSENHA_USUARIO());
		ps.setInt(3, parametro.getNIVEL_ACECSSO());
		return ps.execute();
	}

	@Override
	public boolean editar(TB_USUARIOS parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_USUARIOS SET NOME_USUARIO=?, SENHA_USUARIO=?, NIVEL_ACESSO=? WHERE ID_USUARIO=?");
		ps.setString(1, parametro.getNOME_USUARIO());
		ps.setString(2, parametro.getSENHA_USUARIO());
		ps.setInt(3, parametro.getNIVEL_ACECSSO());
		ps.setInt(4, parametro.getID_USUARIO());
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_USUARIOS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_USUARIOS WHERE ID_USUARIO=?");
		ps.setInt(1, parametro.getID_USUARIO());
		return ps.execute();
	}

	@Override
	public TB_USUARIOS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_USUARIOS usuario = new TB_USUARIOS();
		usuario.setID_USUARIO(rs.getInt("ID_USUARIO"));
		usuario.setNOME_USUARIO(rs.getString("NOME_USUARIO"));
		usuario.setSENHA_USUARIO(rs.getString("SENHA_USUARIO"));
		usuario.setNIVEL_ACECSSO(rs.getInt("NIVEL_ACESSO"));
		return usuario;
	}

	@Override
	public TB_USUARIOS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_USUARIOS WHERE ID_USUARIO="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
