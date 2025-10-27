public class Funcionario extends Pessoa {
    final private String cargo;
    final private double salario;

    public Funcionario(int id, String nome, String cpf, String cargo, double salario) {
        super(id, nome, cpf);
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.print(" | Cargo: " + this.cargo + " | Salario: R$" + this.salario + "\n");
    }
}
