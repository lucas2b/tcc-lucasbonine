package tcc.dtos;

public class TB_UF {
	
	private int ID_UF;
	private String NOME_UF;
	
	public int getID_UF() {
		return ID_UF;
	}
	public void setID_UF(int iD_UF) {
		ID_UF = iD_UF;
	}
	public String getNOME_UF() {
		return NOME_UF;
	}
	public void setNOME_UF(String nOME_UF) {
		NOME_UF = nOME_UF;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_UF;
		result = prime * result + ((NOME_UF == null) ? 0 : NOME_UF.hashCode());
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
		TB_UF other = (TB_UF) obj;
		if (ID_UF != other.ID_UF)
			return false;
		if (NOME_UF == null) {
			if (other.NOME_UF != null)
				return false;
		} else if (!NOME_UF.equals(other.NOME_UF))
			return false;
		return true;
	}

}
