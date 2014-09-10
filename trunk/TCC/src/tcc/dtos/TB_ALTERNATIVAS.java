package tcc.dtos;

public class TB_ALTERNATIVAS {
	
	private int ID_ALTERNATIVA;
	private TB_PERGUNTAS PERGUNTA;
	private String COD_ALTERNATIVA;
	private String ALTERNATIVA_TXT;
	
	//CAMPO EXTRA, não consta no BD nem no DAO
	private boolean MARCADA; 
	
	public boolean isMARCADA() {
		return MARCADA;
	}
	public void setMARCADA(boolean mARCADA) {
		MARCADA = mARCADA;
	}
	public int getID_ALTERNATIVA() {
		return ID_ALTERNATIVA;
	}
	public void setID_ALTERNATIVA(int iD_ALTERNATIVA) {
		ID_ALTERNATIVA = iD_ALTERNATIVA;
	}
	public TB_PERGUNTAS getPERGUNTA() {
		return PERGUNTA;
	}
	public void setPERGUNTA(TB_PERGUNTAS pERGUNTA) {
		PERGUNTA = pERGUNTA;
	}
	public String getCOD_ALTERNATIVA() {
		return COD_ALTERNATIVA;
	}
	public void setCOD_ALTERNATIVA(String cOD_ALTERNATIVA) {
		COD_ALTERNATIVA = cOD_ALTERNATIVA;
	}
	public String getALTERNATIVA_TXT() {
		return ALTERNATIVA_TXT;
	}
	public void setALTERNATIVA_TXT(String aLTERNATIVA_TXT) {
		ALTERNATIVA_TXT = aLTERNATIVA_TXT;
	}
	
	

}
