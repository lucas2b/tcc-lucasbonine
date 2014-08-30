package tcc.dtos;

public class TB_CIDADE {
	
	private int ID_CIDADE;
	private String NOME_CIDADE;
	private TB_UF UF;
	
	public int getID_CIDADE() {
		return ID_CIDADE;
	}
	public void setID_CIDADE(int iD_CIDADE) {
		ID_CIDADE = iD_CIDADE;
	}
	public String getNOME_CIDADE() {
		return NOME_CIDADE;
	}
	public void setNOME_CIDADE(String nOME_CIDADE) {
		NOME_CIDADE = nOME_CIDADE;
	}
	public TB_UF getUF() {
		return UF;
	}
	public void setUF(TB_UF uF) {
		UF = uF;
	}
	
	

}
