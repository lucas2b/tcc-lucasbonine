package tcc.beans;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import tcc.dao.AlternativasDAO;
import tcc.dao.PerguntasDAO;
import tcc.dtos.TB_ALTERNATIVAS;
import tcc.dtos.TB_PERGUNTAS;
import tcc.mineradores.Agrupamento.Grupo;
import tcc.mineradores.Associacao;
import tcc.mineradores.Associacao.Regras;
import tcc.mineradores.Classificacao;
import tcc.mineradores.Agrupamento;
import tcc.mineradores.ColetorDeInstancias;
import weka.core.converters.ConverterUtils.DataSource;

public class ExtrairConhecimentoBEAN{
	
	//---------------------- Atributos do objeto -------------------------
	private int idAlternativa;
	private String alternativasARemover;
	private LinkedList<Regras> regrasDeAssociacao;
	private int gruposDesejados=2;
	private PieChartModel modeloPizza;
	private HtmlGraphicImage imagemClassificacao;
	static LinkedList<Agrupamento.Grupo> grupos;
	

	//------------------------------DAO'S---------------------------------
	private PerguntasDAO 		 perguntasDAO		  = new PerguntasDAO(); 
	private AlternativasDAO		 alternativasDAO 	  = new AlternativasDAO();

	//--------------------- GETTERS AND SETTERS ---------------------------
	public HtmlGraphicImage getImagemClassificacao() {
		return imagemClassificacao;
	}

	public void setImagemClassificacao(HtmlGraphicImage imagemClassificacao) {
		this.imagemClassificacao = imagemClassificacao;
	}

	public LinkedList<ClasseAuxiliar> getValoresExibicao(){
		return ExtrairConhecimentoBEAN.listaClasseAuxiliar;
	}
	
	public LinkedList<Regras> getRegrasDeAssociacao() {
		return regrasDeAssociacao;
	}

	public void setRegrasDeAssociacao(LinkedList<Regras> regrasDeAssociacao) {
		this.regrasDeAssociacao = regrasDeAssociacao;
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
	
	 //--------------------------- CONSTRUTOR ----------------------------------
	 public ExtrairConhecimentoBEAN() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		ExtrairConhecimentoBEAN.grupos = agrupamento.agrupar(String.valueOf(gruposDesejados), arquivo);
		criarModeloPizza(grupos);
	 }

	//----------------- Achadores de caminho para o arquivo --------------------
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	DataSource arquivo;
	
	//------------------------- Montador do arquivo ----------------------------
	ColetorDeInstancias coletorDeInstancias = new ColetorDeInstancias();
	
	//------------------------- Métodos de mineração ---------------------------
	Classificacao 		classificacao = new Classificacao();
	Agrupamento 		agrupamento = new Agrupamento();
	Associacao 			associacao = new Associacao();

	//----------------------- Dispara Escrita do arquivo -----------------------	
	public String escreveArquivo() throws Exception{
			coletorDeInstancias.escreveArquivoArff();
			return "null";
	}
	
	public String realizarClassificacao() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		classificacao.classificar(idAlternativa, arquivo);
		imagemClassificacao.setUrl("/conhecimento/classific.jpg");
		return "refreshClassific";
	}
	
	public String realizarAssociacoes() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		regrasDeAssociacao = associacao.associar(alternativasARemover, arquivo);
		return "refreshAssociacoes"; 
	}
	
	
	public String realizarAgrupamentos() throws Exception{
		arquivo = new DataSource(externalContext.getRealPath("/conhecimento/pesquisa.arff"));
		ExtrairConhecimentoBEAN.grupos = agrupamento.agrupar(String.valueOf(gruposDesejados), arquivo);
		criarModeloPizza(grupos);
		
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
        modeloPizza.setTitle("Agrupamento por perfis. Exibindo: "+gruposDesejados+" perfis.");
        modeloPizza.setLegendPosition("w");
    }
	

	private static LinkedList<ClasseAuxiliar> listaClasseAuxiliar;
	public void itemSelecionado(ItemSelectEvent event) throws ClassNotFoundException, SQLException{
		LinkedList<String> alternativasMineradas = ExtrairConhecimentoBEAN.grupos.get(event.getItemIndex()).getListaAlternativas();
		listaClasseAuxiliar= new LinkedList<ExtrairConhecimentoBEAN.ClasseAuxiliar>();
		
		int i=0;
		for(TB_PERGUNTAS pergunta : perguntasDAO.listarTodos()){
			ClasseAuxiliar classeAuxiliar = new ClasseAuxiliar();
			boolean flag=false;
			
			LinkedList<TB_ALTERNATIVAS> listaAlternativas = new LinkedList<TB_ALTERNATIVAS>();
			for(TB_ALTERNATIVAS alternativa : alternativasDAO.buscarAlternativasPorPergunta(pergunta)){

					//Se a alternativa selecionada for SIM, então é essa que está passando	
					if(alternativasMineradas.get(i).equals("SIM")){
						flag=true;
						classeAuxiliar.setPergunta(pergunta);
						listaAlternativas.add(alternativa);
					}
					i++;
			}
			classeAuxiliar.setAlternativas(listaAlternativas);
			
			//Flag para verificar se existe ao menos uma alternativa SIM e adicionar na lista
			if(flag){
				listaClasseAuxiliar.add(classeAuxiliar);
			}
		}
    }
	
	
	//--------------------- BOTÃO DE PROSSEGUIR VISUALIZAÇÃO ------------------
	public String prosseguirVisualizar(){
		return "visualizarAgrupamentos";
	}
	
	//------------ Classe que monta os atributos para visualização na tela -------
		public class ClasseAuxiliar{
			private TB_PERGUNTAS pergunta;
			public TB_PERGUNTAS getPergunta() {
				return pergunta;
			}
			public void setPergunta(TB_PERGUNTAS pergunta) {
				this.pergunta = pergunta;
			}
			
			private List<TB_ALTERNATIVAS> alternativas;
			public List<TB_ALTERNATIVAS> getAlternativas() {
				return alternativas;
			}
			public void setAlternativas(List<TB_ALTERNATIVAS> alternativas) {
				this.alternativas = alternativas;
			}
			
		
		}

}