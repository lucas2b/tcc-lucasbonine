package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dao.RespostasPesquisaDAO.Auxiliar;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;
import tcc.mineradores.ColetorDeInstancias;


public class VisualizarRespostasBEAN {
			
	private EmpresasDAO empresasDAO = new EmpresasDAO();
	private PerguntasDAO perguntasDAO = new PerguntasDAO();
	private RespostasPesquisaDAO respostasDAO = new RespostasPesquisaDAO();
	
	//---------------- Componentes de exibição --------------------
	List<ClasseAuxiliar> exibicao = new LinkedList<VisualizarRespostasBEAN.ClasseAuxiliar>();
	private BarChartModel graficoBarras;
	private boolean flagExibir = false;

	//------------- Componentes de seleção na tela ---------------
	private TB_EMPRESAS empresaSelecionada;
	private TB_PERGUNTAS perguntaSelecionada;
	
	//------------------ GETTERS e SETTERS -----------------------

	public TB_PERGUNTAS getPerguntaSelecionada() {
		return perguntaSelecionada;
	}

	public void setPerguntaSelecionada(TB_PERGUNTAS perguntaSelecionada) {
		this.perguntaSelecionada = perguntaSelecionada;
	}

	public boolean isFlagExibir() {
		return flagExibir;
	}

	public void setFlagExibir(boolean flagExibir) {
		this.flagExibir = flagExibir;
	}

	public BarChartModel getGraficoBarras() {
		return graficoBarras;
	}

	public void setGraficoBarras(BarChartModel graficoBarras) {
		this.graficoBarras = graficoBarras;
	}

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
	
	public List<SelectItem> getPerguntas() throws ClassNotFoundException, SQLException{
		List<TB_PERGUNTAS> listaDePerguntas = perguntasDAO.listarTodos();
		
		List<SelectItem> retorno = new LinkedList<SelectItem>();
		for(TB_PERGUNTAS pergunta : listaDePerguntas){
			retorno.add(new SelectItem(pergunta, pergunta.getPERGUNTA_TXT()));
		}
		
		return retorno;
	}
	
	//Monta e exibe o gráfico de barras na tela
	public String listarRespostasPorPergunta() throws ClassNotFoundException, SQLException{
		flagExibir=true; 
		graficoBarras = new BarChartModel();
		 LinkedList<Auxiliar> listaDeAlternativasRespondidas = respostasDAO.listarRespostasPorPergunta(perguntaSelecionada);
		 
		 ChartSeries alternativa = new ChartSeries();
		 for(Auxiliar auxiliar : listaDeAlternativasRespondidas){
		        alternativa.set(auxiliar.getAlternativa().getCOD_ALTERNATIVA(), auxiliar.getSomaDestaAlternativa());
		 }
		 
	       graficoBarras.addSeries(alternativa);
	       
	     return "refreshVisualizarRespostasPorPergunta";
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
	
	//------------------------- Montador do arquivo ----------------------------
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	
	public String escreveArquivo() throws Exception{
		coletorDeInstancias.escreveArquivoArff();
		return "null";
	}
}
