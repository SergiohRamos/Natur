package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.HistoricoClienteBean;
import Conexao.Conexao;

public class HistoricoClienteTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HistoricoClienteBean> Historico = new ArrayList<HistoricoClienteBean>();
	private String[] Colunas = { "Descrição", "Fardos", "Peso(Kg)", "Data de emissão", "Situação" };

	@Override
	public String getColumnName(int Coluna) {
		return Colunas[Coluna];
	}

	@Override
	public int getColumnCount() {
		return Colunas.length;
	}

	@Override
	public int getRowCount() {
		return Historico.size();
	}

	@Override
	public Object getValueAt(int Linha, int Coluna) {
		switch (Coluna) {
		case 0:
			return this.Historico.get(Linha).getDescricao_Material();
		case 1:
			return this.Historico.get(Linha).getQuantidade_Fardos();
		case 2:
			return this.Historico.get(Linha).getPeso_Kg();
		case 3:
			return this.Historico.get(Linha).getData_Emissao();
		case 4:
			return this.Historico.get(Linha).getSituacao_Pedido();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public List<HistoricoClienteBean> PreencherTabela(int ID_Cliente, String Situacao) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(Situacao.equals("Todos")) {
			sql = "SELECT * FROM view_pedidos AS vp WHERE vp.`ID Cliente` = ?";
		}else {
			sql = "SELECT * FROM view_pedidos AS vp WHERE vp.`ID Cliente` = ? AND vp.`Situação` LIKE "+ "'%" + Situacao + "%'";
		}
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Cliente);
			rs = pstmt.executeQuery();

			if (Historico.size() > 0) {
				Historico.clear();
			}
			while (rs.next()) {
				HistoricoClienteBean HistoricoClienteBean = new HistoricoClienteBean();
				HistoricoClienteBean.setDescricao_Material(rs.getString("Descricao"));
				HistoricoClienteBean.setQuantidade_Fardos(rs.getInt("Quantidade"));
				HistoricoClienteBean.setPeso_Kg(rs.getDouble("Peso"));
				HistoricoClienteBean.setData_Emissao(rs.getString("Data emissao"));
				HistoricoClienteBean.setSituacao_Pedido(rs.getString("Situação"));

				this.Historico.add(HistoricoClienteBean);
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar pedidos do cliente! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Historico;
	}

}
