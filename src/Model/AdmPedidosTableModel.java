package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.AdmPedidosBean;
import Conexao.Conexao;

public class AdmPedidosTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AdmPedidosBean> Pedidos = new ArrayList<AdmPedidosBean>();
	private String[] Colunas = { "ID Pedido", "Cliente", "Material", "Fardos", "Peso (Kg)", "Data de emissão" };

	@Override
	public String getColumnName(int coluna) {
		return this.Colunas[coluna];
	}

	@Override
	public int getColumnCount() {
		return this.Colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.Pedidos.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {
		case 0:
			return this.Pedidos.get(linha).getID_Pedido();
		case 1:
			return this.Pedidos.get(linha).getCliente();
		case 2:
			return this.Pedidos.get(linha).getMaterial();
		case 3:
			return this.Pedidos.get(linha).getQuantidade();
		case 4:
			return this.Pedidos.get(linha).getPeso_Kg();
		case 5:
			return this.Pedidos.get(linha).getData_Emissao();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void addRow(AdmPedidosBean AdmPedidosBean) {
		this.Pedidos.add(AdmPedidosBean);
		fireTableDataChanged();
	}

	public void removeRow(int Row) {
		this.Pedidos.remove(Row);
		fireTableDataChanged();
	}

	public List<AdmPedidosBean> PreencherTabela(String Situacao) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		if (Pedidos.size() > 0) {
			Pedidos.clear();
		}
		if (Situacao.equals("Todos")) {
			sql = "SELECT * FROM view_pedidos as vp";
		} else {
			sql = "SELECT * FROM view_pedidos as vp WHERE Situação = " + "'" + Situacao + "'";
		}
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdmPedidosBean ADMPedidos = new AdmPedidosBean();

				ADMPedidos.setID_Pedido(rs.getInt("ID Pedido"));
				ADMPedidos.setID_Cliente(rs.getInt("ID Cliente"));
				ADMPedidos.setCliente(rs.getString("Cliente"));
				ADMPedidos.setID_Item(rs.getInt("ID item"));
				ADMPedidos.setID_Material(rs.getInt("ID Material"));
				ADMPedidos.setMaterial(rs.getString("Descricao"));
				ADMPedidos.setQuantidade(rs.getInt("Quantidade"));
				ADMPedidos.setPeso_Kg(rs.getDouble("Peso"));
				ADMPedidos.setData_Emissao(rs.getString("Data emissao"));

				Pedidos.add(ADMPedidos);
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao buscar dados dos pedidos! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Pedidos;
	}

	public void clearTable() {
		this.Pedidos.clear();
		this.fireTableDataChanged();
	}

	public int getIDMaterial(int Indice) {
		return this.Pedidos.get(Indice).getID_Material();
	}
}
