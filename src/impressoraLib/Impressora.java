package impressoraLib;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import PaineisLib.PainelTblFila;

public class Impressora {
	//Lista encadeada usada para lista de impressão
    private LinkedList<Documento> filaImpressao = new LinkedList<>();
    
    public Impressora() {
    	
    }

    public void adicionarDocumento(Documento documento) {
    	//Adiciona um elemento a fila, diferença do offer pro add é que ele trata erros de maneira diferente
        filaImpressao.offer(documento);
        //Adiciona um elemento a tabela de fila, usado para visualizar a fila.
        PainelTblFila.addRow(documento);
    }
    
    public void removerUltimoDocumento() {
    	//Se a fila não tiver vazia
    	if (!filaImpressao.isEmpty()) {
    		//Remove o ultimo documento como pilha (pop)
    		filaImpressao.pop();
    		PainelTblFila.removerUltimaLinha();
    	} else {
        	JOptionPane.showMessageDialog(null, "Sem documentos para remover!");
        }
    	
	}
    
    public void removerPrimeiroDocumento() {
    	//Se a fila não tiver vazia
    	if (!filaImpressao.isEmpty()) {
    		//Remove o primeiro documento como pilha (poll)
    		filaImpressao.poll();
    		PainelTblFila.removerPrimeiraLinha();
    	} else {
        	JOptionPane.showMessageDialog(null, "Sem documentos para remover!");
        }
	}
    
    public LinkedList<Documento> getFilaImpressao() {
		return filaImpressao;
	}
}
