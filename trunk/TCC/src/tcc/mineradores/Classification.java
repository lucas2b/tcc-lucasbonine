package tcc.mineradores;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class Classification{
 
    public boolean classificar(int index, DataSource arquivo) throws Exception{
    	
    	//DataSource arquivo = new DataSource("/weather.arff");
    	Instances instancias = arquivo.getDataSet();
    	instancias.setClassIndex(index);
    	
    	//são 255 alternativas, então a seleção vai de 0 a 254
    	System.out.println("Numero de atributos: " + instancias.numAttributes());
    	
    	String[] options = new String[4];
    	options[0] = "-C";
    	options[1] = "0.25";
    	options[2] = "-M";
    	options[3] = "2";
    	
    	
    	J48 j48 = new J48();
    	j48.setUnpruned(false);
    	j48.setOptions(options);
    	j48.buildClassifier(instancias);
    	
    	//Mostrando janelinha
        final javax.swing.JFrame jf = 
          new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(500,400);
        jf.getContentPane().setLayout(new BorderLayout());
        
        TreeVisualizer tv = new TreeVisualizer(null, j48.graph(), new PlaceNode2());
        tv.setSize(800,600);
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
          public void windowClosing(java.awt.event.WindowEvent e) {
            jf.dispose();
          }
        });
    
        jf.setVisible(true);
        tv.fitToScreen();
        
        //Escrevendo imagem em arquivo
        BufferedImage imagem = ScreenImage.createImage(tv);
        File outputfile = new File("C:\\teste\\out.jpg");
        
        if(ImageIO.write(imagem, "jpg", outputfile))
        	return true;
        else
        	return false;
    }
    
}