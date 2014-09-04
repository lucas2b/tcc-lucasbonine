package tcc.dtos;

public class TB_CIDADES {
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_CIDADE;
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
		TB_CIDADES other = (TB_CIDADES) obj;
		if (ID_CIDADE != other.ID_CIDADE)
			return false;
		return true;
	}
	
	

}
