package tcc.dtos;

public class TB_PERGUNTAS {
	
	private int ID_PERGUNTA;
	private String PERGUNTA_TXT;
	private boolean MULTI_SELECAO;
	
	public boolean isMULTI_SELECAO() {
		return MULTI_SELECAO;
	}
	public void setMULTI_SELECAO(boolean mULTI_SELECAO) {
		MULTI_SELECAO = mULTI_SELECAO;
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
