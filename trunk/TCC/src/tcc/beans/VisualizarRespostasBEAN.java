package tcc.beans;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;
import tcc.mineradores.Association;
import tcc.mineradores.Classification;
import tcc.mineradores.Clustering;
import weka.core.converters.ConverterUtils;

public class VisualizarRespostasBEAN {
	
	
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	InputStream input = externalContext.getResourceAsStream("/WEB-INF/weka/weather.arff");
	
	
	//ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	Classification testeWeka = new Classification();
	Clustering clustering = new Clustering();
	Association association = new Association();
	//Metodo para testas as instancias de mineração
		public String imprimeInstancias() throws Exception{
			
			ConverterUtils.DataSource convert = new ConverterUtils.DataSource(input);
			testeWeka.classificar(0, convert);
			return "null";
		}
			
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

	//--------------------------- MÉTODOS ---------------------------------
	
	//Ação do botão que monta a table com as repostas de acordo com a empresaSelecionada
	public String atualizar() throws ClassNotFoundException, SQLException{
		exibicao.clear();
		
		//Lista todas as perguntas
		List<TB_PERGUNTAS> listaPerguntas = perguntasDAO.listarTodos();
		
		//Varre pergunta por pergunta
		for(TB_PERGUNTAS pergunta : listaPerguntas){
			ClasseAuxiliar temp = new ClasseAuxiliar();
			temp.setPergunta(pergunta);
			
			//Caso a empresa não tenha respondido a pergunta, monta uma instância falsa da ClasseAuxiliar com alternativa "NAO RESPONDEU"
			if(respostasDAO.listarRespostasPorPerguntaEEmpresa(pergunta, empresaSelecionada).size() == 0){
				TB_RESPOSTAS_PESQUISA respostaFalsa = new TB_RESPOSTAS_PESQUISA();
				
				TB_ALTERNATIVAS alternativaFalsa = new TB_ALTERNATIVAS();
				alternativaFalsa.setALTERNATIVA_TXT("NÃO RESPONDEU");
				respostaFalsa.setALTERNATIVA(alternativaFalsa);
				
				LinkedList<TB_RESPOSTAS_PESQUISA> listaDeRespostasFalsas = new LinkedList<TB_RESPOSTAS_PESQUISA>();
				listaDeRespostasFalsas.add(respostaFalsa);
				
				temp.setRespostas(listaDeRespostasFalsas);
				exibicao.add(temp);
			}else{
				temp.setRespostas(respostasDAO.listarRespostasPorPerguntaEEmpresa(pergunta, empresaSelecionada));
				exibicao.add(temp);				
			}
		}
		return "refreshVisualizarRespostas";
	}
	
	//Combo das empresas que responderam a pesquisa
	public List<SelectItem> getEmpresasQueResponderam() throws ClassNotFoundException, SQLException{
		List<TB_EMPRESAS> empresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
		List<SelectItem> retorno = new LinkedList<SelectItem>();
		
		for(TB_EMPRESAS empresa : empresasQueResponderam){
			retorno.add(new SelectItem(empresa, empresa.getNOME_EMPRESA()));
		}
		
		return retorno;
	}
	

	//Classe que monta os atributos para visualização na tela
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
