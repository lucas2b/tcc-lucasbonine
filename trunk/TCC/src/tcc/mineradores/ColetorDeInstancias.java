package tcc.mineradores;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tcc.bd.DatabaseUtil;
import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.dtos.TB_RESPOSTAS_PESQUISA;

public class ColetorDeInstancias extends DatabaseUtil{

	private EmpresasDAO 		 empresasDAO 		  = new EmpresasDAO();
	private RespostasPesquisaDAO respostasPesquisaDAO = new RespostasPesquisaDAO();
	private PerguntasDAO 		 perguntasDAO		  = new PerguntasDAO(); 
	
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
}