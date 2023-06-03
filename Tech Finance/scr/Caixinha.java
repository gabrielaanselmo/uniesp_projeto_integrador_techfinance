import java.time.LocalDate;

public class Caixinha {
    private double saldo;
    private LocalDate dataUltimoRendimento;
    private Cliente cliente;

    public Caixinha(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.dataUltimoRendimento = LocalDate.now();
    }

    public void adicionarValor(double valor) {
        this.cliente.setSaldo(this.cliente.getSaldo() - valor);
        this.saldo += valor;
    }

    public double consultarSaldo() {
        atualizarRendimento();
        return saldo;
    }

    private void atualizarRendimento() {
        LocalDate hoje = LocalDate.now();
        int dias = hoje.getDayOfYear() - dataUltimoRendimento.getDayOfYear();
        if (dias > 0) {
            double rendimento = saldo * 0.001 * dias;
            saldo += rendimento;
            dataUltimoRendimento = hoje;
        }
    }
}