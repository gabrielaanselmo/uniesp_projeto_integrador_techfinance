import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cria um cliente com CPF, senha e saldo iniciais
        Cliente cliente = new Cliente("1", "1234", 1000.0);

        // Cria uma conta corrente associada ao cliente
        ContaCorrente contaCorrente = new ContaCorrente(cliente);

        // Realiza o login do cliente
        boolean loginEfetuado = false;
        do {
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.next();
            System.out.print("Digite sua senha: ");
            String senha = scanner.next();

            if (cpf.equals(cliente.getCpf()) && senha.equals(cliente.getSenha())) {
                loginEfetuado = true;
                System.out.println("Login efetuado com sucesso!");
            } else {
                System.out.println("CPF ou senha incorretos. Tente novamente.");
            }
        } while (!loginEfetuado);

        // Realiza um investimento com o saldo do cliente
        cliente.investir(100, 12);

        // Realiza um saque da conta corrente
        boolean saqueEfetuado = false;
        while (!saqueEfetuado) {
            System.out.print("Digite o valor do saque: ");
            double valor = scanner.nextDouble();
            if (contaCorrente.sacar(valor)) {
                System.out.println("Saque efetuado com sucesso. Novo saldo: " + contaCorrente.consultarSaldo());
                saqueEfetuado = true;
            } else {
                System.out.println("Saldo insuficiente para o saque. Digite um valor válido.");
            }
        }

        // Realiza um depósito na conta corrente
        System.out.print("Digite o valor do depósito: ");
        double valorDeposito = scanner.nextDouble();
        contaCorrente.depositar(valorDeposito);

        // Realiza uma transferência PIX para a conta destino
        System.out.print("Digite o valor da transferência PIX: ");
        double valorTransferenciaPIX = scanner.nextDouble();
        ContaCorrente contaDestino = new ContaCorrente(new Cliente("2", "4321", 0.0));
        if (contaCorrente.pix(valorTransferenciaPIX, contaDestino)) {
            System.out.printf("Transferência PIX realizada com sucesso. Novo saldo na conta corrente: %.2f.%n", contaCorrente.consultarSaldo());
        } else {
            System.out.println("Saldo insuficiente para a transferência PIX.");
        }

        // Realiza uma transferência da conta corrente para a conta poupança
        System.out.print("Digite o valor da transferência da conta corrente para a conta poupança: ");
        double valorTransferencia = scanner.nextDouble();
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
        while (!contaCorrente.transferir(valorTransferencia, contaPoupanca)) {
            System.out.println("Saldo insuficiente na conta corrente para a transferência.");
            System.out.print("Digite um valor válido: ");
            valorTransferencia = scanner.nextDouble();
        }
        System.out.printf("Transferência efetuada com sucesso. Novo saldo na conta corrente: %.2f. Novo saldo na poupança: %.2f. %n" , contaCorrente.consultarSaldo() , contaPoupanca.getSaldo());

        // Realiza um saque na conta poupança
        System.out.print("Digite o valor do saque na conta poupança: ");
        double valorSaquePoupanca = scanner.nextDouble();
        if (contaPoupanca.sacar(valorSaquePoupanca)) {
            System.out.println("Saque efetuado com sucesso. Novo saldo na poupança: " + contaPoupanca.getSaldo());
        } else {
            System.out.println("Saldo insuficiente na poupança para o saque.");
        }
        // Cria uma caixinha associada ao cliente
        Caixinha caixinha = new Caixinha(cliente);

        // Adiciona um valor à caixinha
        System.out.print("Digite o valor a ser adicionado na caixinha: ");
        double valorAdicionado = scanner.nextDouble();
        caixinha.adicionarValor(valorAdicionado);

        // Consulta o saldo da caixinha
        System.out.printf("Saldo na caixinha: %.2f.%n", caixinha.consultarSaldo());
    }
}