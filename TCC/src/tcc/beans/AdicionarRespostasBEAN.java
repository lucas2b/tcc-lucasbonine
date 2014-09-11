package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import tcc.dao.AlternativasDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.ProducaoEmpresaDAO;
import tcc.dao.ProdutosDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_PRODUCAO_EMPRESA;
import tcc.dtos.TB_PRODUTOS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class AdicionarRespostasBEAN {
	
	
	//-------------------------- DAO's -----------------------------------
	private AlternativasDAO      alternativasDAO     = new AlternativasDAO();
	private ProdutosDAO 	     produtosDAO         = new ProdutosDAO();
	private ProducaoEmpresaDAO   producaoEmpresaDAO  = new ProducaoEmpresaDAO();
	private EmpresasDAO 	   	 empresasDAO 		 = new EmpresasDAO();
	private RespostasPesquisaDAO respostaPesquisaDAO = new RespostasPesquisaDAO();
	
	
	//------------------------ Listas auxiliares ---------------------------
	private List<List<TB_ALTERNATIVAS>> listaDeTodasAlternativas; //recebe todas alternativas cadastradas em TB_ALTERNATIVAS
	private List<List<TB_ALTERNATIVAS>> listaDeRespostas = new LinkedList<List<TB_ALTERNATIVAS>>();
	
	//------------------------- Flags de controle ----------------------------
	private boolean flagPerguntaProducao = false;
	private int iterador;
	
	//------------------------ COMPONENTES DE TELA ---------------------------
	private List<TB_ALTERNATIVAS> 	  listaDeAlternativasDaVez;	  //Traz a lista de alternativas
	private List<TB_PRODUCAO_EMPRESA> listaProducao 			  = new LinkedList<TB_PRODUCAO_EMPRESA>(); //acumula produção digitada na tela
	private List<TB_EMPRESAS>  		  listaEmpresasNaoResponderam = empresasDAO.listarEmpresasQueNaoResponderamPesquisa();
	private List<TB_EMPRESAS> 		  listaEmpresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
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
	public List<TB_EMPRESAS> getListaEmpresasQueResponderam() {
		return listaEmpresasQueResponderam;
	}
	public List<TB_PRODUCAO_EMPRESA> getListaProducao() {
		return listaProducao;
	}
	public void setListaProducao(List<TB_PRODUCAO_EMPRESA> listaProducao) {
		this.listaProducao = listaProducao;
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
	
	//0 - Apresentação da pergunta sobre produção (vindo da listagem de empresas)
	public String perguntaSobreProducao() throws ClassNotFoundException, SQLException{

		//Trazendo novamente todas alternativas existentes à tela
		listaDeTodasAlternativas = alternativasDAO.retonarPacotesDeAlternativas();
		
		//Limpando a lista acumuladora de produção
		listaProducao.clear();
		
		//Preparando a TB_PRODUCAO_EMPRESA com todos produtos cadastrados
		for(TB_PRODUTOS produto : produtosDAO.listarTodos()){
			TB_PRODUCAO_EMPRESA producaoEmpresa = new TB_PRODUCAO_EMPRESA();
			producaoEmpresa.setEMPRESA(empresaSelecionada);
			producaoEmpresa.setPRODUTO(produto);
			listaProducao.add(producaoEmpresa); //listaProducao é exibida na tela, basta entrar apenas o peso
		}
		
		flagPerguntaProducao = true;
		iterador = 1;
		return "avancaPerguntaProducao";
	}
	
	public String acaoBotaoProximaPergunta() throws ClassNotFoundException, SQLException{
		
		//1 - Chegando da pergunta de produção (primeira transição para tela de alternativas)
		if(flagPerguntaProducao == true){
			
			//Prepara para apresentar alternativas pela primeira vez
			listaDeAlternativasDaVez = listaDeTodasAlternativas.get(0);
			
			//Para elementos de exibição na tela
			pergunta = listaDeAlternativasDaVez.get(0).getPERGUNTA().getPERGUNTA_TXT();
			selecaoUnica = listaDeAlternativasDaVez.get(0).getPERGUNTA().isSELECAO_UNICA();
			
			//Desativa a entrada no If
			flagPerguntaProducao = false;
			
			//Apresenta a tela de alternativas
			return "avancaTelaAlternativas";
		}//Fim de 1
		
				
		//2 - Na tela de alternativas, verificando se a resposta recebida é válida
		if(excedeuNumeroDeAlternativas()){
			
			//Apresenta mensagens de erro na tela e permanece na mesma página
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Mais de uma alternativa selecionada!!", "Mais de uma alternativa selecionada!!"));
			FacesContext.getCurrentInstance().renderResponse();
			return "null";
		}else{
			
			//Se a resposta é válida armazena em uma lista temporária
			listaDeRespostas.add(listaDeAlternativasDaVez);					
		}
		
		
		//3 - Apresentação das alternativas seguintes
		if(iterador < listaDeTodasAlternativas.size()){	
			
			//Apresenta novas alternativas na tela
			listaDeAlternativasDaVez = listaDeTodasAlternativas.get(iterador);
			
			//Para elementos de exibição na tela
			pergunta = listaDeAlternativasDaVez.get(iterador).getPERGUNTA().getPERGUNTA_TXT();
			selecaoUnica = listaDeAlternativasDaVez.get(iterador).getPERGUNTA().isSELECAO_UNICA();
			
			//Incrementa o iterador para recuperar nova leva de alternativas
			iterador++;
			
			return "avancaTelaAlternativas";
			
		}else{
			
			//Perdurar produção e alternativas no BD
			rotinaAdicionarResposta();
			
			//Limpa lista de empresas que já responderam
			listaEmpresasNaoResponderam = empresasDAO.listarEmpresasQueNaoResponderamPesquisa();
			
			//Finaliza com sucesso a adição da pesquisa
			return "avancaPesquisaConcluida";
		}//Fim de 3
	}
	
	public String acaoBotaoCancelar() throws ClassNotFoundException, SQLException{
		return "listarEmpresasNaoResponderam";
	}
	
	//----------------------------- ROTINAS DE AUTOMAÇÃO ------------------------------
	
	public boolean excedeuNumeroDeAlternativas(){
		
		//Se excedeu o número de respostas permitidas (no caso uma), retorna TRUE
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
		
		
		//Perdurando produção
		for(TB_PRODUCAO_EMPRESA producaoEmpresa : listaProducao){
			producaoEmpresaDAO.adicionar(producaoEmpresa);
		} 
		
		//Setando empresa que já respondeu
		empresasDAO.setarFlagDePesquisaRespondida(empresaSelecionada);
	}
}