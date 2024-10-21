package Services;

import Entities.Pedido;

public class VerificarDisponibilidade extends ProcessadorPedido {
    @Override
    public void processar(Pedido pedido) {
        System.out.println("Verificando disponibilidade dos itens...");
        // lógica de verificação
        super.processar(pedido);
    }
}
