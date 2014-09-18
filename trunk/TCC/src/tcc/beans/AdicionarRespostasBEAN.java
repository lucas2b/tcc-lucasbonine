package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import tcc.dao.AlternativasDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class AdicionarRespostasBEAN {
	
	
	//-------------------------- DAO's -----------------------------------
	private AlternativasDAO      alternativasDAO     = new AlternativasDAO();
	private EmpresasDAO 	   	 empresasDAO 		 = new EmpresasDAO();
	private RespostasPesquisaDAO respostaPesquisaDAO = new RespostasPesquisaDAO();
	
	
	//------------------------ Listas auxiliares ---------------------------
	private List<List<TB_ALTERNATIVAS>> listaDeTodasAlternativas; //recebe todas alternativas cadastradas em TB_ALTERNATIVAS
	private List<List<TB_ALTERNATIVAS>> listaDeRespostas = new LinkedList<List<TB_ALTERNATIVAS>>();
	
	//------------------------- Flags de controle ----------------------------
	private boolean flagPrimeiraVez = true;
	private int iterador;
	
	//------------------------ COMPONENTES DE TELA ---------------------------
	private List<TB_ALTERNATIVAS> 	  listaDeAlternativasDaVez;	  //Traz a lista de alternativas
	private List<TB_EMPRESAS>  		  listaEmpresasNaoResponderam = empresasDAO.listarEmpresasQueNaoResponderamPesquisa();
	private TB_EMPRESAS 			  empresaSelecionada;
	private String					  pergunta;
	private boolean					  selecaoUnica;
	
	//------------------------- GETTERS E SETTERS ----------------------------
	
	public boolean isSelecaoUnica() {
		return selecaoUnica;
	}
	public String getPergunta() {
		return pergunta;
	}
	public List<TB_EMPRESAS> getListaEmpresasNaoResponderam() {
		return listaEmpresasNaoResponderam;
	}

	public TB_EMPRESAS getEmpresaSelecionada() {
		return empresaSelecionada;
	}
	public void setEmpresaSelecionada(TB_EMPRESAS empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	public List<TB_ALTERNATIVAS> getListaDeAlternativasDaVez() {
		return listaDeAlternativasDaVez;
	}
	public void setListaDeAlternativasDaVez(
			List<TB_ALTERNATIVAS> listaDeAlternativasDaVez) {
		this.listaDeAlternativasDaVez = listaDeAlternativasDaVez;
	}
	
	public AdicionarRespostasBEAN() throws ClassNotFoundException, SQLException{
	}
	
	//--------------------------- ADICIONAR RESPOSTA ------------------------------------
		
	public String acaoBotaoProximaPergunta() throws ClassNotFoundException, SQLException{
		
		//1 - Chegando da pergunta de produ��o (primeira transi��o para tela de alternativas)
		if(flagPrimeiraVez == true){
			
			//Trazendo novamente todas alternativas existentes � tela
			listaDeTodasAlternativas = alternativasDAO.retonarPacotesDeAlternativas();
			
			//Prepara para apresentar alternativas pela primeira vez
			listaDeAlternativasDaVez = listaDeTodasAlternativas.get(0);
			
			//Para elementos de exibi��o na tela
			pergunta = listaDeAlternativasDaVez.get(0).getPERGUNTA().getPERGUNTA_TXT();
			selecaoUnica = listaDeAlternativasDaVez.get(0).getPERGUNTA().isSELECAO_UNICA();
			
			//Desativa a entrada no If
			flagPrimeiraVez = false;
			
			//proxima
			iterador = 1;
			
			//Apresenta a tela de alternativas
			return "avancaTelaAlternativas";
		}//Fim de 1
		
				
		//2 - Na tela de alternativas, verificando se a resposta recebida � v�lida
		if(excedeuNumeroDeAlternativas()){
			
			//Apresenta mensagens de erro na tela e permanece na mesma p�gina
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Mais de uma alternativa selecionada!!", "Mais de uma alternativa selecionada!!"));
			FacesContext.getCurrentInstance().renderResponse();
			return "null";
		}else{
			
			//Se a resposta � v�lida armazena em uma lista tempor�ria
			listaDeRespostas.add(listaDeAlternativasDaVez);					
		}
		
		
		//3 - Apresenta��o das alternativas seguintes
		if(iterador < listaDeTodasAlternativas.size()){	
			
			//Apresenta novas alternativas na tela
			listaDeAlternativasDaVez = listaDeTodasAlternativas.get(iterador);
			
			//Para elementos de exibi��o na tela
			pergunta = listaDeAlternativasDaVez.get(0).getPERGUNTA().getPERGUNTA_TXT();
			selecaoUnica = listaDeAlternativasDaVez.get(0).getPERGUNTA().isSELECAO_UNICA();
			
			//Incrementa o iterador para recuperar nova leva de alternativas
			iterador++;
			
			return "apresentaTelaAlternativas";
			
		}else{
			
			//Perdurar produ��o e alternativas no BD
			rotinaAdicionarResposta();
			
			//Limpa lista de empresas que j� responderam
			listaEmpresasNaoResponderam = empresasDAO.listarEmpresasQueNaoResponderamPesquisa();
			
			//Finaliza com sucesso a adi��o da pesquisa
			return "avancaPesquisaConcluida";
		}//Fim de 3
	}
	
	public String acaoBotaoCancelar() throws ClassNotFoundException, SQLException{
		listaDeRespostas.clear();
		flagPrimeiraVez = true;
		iterador = 1;
		return "listarEmpresasNaoResponderam";
	}
	
	public String atualizarListagem() throws ClassNotFoundException, SQLException{
		listaEmpresasNaoResponderam = empresasDAO.listarEmpresasQueNaoResponderamPesquisa();
		return "relistarEmpresas1";
	}
	
	//----------------------------- ROTINAS DE AUTOMA��O ------------------------------
	
	public boolean excedeuNumeroDeAlternativas(){
		
		//Se excedeu o n�mero de respostas permitidas (no caso uma), retorna TRUE
		boolean selecaoUnica = listaDeAlternativasDaVez.get(0).getPERGUNTA().isSELECAO_UNICA();		
		if(selecaoUnica){
			int i = 0;
			for(TB_ALTERNATIVAS item : listaDeAlternativasDaVez){
				if(item.isMARCADA()){
					i++;
					if(i > 1){
						System.out.println("Excedeu sim!!");
						return true;
					}	
				}
			}
		}
		return false;
	}

	public void rotinaAdicionarResposta() throws ClassNotFoundException, SQLException{
		
		//Perdurando as repostas no BD 
		for(List<TB_ALTERNATIVAS> item : listaDeRespostas){
			for(TB_ALTERNATIVAS alternativaRespondida : item){
				
				//Verificando se a resposta foi selecionada
				if(alternativaRespondida.isMARCADA()){
					TB_PERGUNTAS pergunta = alternativaRespondida.getPERGUNTA();
					
					TB_RESPOSTAS_PESQUISA resposta = new TB_RESPOSTAS_PESQUISA();
					resposta.setALTERNATIVA(alternativaRespondida);
					resposta.setEMPRESA(empresaSelecionada);
					resposta.setPERGUNTA(pergunta);
					
					respostaPesquisaDAO.adicionar(resposta);		
				}
			}
		}
		
		//limpando a lista de respostas
		listaDeRespostas.clear();
				
		//Setando empresa que j� respondeu
		empresasDAO.setarFlagDePesquisaRespondida(empresaSelecionada);
	}
}