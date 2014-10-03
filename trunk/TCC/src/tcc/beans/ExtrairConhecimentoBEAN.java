package tcc.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tcc.mineradores.Associacao;
import tcc.mineradores.Classificacao;
import tcc.mineradores.Agrupamento;
import tcc.mineradores.ColetorDeInstancias;
import weka.core.converters.ConverterUtils.DataSource;

public class ExtrairConhecimentoBEAN {
	
	private String caminho;
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	//InputStream input = externalContext.getResourceAsStream("\\conhecimento\\pesquisa.arff");
	
	
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	Classificacao classificacao = new Classificacao();
	Agrupamento agrupamento = new Agrupamento();
	Associacao associacao = new Associacao();

	//Metodo para testas as instancias de mineração	
	public String escreveArquivo() throws Exception{
			coletorDeInstancias.escreveArquivoArff();
			return "null";
	}
	
	public String realizarClassificacao() throws Exception{
		caminho = externalContext.getRealPath("/conhecimento/pesquisa.arff");		
		DataSource arquivo = new DataSource(caminho);
		classificacao.classificar(0, arquivo);
		return "null";
	}

}
