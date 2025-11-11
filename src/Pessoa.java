public abstract class Pessoa implements Mostravel {
    final private int id;
    private String nome;
    final private String cpf;

    public Pessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public void mostrarDados() {
        System.out.print("ID: " + this.id + " | CPF: " + this.cpf + " | nome: " + this.nome);
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
