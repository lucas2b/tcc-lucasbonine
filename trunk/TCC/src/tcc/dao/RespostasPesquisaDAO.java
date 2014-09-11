package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class RespostasPesquisaDAO extends DatabaseUtil implements InterfaceDAO<TB_RESPOSTAS_PESQUISA>{

	EmpresasDAO empresaDAO = new EmpresasDAO();
	PerguntasDAO perguntasDAO = new PerguntasDAO();
	AlternativasDAO alternativasDAO = new AlternativasDAO();
	
	@Override
	public LinkedList<TB_RESPOSTAS_PESQUISA> listarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<TB_RESPOSTAS_PESQUISA> retorno = new LinkedList<TB_RESPOSTAS_PESQUISA>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_RESPOSTAS_PESQUISA(ID_EMPRESA, ID_PERGUNTA, ID_ALTERNATIVA) VALUES (?,?,?)");
		ps.setInt(1, parametro.getEMPRESA().getID_EMPRESA());
		ps.setInt(2, parametro.getPERGUNTA().getID_PERGUNTA());
		ps.setInt(3, parametro.getALTERNATIVA().getID_ALTERNATIVA());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_RESPOSTAS_PESQUISA SET ID_EMPRESA=?, ID_PERGUNTA=?, ID_ALTERNATIVA=? WHERE ID_RESPOSTA=?");
		ps.setInt(1, parametro.getEMPRESA().getID_EMPRESA());
		ps.setInt(2, parametro.getPERGUNTA().getID_PERGUNTA());
		ps.setInt(3, parametro.getALTERNATIVA().getID_ALTERNATIVA());
		ps.setInt(4, parametro.getID_RESPOSTA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_RESPOSTAS_PESQUISA parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_RESPOSTAS_PESQUISA WHERE ID_RESPOSTA=?");
		ps.setInt(1, parametro.getID_RESPOSTA());
		
		return ps.execute();
	}

	@Override
	public TB_RESPOSTAS_PESQUISA popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_RESPOSTAS_PESQUISA resposta = new TB_RESPOSTAS_PESQUISA();
		resposta.setID_RESPOSTA(rs.getInt("ID_RESPOSTA"));
		resposta.setEMPRESA(empresaDAO.buscarPorID(rs.getInt("ID_EMPRESA")));
		resposta.setPERGUNTA(perguntasDAO.buscarPorID(rs.getInt("ID_PERGUNTA")));
		resposta.setALTERNATIVA(alternativasDAO.buscarPorID(rs.getInt("ID_ALTERNATIVA")));
		
		return resposta;
	}

	@Override
	public TB_RESPOSTAS_PESQUISA buscarPorID(int id)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_RESPOSTA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}
	
	//------------------------------------ MÉTODOS ADICIONAIS --------------------------------------------

	//Excluir as alternativas de certa pergunta onde empresa se manifestou
	public void excluirAlternativaPorChavePerguntaEmpresa(TB_EMPRESAS empresa, TB_PERGUNTAS pergunta)
			throws ClassNotFoundException, SQLException {
		
		LinkedList<TB_RESPOSTAS_PESQUISA> listaChave = new LinkedList<TB_RESPOSTAS_PESQUISA>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_EMPRESA="+empresa.getID_EMPRESA()+" AND ID_PERGUNTA="+pergunta.getID_PERGUNTA());
		
		
		while(rs.next()){
			listaChave.add(popular(rs));
		}
		
		for(TB_RESPOSTAS_PESQUISA deletar : listaChave){
			excluir(deletar);
		}
	}
}
