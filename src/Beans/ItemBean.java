package Beans;

public class ItemBean {

	private int id_Item;
	private int id_Material;
	private int Quantidade_Fardos;
	private double Peso_Kg;
	
	public int getId_Item() {
		return id_Item;
	}
	public void setId_Item(int id_Item) {
		this.id_Item = id_Item;
	}
	public int getId_Material() {
		return id_Material;
	}
	public void setId_Material(int id_Material) {
		this.id_Material = id_Material;
	}
	public int getQuantidade_Fardos() {
		return Quantidade_Fardos;
	}
	public void setQuantidade_Fardos(int quantidade_Fardos) {
		Quantidade_Fardos = quantidade_Fardos;
	}
	public double getPeso_Kg() {
		return Peso_Kg;
	}
	public void setPeso_Kg(double peso_Kg) {
		Peso_Kg = peso_Kg;
	}
}
