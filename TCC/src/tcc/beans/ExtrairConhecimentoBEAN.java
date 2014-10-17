package tcc.beans;

import java.io.Serializable;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.sun.org.apache.xml.internal.security.Init;

import tcc.mineradores.Agrupamento.Grupo;
import tcc.mineradores.Associacao;
import tcc.mineradores.Classificacao;
import tcc.mineradores.Agrupamento;
import tcc.mineradores.ColetorDeInstancias;
import weka.core.converters.ConverterUtils.DataSource;

public class ExtrairConhecimentoBEAN{
	private int idAlternativa;
	private String alternativasARemover;
	private String regrasDeAssociacao;
	private int gruposDesejados;
	private PieChartModel modeloPizza;
	private boolean renderPizza = false;
	
	public boolean isRenderPizza() {
		return renderPizza;
	}

	public void setRenderPizza(boolean renderPizza) {
		this.renderPizza = renderPizza;
	}

	public PieChartModel getModeloPizza() {
		return modeloPizza;
	}

	public void setModeloPizza(PieChartModel modeloPizza) {
		this.modeloPizza = modeloPizza;
	}

	public int getGruposDesejados() {
		return gruposDesejados;
	}

	public void setGruposDesejados(int gruposDesejados) {
		this.gruposDesejados = gruposDesejados;
	}

	public String getRegrasDeAssociacao() {
		return regrasDeAssociacao;
	}

	public void setRegrasDeAssociacao(String regrasDeAssociacao) {
		this.regrasDeAssociacao = regrasDeAssociacao;
	}

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
		regrasDeAssociacao = associacao.associar(alternativasARemover, arquivo);
		return "refreshAssociacoes"; 
	}
	
	public String realizarAgrupamentos() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		LinkedList<Agrupamento.Grupo> grupos = agrupamento.agrupar(String.valueOf(gruposDesejados), arquivo);
		renderPizza = true;
		criarModeloPizza(grupos);
		
//		for(Grupo grupo : grupos){
//			System.out.println("Percentual deste grupo: " + grupo.getPercentualNesteGrupo() + "%");
//			
//			System.out.println("Alternativas que compoem este perfil:");
//			for(String alternativa : grupo.getListaAlternativas()){
//				System.out.print(alternativa+" ");
//			}
//			System.out.println();
//		}
		return "refreshAgrupamentos"; 
	}
	
	private void criarModeloPizza(LinkedList<Grupo> grupos) {		
		modeloPizza = new PieChartModel();
		
		int i=1;
		for(Grupo grupo : grupos){
			String numeroGrupo = "Perfil "+i;
			modeloPizza.set(numeroGrupo, grupo.getPercentualNesteGrupo());
			i++;
		}
		
		modeloPizza.setShowDataLabels(true);
        modeloPizza.setTitle("Agrupamentos de perfis");
        modeloPizza.setLegendPosition("w");
    }
	
	public void itemSelecionado(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
