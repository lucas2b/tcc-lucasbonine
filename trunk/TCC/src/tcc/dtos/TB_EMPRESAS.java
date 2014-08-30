package tcc.dtos;

public class TB_EMPRESAS {
	
	//Dados referentes à empresa
	private int ID_EMPRESA;
	private String NOME_EMPRESA;
	private TB_CIDADES CIDADE;
	private String CNPJ_EMPRESA;
	private String ENDERECO_EMPRESA;
	private double EMPRESA_LATITUDE;
	private double EMPRESA_LONGITUDE;
	private String TELEFONE1_EMPRESA;
	private String TELEFONE2_EMPRESA;
	private String EMAIL_EMPRESA;
	private String SITE_EMPRESA;
	private int EMPREGOS_FORMAIS;
	private float VLR_ANUAL_VENDAS_MERC_INT;
	private float VLR_ANUAL_VENDAS_MERC_EXT;
	private String DESCRICAO_EMPRESA;
	
	//Dados referentes ao dirigente da empresa
	private String NOME_DIRIGENTE;
	private String CPF_DIRIGENTE;
	private String TELEFONE1_DIRIGENTE;
	private String TELEFONE2_DIRIGENTE;
	private String EMAIL_DIRIGENTE;
	private int IDADE_DIRIGENTE;
	private String ESCOLARIDADE_DIRIGENTE;
	private TB_CLASSIFIC_ESPECIALISTA CLASSIFIC_ESPECIALISTA;
	
