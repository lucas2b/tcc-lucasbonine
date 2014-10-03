package tcc.mineradores;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.treevisualizer.PlaceNode1;
import weka.gui.treevisualizer.TreeVisualizer;

public class Classificacao{
 
	public boolean classificar(int index, DataSource arquivo) throws Exception{
    	Instances instancias = arquivo.getDataSet();
    	instancias.setClassIndex(index);
    	
    	String[] options = new String[4];
    	options[0] = "-C";
    	options[1] = "0.25";
    	options[2] = "-M";
    	options[3] = "2"; 	
    	
    	J48 j48 = new J48();
    	j48.setUnpruned(false);
    	j48.setOptions(options);
    	j48.buildClassifier(instancias);
    	
    	TreeVisualizer tv = new TreeVisualizer(null, j48.graph(), new PlaceNode1());        
    	tv.setSize(800, 600);
    	tv.fitToScreen();

    	//Mostrando janelinha
//        final javax.swing.JFrame jf = 
//          new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
//        jf.setSize(800,600);
//        jf.getContentPane().setLayout(new BorderLayout());
//        jf.getContentPane().add(tv, BorderLayout.CENTER);
//        
//        jf.addWindowListener(new java.awt.event.WindowAdapter() {
//          public void windowClosing(java.awt.event.WindowEvent e) {
//            jf.dispose();
//          }
//        });
//    
//        jf.setVisible(true);
        
        //Escrevendo imagem em arquivo
        BufferedImage imagem = ScreenImage.createImage(tv);
        
        //Caminho de escrita
        String caminhoDeEscrita = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/conhecimento");
		
		//Criando novo arquivo
		File arquivoImagem = new File(caminhoDeEscrita+"/classific.jpg");
        //File arquivoImagem = new File("C:\\teste\\classific.jpg");
		
		arquivoImagem.delete();
		arquivoImagem = new File(caminhoDeEscrita+"/classific.jpg");
		
		if(ImageIO.write(imagem, "jpg", arquivoImagem))
        	return true;
        else
        	return false;
    }
    
}