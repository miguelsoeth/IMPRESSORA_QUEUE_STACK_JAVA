package PaineisLib;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PainelTblImpressao extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private static DefaultTableModel model;
	private JTable tb_pilha;
	private JScrollPane spnDados;
	
	public PainelTblImpressao(int x, int y, int w, int h) {
		criarPainel(x, y, w, h);
	}
	
	public void criarPainel(int x, int y, int w, int h) {
		//Adiciona borda ao Jpanel
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(x, y, w, h);
		setLayout(null);
		
		//Titulo da tabela
		title = new JLabel("Documentos Impressos");
		title.setBounds(10, 0, 200, 25);
		add(title);
		
		//Modelo que define as colunas da tabela
		model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("Paginas");
		
		//Criação da tabela
		tb_pilha = new JTable(model);
		tb_pilha.setDefaultEditor(Object.class, null);
		
		//Criação do painel dinamico para tabela
		spnDados = new JScrollPane(tb_pilha);
		spnDados.setBounds(0, 25, w, h-25);
		add(spnDados);
	}

	public static void addRow(String nome, int pagina) {
		Object[] rowData = {nome, pagina};
		model.addRow(rowData);
	}
	
	public static void ResetRows() {
		model.setRowCount(0);
	}
}
