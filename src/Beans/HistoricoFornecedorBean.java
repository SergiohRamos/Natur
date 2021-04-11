package Beans;

public class HistoricoFornecedorBean {

	private int    Id_Fornecedor;
	private String Fornecedor;
	private String Material;
	private double Quantidade;
	private String Data;
	private double ValorArrecadado;
	
	public int getId() {
		return Id_Fornecedor;
	}
	public void setId(int Id_Fornecedor) {
		this.Id_Fornecedor = Id_Fornecedor;
	}
	public String getFornecedor() {
		return Fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		Fornecedor = fornecedor;
	}
	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public double getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(double quantidade) {
		Quantidade = quantidade;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public double getValorArrecadado() {
		return ValorArrecadado;
	}
	public void setValorArrecadado(double valorArrecadado) {
		ValorArrecadado = valorArrecadado;
	}
}
