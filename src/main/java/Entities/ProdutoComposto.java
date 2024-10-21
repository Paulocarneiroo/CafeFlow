package Entities;

import java.util.ArrayList;
import java.util.List;

public class ProdutoComposto extends ItemPedido {
    private List<ItemPedido> itens = new ArrayList<>();

    public ProdutoComposto(String nome) {
        this.nome = nome;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        this.preco += item.getPreco();
    }

    @Override
    public double getPreco() {
        return this.preco;
    }
}