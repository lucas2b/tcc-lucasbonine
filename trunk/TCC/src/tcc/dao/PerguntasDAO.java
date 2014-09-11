package tcc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tcc.bd.DatabaseUtil;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class PerguntasDAO extends DatabaseUtil implements InterfaceDAO<TB_PERGUNTAS>{

	@Override
	public LinkedList<TB_PERGUNTAS> listarTodos()
			throws ClassNotFoundException, SQLException {
		LinkedList<TB_PERGUNTAS> retorno = new LinkedList<TB_PERGUNTAS>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PERGUNTAS");
		
		while(rs.next()){
			retorno.add(popular(rs));
		}
		
		return retorno;
	}

	@Override
	public boolean adicionar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO TB_PERGUNTAS(PERGUNTA_TXT, SELECAO_UNICA) VALUES (?,?)");
		ps.setString(1, parametro.getPERGUNTA_TXT());
		ps.setBoolean(2, parametro.isSELECAO_UNICA());
		
		return ps.execute();
	}

	@Override
	public boolean editar(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE TB_PERGUNTAS SET PERGUNTA_TXT=?, SELECAO_UNICA=? WHERE ID_PERGUNTA=?");
		ps.setString(1, parametro.getPERGUNTA_TXT());
		ps.setInt(2, parametro.getID_PERGUNTA());
		ps.setBoolean(3, parametro.isSELECAO_UNICA());
		
		return ps.execute();
	}

	@Override
	public boolean excluir(TB_PERGUNTAS parametro)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM TB_PERGUNTAS WHERE ID_PERGUNTA=?");
		ps.setInt(1, parametro.getID_PERGUNTA());
		
		return ps.execute();
	}

	@Override
	public TB_PERGUNTAS popular(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		TB_PERGUNTAS pergunta = new TB_PERGUNTAS();
		pergunta.setID_PERGUNTA(rs.getInt("ID_PERGUNTA"));
		pergunta.setPERGUNTA_TXT(rs.getString("PERGUNTA_TXT"));
		pergunta.setSELECAO_UNICA(rs.getBoolean("SELECAO_UNICA"));
		
		return pergunta;
	}

	@Override
	public TB_PERGUNTAS buscarPorID(int id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_PERGUNTAS WHERE ID_PERGUNTA="+id);
		
		if(rs.next())
			return popular(rs);
		else
			return null;
	}
	
	//--------------------------- METODOS ADICIONAIS ----------------------
	public int retornarUltimoID() throws ClassNotFoundException, SQLException{
		int retorno = 0;
		ResultSet rs = getStatement().executeQuery("SELECT ID_PERGUNTA FROM TB_PERGUNTAS");
		
		while(rs.next())
			retorno = rs.getInt("ID_PERGUNTA");
		
		return retorno;
		
	}
	
	public List<TB_PERGUNTAS> listarPerguntasRespondidasPorEmpresa(TB_EMPRESAS empresa) throws ClassNotFoundException, SQLException{
		RespostasPesquisaDAO respostasPesquisaDAO = new RespostasPesquisaDAO();
		LinkedList<TB_RESPOSTAS_PESQUISA> respostasPesquisa = new LinkedList<TB_RESPOSTAS_PESQUISA>();
		
		PerguntasDAO perguntasDAO = new PerguntasDAO();
		LinkedList<TB_PERGUNTAS> listaPerguntasRetorno = new LinkedList<TB_PERGUNTAS>();
		
		ResultSet rs = getStatement().executeQuery("SELECT * FROM TB_RESPOSTAS_PESQUISA WHERE ID_EMPRESA="+empresa.getID_EMPRESA());		
		
		//Retorna uma lista da tabela TB_RESPOSTAS_PESQUISA onde figuram a Empresa do argumento
		while(rs.next()){ 
			respostasPesquisa.add(respostasPesquisaDAO.popular(rs)); 
		}
		
		//Vasculhando para ver quais foram os ID's das perguntas
		Set<Integer> listaComIdPergunta = new HashSet<Integer>(); 
		for(TB_RESPOSTAS_PESQUISA resposta : respostasPesquisa){
			listaComIdPergunta.add(resposta.getPERGUNTA().getID_PERGUNTA());
		}
		
		//Percorrendo a lista com os ID's e montando a lista de perguntas
		for(Integer id : listaComIdPergunta){
			listaPerguntasRetorno.add(perguntasDAO.buscarPorID(id.intValue()));
		}
		
		return listaPerguntasRetorno;
	}

}