	public int getID_EMPRESA() {
		return ID_EMPRESA;
	}
	public void setID_EMPRESA(int iD_EMPRESA) {
		ID_EMPRESA = iD_EMPRESA;
	}
	public String getNOME_EMPRESA() {
		return NOME_EMPRESA;
	}
	public void setNOME_EMPRESA(String nOME_EMPRESA) {
		NOME_EMPRESA = nOME_EMPRESA;
	}
	public TB_CIDADES getCIDADE() {
		return CIDADE;
	}
	public void setCIDADE(TB_CIDADES cIDADE) {
		CIDADE = cIDADE;
	}
	public String getCNPJ_EMPRESA() {
		return CNPJ_EMPRESA;
	}
	public void setCNPJ_EMPRESA(String cNPJ_EMPRESA) {
		CNPJ_EMPRESA = cNPJ_EMPRESA;
	}
	public String getENDERECO_EMPRESA() {
		return ENDERECO_EMPRESA;
	}
	public void setENDERECO_EMPRESA(String eNDERECO_EMPRESA) {
		ENDERECO_EMPRESA = eNDERECO_EMPRESA;
	}
	public double getEMPRESA_LATITUDE() {
		return EMPRESA_LATITUDE;
	}
	public void setEMPRESA_LATITUDE(double eMPRESA_LATITUDE) {
		EMPRESA_LATITUDE = eMPRESA_LATITUDE;
	}
	public double getEMPRESA_LONGITUDE() {
		return EMPRESA_LONGITUDE;
	}
	public void setEMPRESA_LONGITUDE(double eMPRESA_LONGITUDE) {
		EMPRESA_LONGITUDE = eMPRESA_LONGITUDE;
	}
	public String getTELEFONE1_EMPRESA() {
		return TELEFONE1_EMPRESA;
	}
	public void setTELEFONE1_EMPRESA(String tELEFONE1_EMPRESA) {
		TELEFONE1_EMPRESA = tELEFONE1_EMPRESA;
	}
	public String getTELEFONE2_EMPRESA() {
		return TELEFONE2_EMPRESA;
	}
	public void setTELEFONE2_EMPRESA(String tELEFONE2_EMPRESA) {
		TELEFONE2_EMPRESA = tELEFONE2_EMPRESA;
	}
	public String getEMAIL_EMPRESA() {
		return EMAIL_EMPRESA;
	}
	public void setEMAIL_EMPRESA(String eMAIL_EMPRESA) {
		EMAIL_EMPRESA = eMAIL_EMPRESA;
	}
	public String getSITE_EMPRESA() {
		return SITE_EMPRESA;
	}
	public void setSITE_EMPRESA(String sITE_EMPRESA) {
		SITE_EMPRESA = sITE_EMPRESA;
	}
	public int getEMPREGOS_FORMAIS() {
		return EMPREGOS_FORMAIS;
	}
	public void setEMPREGOS_FORMAIS(int eMPREGOS_FORMAIS) {
		EMPREGOS_FORMAIS = eMPREGOS_FORMAIS;
	}
	public float getVLR_ANUAL_VENDAS_MERC_INT() {
		return VLR_ANUAL_VENDAS_MERC_INT;
	}
	public void setVLR_ANUAL_VENDAS_MERC_INT(float vLR_ANUAL_VENDAS_MERC_INT) {
		VLR_ANUAL_VENDAS_MERC_INT = vLR_ANUAL_VENDAS_MERC_INT;
	}
	public float getVLR_ANUAL_VENDAS_MERC_EXT() {
		return VLR_ANUAL_VENDAS_MERC_EXT;
	}
	public void setVLR_ANUAL_VENDAS_MERC_EXT(float vLR_ANUAL_VENDAS_MERC_EXT) {
		VLR_ANUAL_VENDAS_MERC_EXT = vLR_ANUAL_VENDAS_MERC_EXT;
	}
	public String getDESCRICAO_EMPRESA() {
		return DESCRICAO_EMPRESA;
	}
	public void setDESCRICAO_EMPRESA(String dESCRICAO_EMPRESA) {
		DESCRICAO_EMPRESA = dESCRICAO_EMPRESA;
	}
	public String getNOME_DIRIGENTE() {
		return NOME_DIRIGENTE;
	}
	public void setNOME_DIRIGENTE(String nOME_DIRIGENTE) {
		NOME_DIRIGENTE = nOME_DIRIGENTE;
	}
	public String getCPF_DIRIGENTE() {
		return CPF_DIRIGENTE;
	}
	public void setCPF_DIRIGENTE(String cPF_DIRIGENTE) {
		CPF_DIRIGENTE = cPF_DIRIGENTE;
	}
	public String getTELEFONE1_DIRIGENTE() {
		return TELEFONE1_DIRIGENTE;
	}
	public void setTELEFONE1_DIRIGENTE(String tELEFONE1_DIRIGENTE) {
		TELEFONE1_DIRIGENTE = tELEFONE1_DIRIGENTE;
	}
	public String getTELEFONE2_DIRIGENTE() {
		return TELEFONE2_DIRIGENTE;
	}
	public void setTELEFONE2_DIRIGENTE(String tELEFONE2_DIRIGENTE) {
		TELEFONE2_DIRIGENTE = tELEFONE2_DIRIGENTE;
	}
	public String getEMAIL_DIRIGENTE() {
		return EMAIL_DIRIGENTE;
	}
	public void setEMAIL_DIRIGENTE(String eMAIL_DIRIGENTE) {
		EMAIL_DIRIGENTE = eMAIL_DIRIGENTE;
	}
	public int getIDADE_DIRIGENTE() {
		return IDADE_DIRIGENTE;
	}
	public void setIDADE_DIRIGENTE(int iDADE_DIRIGENTE) {
		IDADE_DIRIGENTE = iDADE_DIRIGENTE;
	}
	public String getESCOLARIDADE_DIRIGENTE() {
		return ESCOLARIDADE_DIRIGENTE;
	}
	public void setESCOLARIDADE_DIRIGENTE(String eSCOLARIDADE_DIRIGENTE) {
		ESCOLARIDADE_DIRIGENTE = eSCOLARIDADE_DIRIGENTE;
	}
	public TB_CLASSIFIC_ESPECIALISTA getCLASSIFIC_ESPECIALISTA() {
		return CLASSIFIC_ESPECIALISTA;
	}
	public void setCLASSIFIC_ESPECIALISTA(
			TB_CLASSIFIC_ESPECIALISTA cLASSIFIC_ESPECIALISTA) {
		CLASSIFIC_ESPECIALISTA = cLASSIFIC_ESPECIALISTA;
	}
	
}
