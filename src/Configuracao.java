import java.io.*;
import java.util.Scanner;

public class Configuracao {
    private String nomeUsuario;
    private String tema;
    private final String ARQUIVO_CONFIG = "config.txt";

    public void definirPreferencias(Scanner scanner) {
        System.out.println("\n--- Configuração Inicial ---");

        do {
            System.out.print("Como você gostaria de ser chamado? ");
            this.nomeUsuario = scanner.nextLine();
            if(this.nomeUsuario.trim().isEmpty()) {
                System.out.println("O nome não pode ser vazio.");
            }
        } while(this.nomeUsuario.trim().isEmpty());

        while(true) {
            System.out.println("\nEscolha o tema visual:");
            System.out.println("1. Claro");
            System.out.println("2. Escuro");
            System.out.print("Sua escolha (1 ou 2): ");

            String opcao = scanner.nextLine();

            if(opcao.equals("1")) {
                this.tema = "CLARO";
                break;
            } else if(opcao.equals("2")) {
                this.tema = "ESCURO";
                break;
            } else {
                System.out.println("Entrada inválida! Por favor, digite apenas 1 ou 2.");
            }
        }

        salvarArquivo();
    }

    private void salvarArquivo() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CONFIG))) {
            writer.write(nomeUsuario);
            writer.newLine();
            writer.write(tema);
            System.out.println("Preferências salvas com sucesso!");
        } catch(IOException e) {
            System.err.println("Erro ao salvar configurações: " + e.getMessage());
        }
    }

    public void carregarPreferencias() {
        File arquivo = new File(ARQUIVO_CONFIG);
        if(! arquivo.exists()) {
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            this.nomeUsuario = reader.readLine();
            this.tema = reader.readLine();
            System.out.println("👋 Bem-vindo de volta, " + nomeUsuario + "! Tema atual: " + tema);
        } catch(IOException e) {
            System.err.println("Erro ao ler configurações.");
        }
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}