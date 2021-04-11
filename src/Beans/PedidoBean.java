package Beans;

public class PedidoBean {

	private int id_Pedido;
	private int id_Cliente;
	private String Data_Emissao;
	
	public int getId_Pedido() {
		return id_Pedido;
	}
	public void setId_Pedido(int id_Pedido) {
		this.id_Pedido = id_Pedido;
	}
	public int getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	public String getData_Emissao() {
		return Data_Emissao;
	}
	public void setData_Emissao(String data_Emissao) {
		Data_Emissao = data_Emissao;
	}
}
