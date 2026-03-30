package br.edu.ifba.saj.fwads.repository;

import br.edu.ifba.saj.fwads.model.Produto; //importando a classe Produto
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import br.edu.ifba.saj.fwads.repository.GenericDAO;

public class ProdutoRepositorio extends GenericDAO<Produto> {

   private final HashMap<String, Produto> mapa = new HashMap<>();

    public void adicionar(Produto produto) {
        if (produto != null && produto.getNome() != null) {
            mapa.put(produto.getNome(), produto);
        }
    }

    public List<Produto> listarTodos() {
        // Retorna uma nova lista com todos os valores do HashMap
        return new ArrayList<>(mapa.values());
    }

    public Produto buscarPorNome(String nome) {
        if (nome == null) {
            return null;
        }
        return mapa.get(nome);   // O get() do HashMap já faz a busca pela chave
    }

    public boolean remover(String nome) {
        if (nome == null) {
            return false;
        }
        // remove() retorna o valor removido ou null se não existia
        return mapa.remove(nome) != null;
    }


















}
