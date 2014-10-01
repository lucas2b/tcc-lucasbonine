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
	
	
	/* Formato ver.1:
	 * "Empresa: 1 ALTERNATIVAS: I|AFIN|ACE|B|E|CGHJLMNRSTU|C|A|A|A|A|A|A|B|CE|C|C|B|DF|AE|F|D|A|B|B|C|H|C|A|CKM|A|B|BEG|CG|BEF|CJK|"
	 */
	public void imprimeInstanciasVersao1() throws ClassNotFoundException, SQLException{
		
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
	}//Fim_versão1
	
	/* Formato ver.2:
	 *  EMPRESA: Shelby Indústria de Conservas LTDA
	 *	PERGUNTA: 37
	 *	ALTERNATIVA A{NAO}
	 *	ALTERNATIVA B{NAO}
	 *	ALTERNATIVA C{NAO}
	 *	ALTERNATIVA D{NAO}
	 *	ALTERNATIVA E{SIM}
	 *	ALTERNATIVA F{SIM}
	 *	ALTERNATIVA G{NAO}
	 *	ALTERNATIVA H{NAO}
	 *	ALTERNATIVA I{NAO}
	 *	ALTERNATIVA J{NAO}
	 *	ALTERNATIVA K{SIM}
	 *	ALTERNATIVA L{NAO}
	 *	ALTERNATIVA M{NAO}
	 *	ALTERNATIVA N{NAO}
	 *	ALTERNATIVA O{NAO}
	 */
	public void imprimeInstanciasVersao2() throws ClassNotFoundException, SQLException{
		
		//Imprimindo a sessão @atributes
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			
			for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
				System.out.println("@atribute "+pergunta.getID_PERGUNTA()+alternativa.getCOD_ALTERNATIVA()+" {SIM, NAO}");
			}
		}
		
		//Imprimindo a sessão @data (instancias)
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
	}//Fim_versão2
	
	//Formato WEKA
	public void imprimeInstanciasVersao3() throws ClassNotFoundException, SQLException{
		
		//Imprimindo relation
		System.out.println("@relation pesquisa");
		System.out.println();
		
		//Imprimindo a sessão @atributes
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			
			for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
				System.out.println("@attribute "+pergunta.getID_PERGUNTA()+alternativa.getCOD_ALTERNATIVA()+" {TRUE, FALSE}");
				//count++;
			}
		}
		
		//System.out.println("Numero TOTAL de alternativas: "+count);
		
		
		//Imprimindo a sessão @data (instancias)
		System.out.println();
		System.out.println("@data");
		
		for(TB_EMPRESAS empresa : empresasDAO.listarEmpresasQueResponderamPesquisa()){
			//count=0;
			
			for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){	
				
				for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
					
					if(respostasPesquisaDAO.verificarSeAlternativaFoiRespondidaPorEmpresa(empresa, alternativa))
					 	System.out.print("TRUE");
					else
						System.out.print("FALSE");

					System.out.print(" ");
					//count++;
				}
				
			}
			System.out.println();
			//System.out.println("Numero de alternativas para empresa de ID "+empresa.getID_EMPRESA() + ": "+count);
		}
	}//Fim_versão2
}