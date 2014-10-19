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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_PERGUNTA;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TB_PERGUNTAS other = (TB_PERGUNTAS) obj;
		if (ID_PERGUNTA != other.ID_PERGUNTA)
			return false;
		return true;
	}
	
	

}
