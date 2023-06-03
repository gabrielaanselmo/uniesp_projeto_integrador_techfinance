public class ContaCorrente {
    private Cliente cliente;

    public ContaCorrente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para sacar um valor da conta corrente
    public boolean sacar(double valor) {
        if (valor > cliente.getSaldo()) {
            return false;
        } else {
            double novoSaldo = cliente.getSaldo() - valor;
            cliente.setSaldo(novoSaldo);
            return true;
        }
    }

    // Método para consultar o saldo da conta corrente
    public double consultarSaldo() {
        return cliente.getSaldo();
    }

    // Método para depositar um valor na conta corrente
    public void depositar(double valor) {
        double novoSaldo = cliente.getSaldo() + valor;
        cliente.setSaldo(novoSaldo);
        System.out.println("Depósito efetuado com sucesso. Novo saldo: " + novoSaldo);
    }

    // Método para transferir um valor da conta corrente para uma conta poupança
    public boolean transferir(double valor, ContaPoupanca contaPoupanca) {
        if (valor > cliente.getSaldo()) {
            System.out.println("Saldo insuficiente para a transferência.");
            return false;
        } else {
            cliente.setSaldo(cliente.getSaldo() - valor);
            contaPoupanca.depositar(valor);
            return true;
        }
    }

    // Método para fazer um PIX da conta corrente para outra conta corrente
    public boolean pix(double valor, ContaCorrente contaDestino) {
        if (valor > cliente.getSaldo()) {
            System.out.println("Saldo insuficiente para a transferência.");
            return false;
        } else {
            cliente.setSaldo(cliente.getSaldo() - valor);
            contaDestino.depositar(valor);
            System.out.printf("Transferência realizada com sucesso. Novo saldo na conta: %.2f.%n", cliente.getSaldo());
            return true;
        }
    }
}