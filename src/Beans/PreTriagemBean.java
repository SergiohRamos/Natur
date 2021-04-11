package Beans;

public class PreTriagemBean {

	private int id_pre_triagem;
	private int id_funcionario;
	private int id_material;
	private double peso;

	public int getId_pre_triagem() {
		return id_pre_triagem;
	}

	public void setId_pre_triagem(int id_pre_triagem) {
		this.id_pre_triagem = id_pre_triagem;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public int getId_material() {
		return id_material;
	}

	public void setId_material(int id_material) {
		this.id_material = id_material;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Object getDados() {
		return "ID_Fornecedor " + this.id_funcionario + "\n ID_Material" + this.id_material + "\n Peso" + this.peso;
	}
}
