package tcc.dtos;

public class TB_PERGUNTAS {
	
	private int ID_PERGUNTA;
	private String PERGUNTA_TXT;
	private boolean SELECAO_UNICA;
	
	
	public boolean isSELECAO_UNICA() {
		return SELECAO_UNICA;
	}
	public void setSELECAO_UNICA(boolean sELECAO_UNICA) {
		SELECAO_UNICA = sELECAO_UNICA;
	}
	public int getID_PERGUNTA() {
		return ID_PERGUNTA;
	}
	public void setID_PERGUNTA(int iD_PERGUNTA) {
		ID_PERGUNTA = iD_PERGUNTA;
	}
	public String getPERGUNTA_TXT() {
		return PERGUNTA_TXT;
	}
	public void setPERGUNTA_TXT(String pERGUNTA_TXT) {
		PERGUNTA_TXT = pERGUNTA_TXT;
	}
	
	

}
