package tcc.beans;

import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tcc.mineradores.Association;
import tcc.mineradores.Classification;
import tcc.mineradores.Clustering;
import tcc.mineradores.ColetorDeInstancias;

public class ExtrairConhecimentoBEAN {
	
	
	
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	InputStream input = externalContext.getResourceAsStream("/conhecimento/pesquisa.arff");
	
	
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	Classification testeWeka = new Classification();
	Clustering clustering = new Clustering();
	Association association = new Association();

	//Metodo para testas as instancias de mineração	
	public String escreveArquivo() throws Exception{
			coletorDeInstancias.escreveArquivoArff();	
	//		ConverterUtils.DataSource convert = new ConverterUtils.DataSource(input);
	//		testeWeka.classificar(0, convert);
			return "null";
	}

}
