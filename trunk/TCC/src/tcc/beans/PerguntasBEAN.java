package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tcc.dao.AlternativasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_PERGUNTAS;

public class PerguntasBEAN {
	
	//-------------------------------- DAO's --------------------------------------
	PerguntasDAO perguntasDAO = new PerguntasDAO();
	AlternativasDAO alternativasDAO = new AlternativasDAO();
	
	//--------------------- Elementos de tela usados na adição ---------------------
	private TB_PERGUNTAS perguntaSelecionada;
	private AuxPerguntas auxPerguntas = new AuxPerguntas();
	private int numeroDePerguntas;
	private int contadorPerguntaAtual = 0;
	
	//--------------------- Elementos de tela usados na edição --------------------
	private List<TB_ALTERNATIVAS> listaDeAlternativas;
	private List<AuxPerguntas> listaPerguntas = new LinkedList<PerguntasBEAN.AuxPerguntas>();
	
	
	//--------------------------- GETTERS AND SETTERS ---------------------------
	public List<TB_ALTERNATIVAS> getListaDeAlternativas() {
		return listaDeAlternativas;
	}
	public void setListaDeAlternativas(List<TB_ALTERNATIVAS> listaDeAlternativas) {
		this.listaDeAlternativas = listaDeAlternativas;
	}
	public int getContadorPerguntaAtual() {
		return contadorPerguntaAtual;
	}
	public AuxPerguntas getAuxPerguntas() {
		return auxPerguntas;
	}

	public void setAuxPerguntas(AuxPerguntas auxPerguntas) {
		this.auxPerguntas = auxPerguntas;
	}	

	public int getNumeroDePerguntas() {
		return numeroDePerguntas;
	}

	public void setNumeroDePerguntas(int numeroDePerguntas) {
		this.numeroDePerguntas = numeroDePerguntas;
	}
	
	public TB_PERGUNTAS getPerguntaSelecionada() {
		return perguntaSelecionada;
	}

	public void setPerguntaSelecionada(TB_PERGUNTAS perguntaSelecionada) {
		this.perguntaSelecionada = perguntaSelecionada;
	}
	
	public List<TB_PERGUNTAS> getPerguntas() throws ClassNotFoundException, SQLException{
		return perguntasDAO.listarTodos();
	}
	
	
	//------------------------------- INSERIR PERGUNTA ----------------------------
	public String acaoBotaoProximo1(){
		contadorPerguntaAtual++;
		return "avancaPasso2Perguntas";
	}
	
	public String acaoBotaoCancelar1(){
		contadorPerguntaAtual = 0;
		return "listarPerguntas";
	}
	
	public String acaoBotaoCancelar2(){
		contadorPerguntaAtual = 0;
		listaPerguntas = new LinkedList<PerguntasBEAN.AuxPerguntas>();
		auxPerguntas = new AuxPerguntas();
		return "listarPerguntas";
	}
	
	public String acaoBotaoProximo2() throws ClassNotFoundException, SQLException{
		
		if(numeroDePerguntas > 0){
			listaPerguntas.add(auxPerguntas);
			auxPerguntas = new AuxPerguntas(); //limpa para nova leva
			numeroDePerguntas--;
			contadorPerguntaAtual++;
		}	

		if(numeroDePerguntas > 0){			
			return "refreshPasso2";
		}
		else{
			rotinaInserirPergunta();
			auxPerguntas = new AuxPerguntas(); //limpa para nova leva
			contadorPerguntaAtual = 0;
			return "listarPerguntas";
		}
	}
	

	
	//-------------------------------- EDITAR PERGUNTA ------------------------------
	public String startEditarPergunta() throws ClassNotFoundException, SQLException{
		listaDeAlternativas = alternativasDAO.buscarAlternativasPorPergunta(perguntaSelecionada);
		return "editarPergunta";
	}
	
	public String finishEditarPergunta() throws ClassNotFoundException, SQLException{
		for(TB_ALTERNATIVAS alternativa : listaDeAlternativas){
			alternativasDAO.editar(alternativa);
		}
		return "listarPerguntas";
	}
	
