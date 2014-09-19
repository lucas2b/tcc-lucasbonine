package tcc.mineradores;

import java.sql.SQLException;
import java.util.List;

import tcc.bd.DatabaseUtil;
import tcc.dao.AlternativasDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class ColetorDeInstancias extends DatabaseUtil{

	private EmpresasDAO 		 empresasDAO 		  = new EmpresasDAO();
	private RespostasPesquisaDAO respostasPesquisaDAO = new RespostasPesquisaDAO();
	private PerguntasDAO 		 perguntasDAO		  = new PerguntasDAO(); 
	private AlternativasDAO		 alternativasDAO 	  = new AlternativasDAO();
	
	public void imprimeInstancias() throws ClassNotFoundException, SQLException{
		
		List<TB_EMPRESAS> empresasQueResponderam = empresasDAO.listarEmpresasQueResponderamPesquisa();
		
		for(TB_EMPRESAS empresa: empresasQueResponderam){
		
			List<TB_PERGUNTAS> listarTodasPerguntas = perguntasDAO.listarTodos();
			
			System.out.print("Empresa: "+ empresa.getID_EMPRESA() + " ALTERNATIVAS: ");
			for(TB_PERGUNTAS pergunta : listarTodasPerguntas){
			
				List<TB_RESPOSTAS_PESQUISA> respuesta = respostasPesquisaDAO.listarRespostasPorPerguntaEEmpresa(pergunta, empresa);
					
					//Se a lista tem tamanho zero é pq nao respondeu
					if(respuesta.size() == 0){
						System.out.print("NÃO RESPONDEU");
					}else{
						for(TB_RESPOSTAS_PESQUISA resposta : respuesta){
							System.out.print(resposta.getALTERNATIVA().getCOD_ALTERNATIVA());						
					}
				}
				System.out.print("|"); //separa para proxima pergunta			
			}
			System.out.println(); //QUEBRA PARA PROXIMA EMPRESA
		}
	}
	
	public void definicaoAtributos() throws ClassNotFoundException, SQLException{
		
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			
			for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
				System.out.println("@atribute "+pergunta.getID_PERGUNTA()+alternativa.getCOD_ALTERNATIVA()+" {SIM, NAO}");
			}
		}
		
		instancias();
	}
	
	public void instancias() throws ClassNotFoundException, SQLException{
		System.out.println("@data");
		for(TB_EMPRESAS empresa : empresasDAO.listarEmpresasQueResponderamPesquisa()){
			
			System.out.println("EMPRESA: "+empresa.getNOME_EMPRESA());
			for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){	
				
				System.out.println("PERGUNTA: "+pergunta.getID_PERGUNTA());
				for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
					System.out.print("ALTERNATIVA "+alternativa.getCOD_ALTERNATIVA());
					if(respostasPesquisaDAO.verificarSeAlternativaFoiRespondidaPorEmpresa(empresa, alternativa))
					 	System.out.println("{SIM}");
					else
						System.out.println("{NAO}");
				}
			}
			System.out.println();
		}
	}
}