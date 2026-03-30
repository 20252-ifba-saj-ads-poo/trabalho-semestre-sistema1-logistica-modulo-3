package br.edu.ifba.saj.fwads.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import br.edu.ifba.saj.fwads.model.Produto;
import br.edu.ifba.saj.fwads.service.ProdutoService;
import br.edu.ifba.saj.fwads.exception.EstoqueInsuficienteException;
import br.edu.ifba.saj.fwads.exception.ProdutoInvalidoException;

public class ProdutoController {

    @FXML private TextField campoNome;
    @FXML private TextField campoPeso;
    @FXML private TextField campoVolume;
    @FXML private TextField campoQuantidade;
    @FXML private TextField campoBaixa;
    @FXML private ListView<Produto> listaProdutos;

    private final ProdutoService service = new ProdutoService();
    private final ObservableList<Produto> itensLista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaProdutos.setItems(itensLista);

        listaProdutos.setCellFactory(lv -> new ListCell<Produto>() {
            @Override
            protected void updateItem(Produto produto, boolean empty) {
                super.updateItem(produto, empty);
                if (empty || produto == null) {
                    setText(null);
                } else {
                    String detalhes = String.format("%.1fkg · %.3fm³ · %d un",
                            produto.getPeso(), produto.getVolume(), produto.getQuantidade());
                    setText(produto.getNome() + "\n" + detalhes);
                }
            }
        });
    }

    @FXML
    public void adicionarProduto() {
        try {
            service.cadastrar(
                campoNome.getText(),
                campoPeso.getText(),
                campoVolume.getText(),
                campoQuantidade.getText()
            );
            atualizarLista();
            limparCampos();
            mostrarSucesso("Produto cadastrado com sucesso!");
        } catch (ProdutoInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    @FXML
    public void removerProduto() {
        Produto selecionado = listaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarErro("Selecione um produto na lista para remover.");
            return;
        }
        try {
            service.remover(selecionado.getNome());
            atualizarLista();
            mostrarSucesso("Produto removido com sucesso!");
        } catch (ProdutoInvalidoException e) {
            mostrarErro(e.getMessage());
        }
    }

    @FXML
    public void darBaixa() {
        Produto selecionado = listaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarErro("Selecione um produto na lista para dar baixa.");
            return;
        }
        try {
            int qtd = Integer.parseInt(campoBaixa.getText());
            service.darBaixa(selecionado.getNome(), qtd);
            atualizarLista();
            campoBaixa.clear();
            mostrarSucesso("Baixa realizada com sucesso!");
        } catch (NumberFormatException e) {
            mostrarErro("Informe uma quantidade válida para baixa.");
        } catch (ProdutoInvalidoException | EstoqueInsuficienteException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void atualizarLista() {
        itensLista.setAll(service.listar());
    }

    private void limparCampos() {
        campoNome.clear();
        campoPeso.clear();
        campoVolume.clear();
        campoQuantidade.clear();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Algo deu errado");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}