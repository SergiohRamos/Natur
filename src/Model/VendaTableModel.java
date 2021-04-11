package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.PreTriagemTableBean;

public class VendaTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PreTriagemTableBean> dados = new ArrayList<>();
	private String[] colunas = {"Material", "Preço/Kg", "Peso", "Valor Total" };

	@Override
	public String getColumnName(int coluna) {
		return this.colunas[coluna];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {
		case 0:
			return dados.get(linha).getMaterial();
		case 1:
			return dados.get(linha).getPreco_Kg();
		case 2:
			return dados.get(linha).getPesoMaterial();
		case 3:
			return dados.get(linha).getValor_total();
		}
		return null;
	}

	public void addRow(PreTriagemTableBean ptb) {
		this.dados.add(ptb);
		fireTableDataChanged();
	}
	public void removeRow(int Row) {
		this.dados.remove(Row);
		this.fireTableRowsDeleted(Row, Row);
	}
	
	public void clear() {
		this.dados.clear();
		this.fireTableDataChanged();
	}

}
