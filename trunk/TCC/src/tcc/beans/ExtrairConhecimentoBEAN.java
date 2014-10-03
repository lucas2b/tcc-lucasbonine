package tcc.beans;

import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tcc.mineradores.Associacao;
import tcc.mineradores.Classificacao;
import tcc.mineradores.Agrupamento;
import tcc.mineradores.ColetorDeInstancias;
import weka.core.converters.ConverterUtils.DataSource;

public class ExtrairConhecimentoBEAN {
	
	
	
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	InputStream input = externalContext.getResourceAsStream("/conhecimento/pesquisa.arff");
	
	
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	Classificacao classificacao = new Classificacao();
	Agrupamento agrupamento = new Agrupamento();
	Associacao associacao = new Associacao();

	//Metodo para testas as instancias de mineração	
	public String escreveArquivo() throws Exception{
			coletorDeInstancias.escreveArquivoArff();	
	//		ConverterUtils.DataSource convert = new ConverterUtils.DataSource(input);
	//		testeWeka.classificar(0, convert);
			return "null";
	}
	
	public String realizarClassificacao() throws Exception{
		DataSource arquivo = new DataSource(input);
		classificacao.classificar(0, arquivo);
		return null;
	}

}
