package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.HistoricoFornecedorBean;
import Conexao.Conexao;

public class HistoricoTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HistoricoFornecedorBean> Historico = new ArrayList<>();
	private String[] Colunas = { "Material", "Peso (Kg)", "Valor arrecadado", "Data" };

	@Override
	public String getColumnName(int coluna) {
		return Colunas[coluna];
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
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {
		case 0:
			return this.Historico.get(linha).getMaterial();
		case 1:
			return this.Historico.get(linha).getQuantidade();
		case 2:
			return this.Historico.get(linha).getValorArrecadado();
		case 3:
			return this.Historico.get(linha).getData();
		}
		return null;
	}

	public List<HistoricoFornecedorBean> readHistorico(int ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String BuscaHistorico = "SELECT * FROM view_historicofornecedor as vh WHERE vh.Id = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(BuscaHistorico);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();

			if (Historico.size() > 0) {
				this.Historico.clear();
			}
			while (rs.next()) {
				HistoricoFornecedorBean Historico = new HistoricoFornecedorBean();
				Historico.setMaterial(rs.getString("Material"));
				Historico.setQuantidade(rs.getDouble("Quantidade"));
				Historico.setValorArrecadado(rs.getDouble("Valor arrecadado"));
				Historico.setData(rs.getString("Data"));

				this.Historico.add(Historico);
			}
			this.fireTableDataChanged();

		} catch (Exception e) {
			System.out.println("Erro ao buscar historico do fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Historico;

	}

}
