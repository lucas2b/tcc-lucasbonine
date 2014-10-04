package tcc.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tcc.mineradores.Associacao;
import tcc.mineradores.Classificacao;
import tcc.mineradores.Agrupamento;
import tcc.mineradores.ColetorDeInstancias;
import weka.core.converters.ConverterUtils.DataSource;

public class ExtrairConhecimentoBEAN {
	private int idAlternativa;
	private String alternativasARemover;
	
	public String getAlternativasARemover() {
		return alternativasARemover;
	}

	public void setAlternativasARemover(String alternativasARemover) {
		this.alternativasARemover = alternativasARemover;
	}

	public int getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(int idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	//Achadores de caminho para o arquivo
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	DataSource arquivo;
	
	//Montador do arquivo
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	
	//Classses de mineração
	Classificacao 		classificacao = new Classificacao();
	Agrupamento 		agrupamento = new Agrupamento();
	Associacao 			associacao = new Associacao();

	//Metodo para testas as instancias de mineração	
	public String escreveArquivo() throws Exception{
			coletorDeInstancias.escreveArquivoArff();
			
			return "null";
	}
	
	public String realizarClassificacao() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		classificacao.classificar(idAlternativa, arquivo);
		return "refreshClassific";
	}
	
	public String realizarAssociacoes() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		associacao.associar(null, arquivo);
		return "refreshAssociacoes"; 
	}
	
	public String realizarAgrupamentos() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		agrupamento.agrupar(0, arquivo);
		return "refreshAgrupamentos"; 
	}

}
