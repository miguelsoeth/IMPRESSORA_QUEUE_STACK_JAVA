package impressoraLib;

public class Documento {
    private String nome;
    private ListaPaginas listaPaginas;

    public Documento(String nome, int numeroPaginas) {
        this.nome = nome;
        this.listaPaginas = new ListaPaginas();
        for (int pagina = 1; pagina <= numeroPaginas; pagina++) {
            this.listaPaginas.adicionarPagina(Integer.toString(pagina));
        }
    }

    public String getNome() {
        return nome;
    }

    public ListaPaginas getListaPaginas() {
        return listaPaginas;
    }
}
