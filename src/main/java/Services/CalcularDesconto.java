package Services;

import Entities.Pedido;

public class CalcularDesconto extends ProcessadorPedido {
    @Override
    public void processar(Pedido pedido) {
        System.out.println("Aplicando descontos...");
        // lógica de desconto
        super.processar(pedido);
    }
}