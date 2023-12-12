package impressoraLib;

import java.util.Stack;

public class ListaPaginas {
	
    private Stack<NumPagina> pilhaPaginas;

    public ListaPaginas() {
        this.pilhaPaginas = new Stack<>();
    }

    public void adicionarPagina(String conteudo) {
        NumPagina novaPagina = new NumPagina(conteudo);
        pilhaPaginas.push(novaPagina);
    }

    public void removerUltimaPagina() {
        if (!pilhaPaginas.isEmpty()) {
            pilhaPaginas.pop();
        }
    }

    public boolean isEmpty() {
        return pilhaPaginas.isEmpty();
    }

    public NumPagina getFim() {
        return pilhaPaginas.isEmpty() ? null : pilhaPaginas.peek();
    }
}