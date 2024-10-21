package Services;

import Entities.Pedido;

public abstract class ProcessadorPedido {
    protected ProcessadorPedido proximo;

    public void definirProximo(ProcessadorPedido proximo) {
        this.proximo = proximo;
    }

    public void processar(Pedido pedido) {
        if (proximo != null) {
            proximo.processar(pedido);
        }
    }
}
