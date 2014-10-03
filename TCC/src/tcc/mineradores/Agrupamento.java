package tcc.mineradores;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Agrupamento {
	
	public void agrupar() throws Exception{
		
	
		DataSource arquivo = new DataSource("/weather.arff");
		Instances instancias = arquivo.getDataSet();
		
		String[] options = new String[9];
		options[0] = "-N";
		options[1] = "2";
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
		
		System.out.println("Grupo1: " + instanciasCalculadas.instance(0).stringValue(0));
		//System.out.println("Grupo2: " + instanciasCalculadas.instance(1));
		
		
//		for ( int i = 0; i < instanciasCalculadas.numInstances(); i++ ) {
//		    // for each cluster center
//		    Instance inst = instanciasCalculadas.instance( i );
//		    // as you mentioned, you only had 1 attribute
//		    // but you can iterate through the different attributes
//		    
//		    String value = inst.attribute(0).toString();
//		    
//		    System.out.println( "Value for centroid " + i + ": " + value );
//		}
	}

}
