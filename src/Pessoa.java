public abstract class Pessoa implements Mostravel, Validavel {
    final private int id;
    private String nome;
    final private String cpf;

    public Pessoa(int id, String nome, String cpf) throws ValidacaoException {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;

        this.validar();
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

    @Override
    public void validar() throws ValidacaoException {
        if(this.getNome() == null || this.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome do cliente não pode estar vazio.");
        }
        if(this.getCpf() == null || this.getCpf().trim().isEmpty()) {
            throw new ValidacaoException("O CPF é obrigatório");
        }
        if(this.getCpf().length() < 11) {
            throw new ValidacaoException("O CPF é muito curto");
        }
    }
}
