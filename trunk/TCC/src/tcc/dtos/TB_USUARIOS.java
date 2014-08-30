package tcc.dtos;

public class TB_USUARIOS {
	
	private int ID_USUARIO;
	private String NOME_USUARIO;
	private String SENHA_USUARIO;
	private int NIVEL_ACECSSO;
	
	public int getID_USUARIO() {
		return ID_USUARIO;
	}
	public void setID_USUARIO(int iD_USUARIO) {
		ID_USUARIO = iD_USUARIO;
	}
	public String getNOME_USUARIO() {
		return NOME_USUARIO;
	}
	public void setNOME_USUARIO(String nOME_USUARIO) {
		NOME_USUARIO = nOME_USUARIO;
	}
	public String getSENHA_USUARIO() {
		return SENHA_USUARIO;
	}
	public void setSENHA_USUARIO(String sENHA_USUARIO) {
		SENHA_USUARIO = sENHA_USUARIO;
	}
	public int getNIVEL_ACECSSO() {
		return NIVEL_ACECSSO;
	}
	public void setNIVEL_ACECSSO(int nIVEL_ACECSSO) {
		NIVEL_ACECSSO = nIVEL_ACECSSO;
	}
	
	

}
