package tcc.mineradores;

import java.util.List;

import weka.associations.FPGrowth;
import weka.associations.FPGrowth.AssociationRule;
import weka.associations.FilteredAssociator;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

public class Associacao {
	
	public String associar(String remover, DataSource arquivo) throws Exception{
		Instances instancias = arquivo.getDataSet();
		
		String[] options = new String[16];
		options[0] = "-P";
		options[1] = "1";
		options[2] = "-I";
		options[3] = "-1";
		options[4] = "-N";
		options[5] = "10";
		options[6] = "-T";
		options[7] = "0";
		options[8] = "-C";
		options[9] = "0.9";
		options[10] = "-D";
		options[11] = "0.05";
		options[12] = "-U";
		options[13] = "1.0";
		options[14] = "-M";
		options[15] = "0.1";
		
		FPGrowth fpGrowth = new FPGrowth();
		fpGrowth.setOptions(options);
		
		FilteredAssociator filteredAssociator = new FilteredAssociator();
		
		// filtro de remoção
		if(remover != null){			
			System.out.println(remover);
			Remove rm = new Remove();
			rm.setAttributeIndices(remover);
			filteredAssociator.setFilter(rm);
		}
		
		filteredAssociator.setAssociator(fpGrowth);
		filteredAssociator.buildAssociations(instancias);
		
		//Deixar para debugar isso aqui pois exibe mais coisas
		//System.out.println(fpGrowth);
		
		List<AssociationRule> listaDeAssociacoes = fpGrowth.getAssociationRules();
		
		String retorno="";
		for(AssociationRule regra : listaDeAssociacoes){
			retorno += "Se: " +regra.getPremise().toString();
			retorno += " Então: "+regra.getConsequence().toString();
			retorno += " / Confiabilidade: "+ (int)(regra.getMetricValue()*100)+"%\n"; 
		}
		
		//System.out.println(retorno);
		return retorno;
	}

}
