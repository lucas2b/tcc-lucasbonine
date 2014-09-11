package tcc.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import tcc.dao.AlternativasDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.ProducaoEmpresaDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_PRODUCAO_EMPRESA;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class EditarRespostasBEAN {
	
	
	//-------------------------- DAO's -----------------------------------
	private AlternativasDAO      alternativasDAO     = new AlternativasDAO();
	private ProducaoEmpresaDAO   producaoEmpresaDAO  = new ProducaoEmpresaDAO();
	private EmpresasDAO 	   	 empresasDAO 		 = new EmpresasDAO();
	private RespostasPesquisaDAO respostaPesquisaDAO = new RespostasPesquisaDAO();
	private PerguntasDAO 		 perguntasDAO 		 = new PerguntasDAO();

	
	//------------------------ COMPONENTES DE TELA ---------------------------
	private List<TB_ALTERNATIVAS> 	  listaDeAlternativasDaVez;	  //Traz a lista de alternativas
	private List<TB_PRODUCAO_EMPRESA> listaProducao 			  = new LinkedList<TB_PRODUCAO_EMPRESA>();
	private List<TB_EMPRESAS> 		  listaEmpresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
	private TB_EMPRESAS 			  empresaSelecionada;
	private String					  pergunta;
	private boolean					  selecaoUnica;
	
	//novos componentes
	private List<TB_PERGUNTAS> listaDePerguntasRespondidas;
	private TB_PERGUNTAS perguntaSelecionada;
	
	//------------------------- GETTERS E SETTERS ----------------------------
	
	public TB_PERGUNTAS getPerguntaSelecionada() {
		return perguntaSelecionada;
	}
	public void setPerguntaSelecionada(TB_PERGUNTAS perguntaSelecionada) {
		this.perguntaSelecionada = perguntaSelecionada;
	}
	public List<TB_PERGUNTAS> getListaDePerguntasRespondidas() {
		return listaDePerguntasRespondidas;
	}
	public void setListaDePerguntasRespondidas(
			List<TB_PERGUNTAS> listaDePerguntasRespondidas) {
		this.listaDePerguntasRespondidas = listaDePerguntasRespondidas;
	}
	public boolean isSelecaoUnica() {
		return selecaoUnica;
	}
	public String getPergunta() {
		return pergunta;
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
	
	public EditarRespostasBEAN()throws SQLException, ClassNotFoundException{
		
	}

	
	//--------------------------- EDITAR RESPOSTA ------------------------------------
	

	public String retificarProducao() throws ClassNotFoundException, SQLException{
		listaProducao = producaoEmpresaDAO.retificarProducaoPorEmpresa(empresaSelecionada);
		return "retificarProducao";
	}
	
	public String listarPerguntasRespondidas() throws ClassNotFoundException, SQLException{
		//Perdurando a producao vinda da tela anterior
		for(TB_PRODUCAO_EMPRESA producaoEmpresa : listaProducao){
			producaoEmpresaDAO.editar(producaoEmpresa);
		} 
		
		//Montando a exibição de perguntas já respondidas pela empresa
		listaDePerguntasRespondidas = perguntasDAO.listarPerguntasRespondidasPorEmpresa(empresaSelecionada);
		return "listarPerguntasRespondidas";
	}
	
	//Apresenta tela com alternativas
	public String retificarResposta() throws ClassNotFoundException, SQLException{
		listaDeAlternativasDaVez = alternativasDAO.buscarAlternativasPorPergunta(perguntaSelecionada);
		
		pergunta = listaDeAlternativasDaVez.get(0).getPERGUNTA().getPERGUNTA_TXT();
		selecaoUnica = listaDeAlternativasDaVez.get(0).getPERGUNTA().isSELECAO_UNICA();
		
		return "retificarResposta";
	}
	
	public String finalizarEdicao() throws ClassNotFoundException, SQLException{
		if(excedeuNumeroDeAlternativas()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Mais de uma alternativa selecionada!!", "Mais de uma alternativa selecionada!!"));
			FacesContext.getCurrentInstance().renderResponse();
			return "null";
		}
		else{
			rotinaEditarResposta();
		}
		
		return "voltarATelaDePerguntas";
	}
	
	public String acaoBotaoCancelar() throws ClassNotFoundException, SQLException{
		listaEmpresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
		return null;
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

	public void rotinaEditarResposta() throws ClassNotFoundException, SQLException{
		
		//Fazendo exclusão
		respostaPesquisaDAO.excluirAlternativaPorChavePerguntaEmpresa(empresaSelecionada, perguntaSelecionada);
		
		//Fazendo adição das alternativas escolhidas 
		for(TB_ALTERNATIVAS alternativaRespondida : listaDeAlternativasDaVez){
				
				//Verificando se a resposta foi selecionada
				if(alternativaRespondida.isMARCADA()){
					TB_PERGUNTAS pergunta = alternativaRespondida.getPERGUNTA();
					
					TB_RESPOSTAS_PESQUISA resposta = new TB_RESPOSTAS_PESQUISA();
					resposta.setPERGUNTA(pergunta);
					resposta.setALTERNATIVA(alternativaRespondida);
					resposta.setEMPRESA(empresaSelecionada);
					
					respostaPesquisaDAO.adicionar(resposta);		
			}
		}
		
		
		//Perdurando produção
		
	}
}