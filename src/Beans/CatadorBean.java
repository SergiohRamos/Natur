package Beans;

public class CatadorBean {
	private int ID;
	private String nome;
	private String cpf;
	private String senha;
	private String Cidade_Atua��o;
	private String UF;
	private String telefone;
	private String email;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCidade_Atua��o() {
		return Cidade_Atua��o;
	}

	public void setCidade_Atua��o(String cidade_Atua��o) {
		Cidade_Atua��o = cidade_Atua��o;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
