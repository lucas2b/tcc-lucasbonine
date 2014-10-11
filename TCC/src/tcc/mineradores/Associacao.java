package tcc.mineradores;

import weka.associations.FPGrowth;
import weka.associations.FilteredAssociator;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

public class Associacao {
	
	public void associar(String[] remover, DataSource arquivo) throws Exception{
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
		
		// filtro de remo��o
		 Remove rm = new Remove();
		 rm.setAttributeIndices("84,85,86,87,88,89,90,91,168,169,216,217,218");
		
		FilteredAssociator filteredAssociator = new FilteredAssociator();
		filteredAssociator.setFilter(rm);
		filteredAssociator.setAssociator(fpGrowth);
		filteredAssociator.buildAssociations(instancias);
		
		System.out.println(filteredAssociator);
		
//		List<AssociationRule> listaDeAssociacoes = fpGrowth.getAssociationRules();
//		
//		for(AssociationRule regra : listaDeAssociacoes){
//			System.out.print(regra.getPremise().toString());
//			System.out.print(regra.getConsequence().toString());
//			System.out.println();
//		}
	}

}