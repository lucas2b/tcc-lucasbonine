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
	
	public int getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(int idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
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
		DataSource arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		classificacao.classificar(idAlternativa, arquivo);
		return "refreshClassific";
	}

}
