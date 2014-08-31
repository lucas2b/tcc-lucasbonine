package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_EMPRESAS;

public class EmpresasDAO extends DatabaseUtil implements InterfaceDAO<TB_EMPRESAS>{

	CidadesDAO cidadeDAO = new CidadesDAO();
	ClassificEspecialistaDAO classEspecialistaDAO = new ClassificEspecialistaDAO();
	
	@Override
	public LinkedList<TB_EMPRESAS> listarTodos() throws ClassNotFoundException,
			SQLException {
		LinkedList<TB_EMPRESAS> retorno = new LinkedList<TB_EMPRESAS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_EMPRESAS");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_EMPRESAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_EMPRESAS(NOME_EMPRESA,"
				+ " ID_CIDADE,"
				+ " CNPJ_EMPRESA,"
				+ " ENDERECO_EMPRESA,"
				+ " EMPRESA_LATITUDE,"
				+ " EMPRESA_LONGITUDE,"
				+ " TELEFONE1_EMPRESA,"
				+ " TELEFONE2_EMPRESA,"
				+ " EMAIL_EMPRESA,"
				+ " SITE_EMPRESA,"
				+ " EMPREGOS_FORMAIS,"
				+ " VLR_ANUAL_VENDAS_MERC_INT,"
				+ " VLR_ANUAL_VENDAS_MERC_EXT,"
				+ " DESCRICAO_EMPRESA,"
				+ " NOME_DIRIGENTE,"
				+ " CPF_DIRIGENTE,"
				+ " TELEFONE1_DIRIGENTE,"
				+ " TELEFONE2_DIRIGENTE,"
				+ " EMAIL_DIRIGENTE,"
				+ " IDADE_DIRIGENTE,"
				+ " ESCOLARIDADE_DIRIGENTE,"
				+ " ID_CLASSIFIC_ESPECIALISTA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		ps.setString(1, parametro.getNOME_EMPRESA());
		ps.setInt(2, parametro.getCIDADE().getID_CIDADE());
		ps.setString(3, parametro.getCNPJ_EMPRESA());
		ps.setString(4, parametro.getENDERECO_EMPRESA());
		ps.setDouble(5, parametro.getEMPRESA_LATITUDE());
		ps.setDouble(6, parametro.getEMPRESA_LONGITUDE());
		ps.setString(7, parametro.getTELEFONE1_EMPRESA());
		ps.setString(8, parametro.getTELEFONE2_EMPRESA());
		ps.setString(9, parametro.getEMAIL_EMPRESA());
		ps.setString(10, parametro.getSITE_EMPRESA());
		ps.setInt(11, parametro.getEMPREGOS_FORMAIS());
		ps.setFloat(12, parametro.getVLR_ANUAL_VENDAS_MERC_INT());
		ps.setFloat(13, parametro.getVLR_ANUAL_VENDAS_MERC_EXT());
		ps.setString(14, parametro.getDESCRICAO_EMPRESA());
		ps.setString(15, parametro.getNOME_DIRIGENTE());
		ps.setString(16, parametro.getCPF_DIRIGENTE());
		ps.setString(17, parametro.getTELEFONE1_DIRIGENTE());
		ps.setString(18, parametro.getTELEFONE2_DIRIGENTE());
		ps.setString(19, parametro.getEMAIL_DIRIGENTE());
		ps.setInt(20, parametro.getIDADE_DIRIGENTE());
		ps.setString(21, parametro.getESCOLARIDADE_DIRIGENTE());
		ps.setInt(22, parametro.getCLASSIFIC_ESPECIALISTA().getID_CLASSIFIC_ESPECIALISTA());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_EMPRESAS parametro) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_EMPRESAS SET NOME_EMPRESA=?,"
				+ " ID_CIDADE=?,"
				+ " CNPJ_EMPRESA=?,"
				+ " ENDERECO_EMPRESA=?,"
				+ " EMPRESA_LATITUDE=?,"
				+ " EMPRESA_LONGITUDE=?,"
				+ " TELEFONE1_EMPRESA=?,"
				+ " TELEFONE2_EMPRESA=?,"
				+ " EMAIL_EMPRESA=?,"
				+ " SITE_EMPRESA=?,"
				+ " EMPREGOS_FORMAIS=?,"
				+ " VLR_ANUAL_VENDAS_MERC_INT=?,"
				+ " VLR_ANUAL_VENDAS_MERC_EXT=?,"
				+ " DESCRICAO_EMPRESA=?,"
				+ " NOME_DIRIGENTE=?,"
				+ " CPF_DIRIGENTE=?,"
				+ " TELEFONE1_DIRIGENTE=?,"
				+ " TELEFONE2_DIRIGENTE=?,"
				+ " EMAIL_DIRIGENTE=?,"
				+ " IDADE_DIRIGENTE=?,"
				+ " ESCOLARIDADE_DIRIGENTE=?,"
				+ " ID_CLASSIFIC_ESPECIALISTA=? WHERE ID_EMPRESA=?");
		
		ps.setString(1, parametro.getNOME_EMPRESA());
		ps.setInt(2, parametro.getCIDADE().getID_CIDADE());
		ps.setString(3, parametro.getCNPJ_EMPRESA());
		ps.setString(4, parametro.getENDERECO_EMPRESA());
		ps.setDouble(5, parametro.getEMPRESA_LATITUDE());
		ps.setDouble(6, parametro.getEMPRESA_LONGITUDE());
		ps.setString(7, parametro.getTELEFONE1_EMPRESA());
		ps.setString(8, parametro.getTELEFONE2_EMPRESA());
		ps.setString(9, parametro.getEMAIL_EMPRESA());
		ps.setString(10, parametro.getSITE_EMPRESA());
		ps.setInt(11, parametro.getEMPREGOS_FORMAIS());
		ps.setFloat(12, parametro.getVLR_ANUAL_VENDAS_MERC_INT());
		ps.setFloat(13, parametro.getVLR_ANUAL_VENDAS_MERC_EXT());
		ps.setString(14, parametro.getDESCRICAO_EMPRESA());
		ps.setString(15, parametro.getNOME_DIRIGENTE());
		ps.setString(16, parametro.getCPF_DIRIGENTE());
		ps.setString(17, parametro.getTELEFONE1_DIRIGENTE());
		ps.setString(18, parametro.getTELEFONE2_DIRIGENTE());
		ps.setString(19, parametro.getEMAIL_DIRIGENTE());
		ps.setInt(20, parametro.getIDADE_DIRIGENTE());
		ps.setString(21, parametro.getESCOLARIDADE_DIRIGENTE());
		ps.setInt(22, parametro.getCLASSIFIC_ESPECIALISTA().getID_CLASSIFIC_ESPECIALISTA());
		ps.setInt(23, parametro.getID_EMPRESA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_EMPRESAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_EMPRESAS WHERE ID_EMPRESA=?");
		ps.setInt(1, parametro.getID_EMPRESA());
		return ps.execute();
	}

	@Override
	public TB_EMPRESAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_EMPRESAS empresa = new TB_EMPRESAS();
		empresa.setNOME_EMPRESA(rs.getString("NOME_EMPRESA"));
		empresa.setCIDADE(cidadeDAO.buscarPorID(rs.getInt("ID_CIDADE")));
		empresa.setCNPJ_EMPRESA(rs.getString("CNPJ_EMPRESA"));
		empresa.setENDERECO_EMPRESA(rs.getString("ENDERECO_EMPRESA"));
		empresa.setEMPRESA_LATITUDE(rs.getDouble("EMPRESA_LATITUDE"));
		empresa.setEMPRESA_LONGITUDE(rs.getDouble("EMPRESA_LONGITUDE"));
		empresa.setTELEFONE1_EMPRESA(rs.getString("TELEFONE1_EMPRESA"));
		empresa.setTELEFONE1_EMPRESA(rs.getString("TELEFONE2_EMPRESA"));
		empresa.setEMAIL_EMPRESA(rs.getString("EMAIL_EMPRESA"));
		empresa.setSITE_EMPRESA(rs.getString("SITE_EMPRESA"));
		empresa.setEMPREGOS_FORMAIS(rs.getInt("EMPREGOS_FORMAIS"));
		empresa.setVLR_ANUAL_VENDAS_MERC_INT(rs.getFloat("VLR_ANUAL_VENDAS_MERC_INT"));
		empresa.setVLR_ANUAL_VENDAS_MERC_EXT(rs.getFloat("VLR_ANUAL_VENDAS_MERC_EXT"));
		empresa.setDESCRICAO_EMPRESA(rs.getString("DESCRICAO_EMPRESA"));
		empresa.setNOME_DIRIGENTE(rs.getString("NOME_DIRIGENTE"));
		empresa.setCPF_DIRIGENTE(rs.getString("CPF_DIRIGENTE"));
		empresa.setTELEFONE1_DIRIGENTE(rs.getString("TELEFONE1_DIRIGENTE"));
		empresa.setTELEFONE2_DIRIGENTE(rs.getString("TELEFONE2_DIRIGENTE"));
		empresa.setEMAIL_DIRIGENTE(rs.getString("EMAIL_DIRIGENTE"));
		empresa.setIDADE_DIRIGENTE(rs.getInt("IDADE_DIRIGENTE"));
		empresa.setESCOLARIDADE_DIRIGENTE(rs.getString("ESCOLARIDADE_DIRIGENTE"));
		empresa.setCLASSIFIC_ESPECIALISTA(classEspecialistaDAO.buscarPorID(rs.getInt("ID_CLASSIFIC_ESPECIALISTA")));
		
		return empresa;
	}

	@Override
	public TB_EMPRESAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_EMPRESAS WHERE ID_EMPRESA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}

}
