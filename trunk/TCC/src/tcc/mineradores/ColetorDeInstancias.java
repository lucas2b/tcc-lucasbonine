package tcc.mineradores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import tcc.bd.DatabaseUtil;
import tcc.dao.AlternativasDAO;
import tcc.dao.EmpresasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dao.RespostasPesquisaDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_EMPRESAS;
import tcc.dtos.TB_PERGUNTAS;

public class ColetorDeInstancias extends DatabaseUtil{

	private EmpresasDAO 		 empresasDAO 		  = new EmpresasDAO();
	private RespostasPesquisaDAO respostasPesquisaDAO = new RespostasPesquisaDAO();
	private PerguntasDAO 		 perguntasDAO		  = new PerguntasDAO(); 
	private AlternativasDAO		 alternativasDAO 	  = new AlternativasDAO();
	
	//Formato WEKA
	public boolean escreveArquivoArff() throws ClassNotFoundException, SQLException, IOException{
		
		String caminhoDeEscrita = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/conhecimento");
		
		//Criando arquivo no caminho
		File arquivo = new File(caminhoDeEscrita+"\\pesquisa.arff");
		arquivo.createNewFile();
		
		//Escritor do arquivo
		FileWriter escritorArquivo = new FileWriter( arquivo );
		BufferedWriter bufferDeEscrita = new BufferedWriter(escritorArquivo);
		
		//Escrevendo @relation
		bufferDeEscrita.write("@relation pesquisa");
		bufferDeEscrita.newLine();
		
		//Escrevendo os @attributes para cada alternativa
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			
			for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
				bufferDeEscrita.write("@attribute "+pergunta.getID_PERGUNTA()+alternativa.getCOD_ALTERNATIVA()+" {SIM, NAO}");
				bufferDeEscrita.newLine();
			}
		}
		
		//Escrevendo @data
		bufferDeEscrita.newLine();
		bufferDeEscrita.write("@data");
		bufferDeEscrita.newLine();
		
		//Escrevendo as instâncias
		for(TB_EMPRESAS empresa : empresasDAO.listarEmpresasQueResponderamPesquisa()){
			
			for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){	
				
				for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){
					
					if(respostasPesquisaDAO.verificarSeAlternativaFoiRespondidaPorEmpresa(empresa, alternativa))
						bufferDeEscrita.write("SIM");
					else
						bufferDeEscrita.write("NAO");

				bufferDeEscrita.write(" ");
				}
			}
			bufferDeEscrita.newLine();
		}
		
		bufferDeEscrita.close();
		escritorArquivo.close();
		
		return true;
	}//Fim_fomatoWEKA
	
	//--------------- VERSÃO DEBUG -----------------------
	
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
	public void debugArquivo() throws ClassNotFoundException, SQLException{
		
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
}