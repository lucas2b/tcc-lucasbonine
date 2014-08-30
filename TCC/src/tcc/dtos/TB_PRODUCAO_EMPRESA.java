package tcc.dtos;

public class TB_PRODUCAO_EMPRESA {
	
	private int ID_PRODUCAO_EMPRESA;
	private TB_EMPRESAS EMPRESA;
	private TB_PRODUTOS PRODUTO;
	private float PESO_PRODUCAO;
	
	public int getID_PRODUCAO_EMPRESA() {
		return ID_PRODUCAO_EMPRESA;
	}
	public void setID_PRODUCAO_EMPRESA(int iD_PRODUCAO_EMPRESA) {
		ID_PRODUCAO_EMPRESA = iD_PRODUCAO_EMPRESA;
	}
	public TB_EMPRESAS getEMPRESA() {
		return EMPRESA;
	}
	public void setEMPRESA(TB_EMPRESAS eMPRESA) {
		EMPRESA = eMPRESA;
	}
	public TB_PRODUTOS getPRODUTO() {
		return PRODUTO;
	}
	public void setPRODUTO(TB_PRODUTOS pRODUTO) {
		PRODUTO = pRODUTO;
	}
	public float getPESO_PRODUCAO() {
		return PESO_PRODUCAO;
	}
	public void setPESO_PRODUCAO(float pESO_PRODUCAO) {
		PESO_PRODUCAO = pESO_PRODUCAO;
	}

}
