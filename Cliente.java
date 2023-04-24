public class Cliente {
    private String cpf;
    private String senha;
    private double saldo;

    public Cliente(String cpf, String senha, double saldo) {
        this.cpf = cpf;
        this.senha = senha;
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    // Objeto cliente tem acesso ao método investir, que recebe um valor que vai ser retirado do seu saldo, e
    // recebe também um valor em meses. Na conclusão com sucesso do método ele recebe uma mensagem com o valor
    // que investiu e o valor que teve de retorno, o qual é adicionado ao saldo.
    public void investir(double valorInvestido, int meses) {

        // Checa se o cliente tem saldo para efetuar o investimento, caso não, envia uma mensagem, mantém o
        // Cliente com o saldo dele e sai do método.
        if (this.saldo >= valorInvestido) {
            this.saldo -= valorInvestido;
        } else {
            System.out.println("Infelizmente o cliente de CPF " + this.cpf + " não tem saldo suficiente para investir esse valor.");
            return;
        }

        // Valor arbitrário de juros, corresponde a 10% ao mês
        double juros = 10;

        // Variável recebe a fórmula para juros composto
        double valorAcumulado = valorInvestido * (Math.pow((1 + juros / 100), meses));
        valorAcumulado = Math.round(valorAcumulado * 100.0) / 100.0;


        // Mensagens para informar os valores da movimentação
        System.out.println("O cliente de CPF " + this.cpf + " fez um investimento de R$" + valorInvestido + ".");
        System.out.println("Após " + meses + " mês(es), o investimento resultou em uma quantia de R$" + valorAcumulado + ".");

        // Soma o valor acumulado ao saldo do cliente
        this.saldo += valorAcumulado;
        System.out.println("O saldo atual do cliente é de R$" + this.saldo);

    }
}