package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class VisualizarRespostasBEAN {
			
	private EmpresasDAO empresasDAO = new EmpresasDAO();
	private PerguntasDAO perguntasDAO = new PerguntasDAO();
	private RespostasPesquisaDAO respostasDAO = new RespostasPesquisaDAO();
	
	List<ClasseAuxiliar> exibicao = new LinkedList<VisualizarRespostasBEAN.ClasseAuxiliar>();
	

	private TB_EMPRESAS empresaSelecionada;
	
	//GETTERS e SETTERS
	public List<ClasseAuxiliar> getExibicao() {
		return exibicao;
	}
	
	public TB_EMPRESAS getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(TB_EMPRESAS empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}


	
	public String atualizar() throws ClassNotFoundException, SQLException{
		exibicao.clear();
		List<TB_PERGUNTAS> listaPerguntas = perguntasDAO.listarPerguntasRespondidasPorEmpresa(empresaSelecionada);
		
		for(TB_PERGUNTAS pergunta : listaPerguntas){
			ClasseAuxiliar temp = new ClasseAuxiliar();
			temp.setPergunta(pergunta);
			temp.setRespostas(respostasDAO.listarRespostasPorPerguntaEEmpresa(pergunta, empresaSelecionada));
			exibicao.add(temp);
		}
		return "refreshVisualizarRespostas";
	}
	
	//Métodos
	public List<SelectItem> getEmpresasQueResponderam() throws ClassNotFoundException, SQLException{
		List<TB_EMPRESAS> empresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
		List<SelectItem> retorno = new LinkedList<SelectItem>();
		
		for(TB_EMPRESAS empresa : empresasQueResponderam){
			retorno.add(new SelectItem(empresa, empresa.getNOME_EMPRESA()));
		}
		
		return retorno;
	}
	
	public class ClasseAuxiliar{
		private TB_PERGUNTAS pergunta;
		public TB_PERGUNTAS getPergunta() {
			return pergunta;
		}
		public void setPergunta(TB_PERGUNTAS pergunta) {
			this.pergunta = pergunta;
		}
		
		private List<TB_RESPOSTAS_PESQUISA> respostas;
		public List<TB_RESPOSTAS_PESQUISA> getRespostas() {
			return respostas;
		}
		public void setRespostas(List<TB_RESPOSTAS_PESQUISA> respostas) {
			this.respostas = respostas;
		}
	
	}

}
