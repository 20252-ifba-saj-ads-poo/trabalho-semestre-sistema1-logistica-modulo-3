package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.repository.ProdutoRepositorio;
import br.edu.ifba.saj.fwads.model.Produto;
import br.edu.ifba.saj.fwads.exception.EstoqueInsuficienteException;
import br.edu.ifba.saj.fwads.exception.ProdutoInvalidoException;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepositorio repositorio = new ProdutoRepositorio();

    public void cadastrar(String nome, String pesoStr, String volumeStr, String qtdStr)
            throws ProdutoInvalidoException {

        if (nome == null || nome.isBlank())
            throw new ProdutoInvalidoException("O nome do produto não pode ser vazio.");

        double peso;
        double volume;
        int quantidade;

        try {
            peso = Double.parseDouble(pesoStr);
        } catch (NumberFormatException e) {
            throw new ProdutoInvalidoException("Peso inválido. Use números como 1.5");
        }

        try {
            volume = Double.parseDouble(volumeStr);
        } catch (NumberFormatException e) {
            throw new ProdutoInvalidoException("Volume inválido. Use números como 0.02");
        }

        try {
            quantidade = Integer.parseInt(qtdStr);
        } catch (NumberFormatException e) {
            throw new ProdutoInvalidoException("Quantidade inválida. Use números inteiros.");
        }

        if (peso <= 0)
            throw new ProdutoInvalidoException("O peso deve ser maior que zero.");
        if (volume <= 0)
            throw new ProdutoInvalidoException("O volume deve ser maior que zero.");
        if (quantidade < 0)
            throw new ProdutoInvalidoException("A quantidade não pode ser negativa.");
        if (repositorio.buscarPorNome(nome) != null)
            throw new ProdutoInvalidoException("Já existe um produto com o nome \"" + nome + "\".");

        repositorio.adicionar(new Produto(nome, peso, volume, quantidade));
    }

    public void remover(String nome) throws ProdutoInvalidoException {
        if (nome == null || nome.isBlank())
            throw new ProdutoInvalidoException("Informe o nome do produto.");
        boolean removido = repositorio.remover(nome);
        if (!removido)
            throw new ProdutoInvalidoException("Produto \"" + nome + "\" não encontrado.");
    }

    public void darBaixa(String nome, int qtd)
            throws ProdutoInvalidoException, EstoqueInsuficienteException {

        Produto produto = repositorio.buscarPorNome(nome);
        if (produto == null)
            throw new ProdutoInvalidoException("Produto \"" + nome + "\" não encontrado.");
        if (qtd <= 0)
            throw new ProdutoInvalidoException("A quantidade deve ser maior que zero.");
        if (produto.getQuantidade() < qtd)
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente. Disponível: " + produto.getQuantidade() + ", solicitado: " + qtd);

        produto.setQuantidade(produto.getQuantidade() - qtd);
    }

    public List<Produto> listar() {
        return repositorio.listarTodos();
    }
}