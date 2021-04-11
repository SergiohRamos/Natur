package Beans;

public class AdmPedidosBean {

	private int ID_Pedido;
	private int ID_Cliente;
	private String Cliente;
	private String Data_Emissao;
	private int ID_Item;
	private int ID_Material;
	private String Material;
	private int Quantidade;
	private double Peso_Kg;

	public int getID_Pedido() {
		return ID_Pedido;
	}

	public void setID_Pedido(int iD_Pedido) {
		ID_Pedido = iD_Pedido;
	}

	public int getID_Cliente() {
		return ID_Cliente;
	}

	public void setID_Cliente(int iD_Cliente) {
		ID_Cliente = iD_Cliente;
	}

	public String getCliente() {
		return Cliente;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getData_Emissao() {
		return Data_Emissao;
	}

	public void setData_Emissao(String data_Emissao) {
		Data_Emissao = data_Emissao;
	}

	public int getID_Item() {
		return ID_Item;
	}

	public void setID_Item(int iD_Item) {
		ID_Item = iD_Item;
	}

	public int getID_Material() {
		return ID_Material;
	}

	public void setID_Material(int iD_Material) {
		ID_Material = iD_Material;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public double getPeso_Kg() {
		return Peso_Kg;
	}

	public void setPeso_Kg(double peso_Kg) {
		Peso_Kg = peso_Kg;
	}

}
