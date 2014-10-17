package tcc.mineradores;

import java.util.LinkedList;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Agrupamento {
	
	public LinkedList<Grupo> agrupar(String numeroDeGrupos, DataSource arquivo) throws Exception{
		Instances instancias = arquivo.getDataSet();
		
		String[] options = new String[9];
		options[0] = "-N";
		options[1] = numeroDeGrupos;
		options[2] = "-A";
		options[3] = "weka.core.EuclideanDistance -R first-last";
		options[4] = "-I";
		options[5] = "500";
		options[6] = "-S";
		options[7] = "10";
		options[8] = "-O";
		
		SimpleKMeans simpleKMeans = new SimpleKMeans();
		simpleKMeans.setOptions(options);
		simpleKMeans.buildClusterer(instancias);

		Instances instanciasCalculadas = simpleKMeans.getClusterCentroids();
		LinkedList<Grupo> listaDeGrupos = new LinkedList<Agrupamento.Grupo>();
		
		Grupo grupo;
		for ( int i = 0; i < instanciasCalculadas.numInstances(); i++ ) {
			grupo = new Grupo();
			for(int j=0; j < instanciasCalculadas.numAttributes(); j++){
				grupo.listaAlternativas.add(instanciasCalculadas.instance(i).stringValue(j));
			}
			grupo.percentualNesteGrupo = (int)(((float)simpleKMeans.getClusterSizes()[i]/(float)instancias.numInstances())*100);
			listaDeGrupos.add(grupo);
		}
		
		return listaDeGrupos;
	}
	
	
	public class Grupo{
		private LinkedList<String> listaAlternativas = new LinkedList<String>();
		private int percentualNesteGrupo;
		
		public LinkedList<String> getListaAlternativas() {
			return listaAlternativas;
		}
		public void setListaAlternativas(LinkedList<String> listaAlternativas) {
			this.listaAlternativas = listaAlternativas;
		}
		
		public int getPercentualNesteGrupo() {
			return percentualNesteGrupo;
		}
		public void setPercentualNesteGrupo(int percentualNoGrupo) {
			this.percentualNesteGrupo = percentualNoGrupo;
		}
	}

}