	public String cancelarEdicaoPergunta(){
		listaDeAlternativas = null;
		return "listarPerguntas";
	}
	
	//------------------------------- ROTINAS E APOIOS -------------------------------------
	
	private void rotinaInserirPergunta() throws ClassNotFoundException, SQLException {
	
		for(AuxPerguntas pergunta :  listaPerguntas){
			//Inserindo a pergunta
			perguntasDAO.adicionar(pergunta.getPergunta());
			
			//CONTROLE DE ALTERNATIVAS ALFABETICAS
			int i=0;
			
			//Inserindo as alternativas
			for(String itemAlternativa :  pergunta.getAlternativa()){
				
				//Se a alternativa veio vazia, pular essa iteração
				if(itemAlternativa.equals(""))
					continue;
				
				i++;
				
				TB_ALTERNATIVAS alternativa = new TB_ALTERNATIVAS();
				alternativa.setALTERNATIVA_TXT(itemAlternativa);
				
				switch (i) {
				case 1:
					alternativa.setCOD_ALTERNATIVA("A");
					break;
					
				case 2:
					alternativa.setCOD_ALTERNATIVA("B");
					break;
					
				case 3:
					alternativa.setCOD_ALTERNATIVA("C");
					break;
					
				case 4:
					alternativa.setCOD_ALTERNATIVA("D");
					break;
					
				case 5:
					alternativa.setCOD_ALTERNATIVA("E");
					break;
					
				case 6:
					alternativa.setCOD_ALTERNATIVA("F");
					break;
					
				case 7:
					alternativa.setCOD_ALTERNATIVA("G");
					break;
					
				case 8:
					alternativa.setCOD_ALTERNATIVA("H");
					break;
					
				case 9:
					alternativa.setCOD_ALTERNATIVA("I");
					break;
					
				case 10:
					alternativa.setCOD_ALTERNATIVA("J");
					break;
					
				case 11:
					alternativa.setCOD_ALTERNATIVA("K");
					break;
					
				case 12:
					alternativa.setCOD_ALTERNATIVA("L");
					break;
					
				case 13:
					alternativa.setCOD_ALTERNATIVA("M");
					break;
					
				case 14:
					alternativa.setCOD_ALTERNATIVA("N");
					break;
					
				case 15:
					alternativa.setCOD_ALTERNATIVA("O");
					break;
					
				case 16:
					alternativa.setCOD_ALTERNATIVA("P");
					break;
					
				case 17:
					alternativa.setCOD_ALTERNATIVA("Q");
					break;
					
				case 18:
					alternativa.setCOD_ALTERNATIVA("R");
					break;
					
				case 19:
					alternativa.setCOD_ALTERNATIVA("S");
					break;
					
				case 20:
					alternativa.setCOD_ALTERNATIVA("T");
					break;
					
				case 21:
					alternativa.setCOD_ALTERNATIVA("U");
					break;
					
				case 22:
					alternativa.setCOD_ALTERNATIVA("V");
					break;
					
				case 23:
					alternativa.setCOD_ALTERNATIVA("X");
					break;
					
				case 24:
					alternativa.setCOD_ALTERNATIVA("Z");
					break;

				default:
					break;
				}
				
				alternativa.setPERGUNTA(perguntasDAO.buscarPorID(perguntasDAO.retornarUltimoID()));
				alternativasDAO.adicionar(alternativa);
			}//Fim do For interno
		}//Fim do For externo
	}
	
	//--------------------------------------- CLASSE AUXILIAR ----------------------------------
	public class AuxPerguntas{
		private String[] alternativa = new String[24]; //de tamanho 24, indice variando de 0 a 23
		private TB_PERGUNTAS pergunta = new TB_PERGUNTAS();
	
		public TB_PERGUNTAS getPergunta() {
			return pergunta;
		}
	
		public void setPergunta(TB_PERGUNTAS pergunta) {
			this.pergunta = pergunta;
		}
	
		public String[] getAlternativa() {
			return alternativa;
		}
	
		public void setAlternativa(String[] alternativa) {
			this.alternativa = alternativa;
		}
		
	}

}
