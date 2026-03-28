### 📝 Resumo e Nota (OK)
- **Nota Final:** 7/100
- **Visão Geral:** O trabalho submetido contém estritamente o código base (boilerplate) fornecido no início da disciplina focado em "Autores e Livros", acompanhado de uma classe script genérica `GestaoEstoque` construída de forma irregular (com erros de sintaxe graves e de formatação estrutural no Java). Nenhuma implementação coerente referente à arquitetura MVCS do Módulo 3 foi entregue.

### 📊 Detalhamento do Barema
- **[5/20] Interface Gráfica:** **⚠️ ALERTA DE REGRA CRÍTICA PARA BOILERPLATE APLICADA (Máx. 5 pts):** A estrutura contém apenas as telas isoladas do código base (`CadAutor`, `CadLivro`, `Login` com admin estático). Nada do escopo exigido de Logística para o módulo foi desenvolvido.
- **[0/30] Camada de Negócio:** **0/30.** **⚠️ ALERTA DE REGRA CRÍTICA:** Nenhuma Service das entidades exigidas do domínio foi desenvolvida, tampouco a herança obrigatória de Service e Exceptions foi apresentada.
- **[0/20] Camada de Dados (Repository/Modelagem):** **0/20.** **⚠️ ALERTA DE REGRA CRÍTICA:** As entidades base continuaram sendo `Autor` e `Livro` num mock list. Modelos que justificam a logística modular inexistiram e as abstrações obrigatórias (`AbstractModel` e `GenericDAO`) foram desconsideradas completamente.
- **[0/20] Separação em Camadas:** **0/20.** **⚠️ ALERTA DE REGRA CRÍTICA:** Ausência do desenvolvimento da arquitetura para a proposta. Todas as ações do código ocorrem diretamente nos metódos ActionEvent do JavaFX e na tentativa de script do `GestaoEstoque` agrupada de forma desorganizada.
- **[2/10] Boas Práticas e POO:** **2/10.** **⚠️ ALERTA DE REGRA CRÍTICA:** A pontuação restringe-se apenas à estruturação residual das referências herdadas da base do professor, pois a classe criada isoladamente pela equipe (`GestaoEstoque`) apresenta erros semânticos crassos contra princípios de POO (como `static Produto { ... }` dentro de método sem encapusalmento ou getters/setters).

### 🐛 Erros Lógicos, Arquiteturais e Execução
- Sintaxe Java Inválida: Em `GestaoEstoque.java`, criar estruturação referencial de classe (`static Produto { String nome; ...}`) no meio do método `main` e sem classes formatadas rompe totalmente as regras de linguagem. O arquivo acusa erro de compilação.
- Nenhum escopo MVC instanciado para o seu respectivo módulo. Autenticação simulada no controller de login.

### 💡 Refatoração / Código
**Problema Grave em `GestaoEstoque.java`:**
Este tipo de pseudo-código (como `static Produto { ... }` dentro de `main`) não pertence a métodos nem a classes do C# e Java. Classes devem ser arquivos ou escopos estruturados na raiz. O correto para seu modelo de `Produto` seria no seu próprio pacote `model`:

```java
// no pacote model
public class Produto extends AbstractModel<UUID> {
    private String nome;
    private double peso;
    private double volume;
    private String categoria;
    private int quantidade;

    public Produto(String nome, double peso, double volume, String categoria) {
        this.nome = nome;
        this.peso = peso;
        this.volume = volume;
        this.categoria = categoria;
        this.quantidade = 0;
    }

    // Getters e Setters
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}
```

E não utilizar classes `main()` para a gestão final de regras; vocês deveriam repassar o preenchimento dos Estoques em uma base `ProdutoService` isolada que faria as invocações de registro na interface em resposta ao JavaFX (Apresentação).
