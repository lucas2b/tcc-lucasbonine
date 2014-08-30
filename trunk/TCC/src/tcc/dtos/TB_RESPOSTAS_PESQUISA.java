package tcc.dtos;

public class TB_RESPOSTAS_PESQUISA {
	
	private int ID_RESPOSTA;
	private TB_EMPRESAS EMPRESA;
	private TB_PERGUNTAS PERGUNTA;
	private TB_ALTERNATIVAS ALTERNATIVA;
	
	public int getID_RESPOSTA() {
		return ID_RESPOSTA;
	}
	public void setID_RESPOSTA(int iD_RESPOSTA) {
		ID_RESPOSTA = iD_RESPOSTA;
	}
	public TB_EMPRESAS getEMPRESA() {
		return EMPRESA;
	}
	public void setEMPRESA(TB_EMPRESAS eMPRESA) {
		EMPRESA = eMPRESA;
	}
	public TB_PERGUNTAS getPERGUNTA() {
		return PERGUNTA;
	}
	public void setPERGUNTA(TB_PERGUNTAS pERGUNTA) {
		PERGUNTA = pERGUNTA;
	}
	public TB_ALTERNATIVAS getALTERNATIVA() {
		return ALTERNATIVA;
	}
	public void setALTERNATIVA(TB_ALTERNATIVAS aLTERNATIVA) {
		ALTERNATIVA = aLTERNATIVA;
	}
	
	

}
