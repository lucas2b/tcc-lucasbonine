package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_ALTERNATIVAS;
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
	
	public LinkedList<TB_RESPOSTAS_PESQUISA> listarRespostasPorPerguntaEEmpresa(TB_PERGUNTAS pergunta, TB_EMPRESAS empresa) throws ClassNotFoundException, SQLException{
		
		LinkedList<TB_RESPOSTAS_PESQUISA> listaChave = new LinkedList<TB_RESPOSTAS_PESQUISA>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_PERGUNTA="+pergunta.getID_PERGUNTA()+" AND ID_EMPRESA="+empresa.getID_EMPRESA());
		
		while(rs.next()){
			listaChave.add(popular(rs));
		}
		
		return listaChave;
	}
	
	public LinkedList<Auxiliar> listarRespostasPorPergunta(TB_PERGUNTAS pergunta) throws ClassNotFoundException, SQLException{
		LinkedList<TB_ALTERNATIVAS> listaAlternativasPorPergunta = (LinkedList<TB_ALTERNATIVAS>) alternativasDAO.buscarAlternativasPorPergunta(pergunta);
		LinkedList<Auxiliar> listaDeRetorno = new LinkedList<RespostasPesquisaDAO.Auxiliar>();
		
		for(TB_ALTERNATIVAS alternativa : listaAlternativasPorPergunta){
			
			ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_ALTERNATIVA="+alternativa.getID_ALTERNATIVA());
			
			int i =0;
			while(rs.next()){
				i++; //contando quantas alternativas foram marcadas
			}
			
			//Testa se houveram alternativas marcadas
			if(i!=0){
				Auxiliar auxiliar = new Auxiliar();
				auxiliar.setAlternativa(alternativa);
				auxiliar.setSomaDestaAlternativa(i);
				listaDeRetorno.add(auxiliar);
			}
		}
		
		return listaDeRetorno;
	}
	
	public class Auxiliar{
		private TB_ALTERNATIVAS alternativa;
		public TB_ALTERNATIVAS getAlternativa() {
			return alternativa;
		}
		public void setAlternativa(TB_ALTERNATIVAS alternativa) {
			this.alternativa = alternativa;
		}
		public int getSomaDestaAlternativa() {
			return somaDestaAlternativa;
		}
		public void setSomaDestaAlternativa(int somaDestaAlternativa) {
			this.somaDestaAlternativa = somaDestaAlternativa;
		}
		private int somaDestaAlternativa;
	}
	
	public boolean verificarSeAlternativaFoiRespondidaPorEmpresa(TB_EMPRESAS empresa, TB_ALTERNATIVAS alternativa) throws ClassNotFoundException, SQLException{
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_EMPRESA="+empresa.getID_EMPRESA()+" AND ID_ALTERNATIVA="+alternativa.getID_ALTERNATIVA());
		
		if(rs.next())
			return true;
		else
			return false;
	}
	
}
