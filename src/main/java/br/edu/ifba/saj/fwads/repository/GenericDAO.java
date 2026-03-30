package br.edu.ifba.saj.fwads.repository;

import java.util.List;

public abstract class GenericDAO<T> {

    public abstract void adicionar(T objeto);

    public abstract T buscarPorNome(String nome);

    public abstract boolean remover(String nome);

    public abstract List<T> listarTodos();
}
