package Services;

import Entities.Pedido;

public class CalcularImposto extends ProcessadorPedido {
    @Override
    public void processar(Pedido pedido) {
        System.out.println("Calculando impostos...");
        // lógica de imposto
        super.processar(pedido);
    }
}
