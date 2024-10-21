package Entities;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<ItemPedido> itens;
    private double total;

    private Pedido(Builder builder) {
        this.itens = builder.itens;
        this.total = builder.total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public static class Builder {
        private List<ItemPedido> itens = new ArrayList<>();
        private double total = 0.0;

        public Builder adicionarItem(ItemPedido item) {
            itens.add(item);
            total += item.getPreco();
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }

    public void exibirPedido() {
        for (ItemPedido item : itens) {
            System.out.println(item.getNome() + " - R$ " + item.getPreco());
        }
        System.out.println("Total: R$ " + total);
    }
}
