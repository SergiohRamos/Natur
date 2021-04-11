package Beans;

public class ClienteCompraTableBean {

	private String 	Nome_Empresa;
	private String 	Material;
	private int 	Quantidade_Fardo;
	private double  Peso_Kg;
	private double  Custo;
	
	public String getNome_Empresa() {
		return Nome_Empresa;
	}
	public void setNome_Empresa(String nome_Empresa) {
		Nome_Empresa = nome_Empresa;
	}
	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public int getQuantidade_Fardo() {
		return Quantidade_Fardo;
	}
	public void setQuantidade_Fardo(int quantidade_Fardo) {
		Quantidade_Fardo = quantidade_Fardo;
	}		
	public double getPeso_Kg() {
		return Peso_Kg;
	}
	public void setPeso_Kg(double peso_Kg) {
		Peso_Kg = peso_Kg;
	}
	public double getCusto() {
		return Custo;
	}
	public void setCusto(double custo) {
		Custo = custo;
	}
}
