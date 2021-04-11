package Beans;

public class FornecedorBean {

	private int Id_fornecedor;
	private String Nome_fornecedor;
	private String Cpf_fornecedor;
	private String Uf_fornecedor;
	private String Cidade_fornecedor;
	private String Cep_fornecedor;
	private String Tele_fornecedor;
	private String Email_fornecedor;
	private String Senha_fornecedor;
	private byte[] imagemPerfil;
	
	public int getId_fornecedor() {
		return Id_fornecedor;
	}
	public void setId_fornecedor(int id_fornecedor) {
		this.Id_fornecedor = id_fornecedor;
	}
	public String getNome_fornecedor() {
		return Nome_fornecedor;
	}
	public void setNome_fornecedor(String nome_fornecedor) {
		this.Nome_fornecedor = nome_fornecedor;
	}
	public String getCpf_fornecedor() {
		return Cpf_fornecedor;
	}
	public void setCpf_fornecedor(String cpf_fornecedor) {
		this.Cpf_fornecedor = cpf_fornecedor;
	}
	public String getUf_fornecedor() {
		return Uf_fornecedor;
	}
	public void setUf_fornecedor(String Uf_fornecedor) {
		this.Uf_fornecedor = Uf_fornecedor;
	}
	public String getCep_fornecedor() {
		return Cep_fornecedor;
	}
	public void setCep_fornecedor(String cep_fornecedor) {
		this.Cep_fornecedor = cep_fornecedor;
	}
	public String getTele_fornecedor() {
		return Tele_fornecedor;
	}
	public void setTele_fornecedor(String tele_fornecedor) {
		this.Tele_fornecedor = tele_fornecedor;
	}
	public String getEmail_fornecedor() {
		return Email_fornecedor;
	}
	public void setEmail_fornecedor(String email_fornecedor) {
		this.Email_fornecedor = email_fornecedor;
	}
	public String getSenha_fornecedor() {
		return Senha_fornecedor;
	}
	public void setSenha_fornecedor(String senha_fornecedor) {
		Senha_fornecedor = senha_fornecedor;
	}
	public byte[] getImagemPerfil() {
		return imagemPerfil;
	}
	public void setImagemPerfil(byte[] imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}
	public String getCidade_fornecedor() {
		return Cidade_fornecedor;
	}
	public void setCidade_fornecedor(String cidade_fornecedor) {
		Cidade_fornecedor = cidade_fornecedor;
	}
	
}
