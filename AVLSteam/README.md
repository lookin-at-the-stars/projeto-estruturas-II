# 🎮 AVLSteam - Sistema de Análise de Dados do Steam

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![ODS 8](https://img.shields.io/badge/ODS%208-Trabalho%20Decente-red?style=flat)](https://brasil.un.org/pt-br/sdgs/8)

> Projeto acadêmico de Estruturas de Dados II - Análise de dados do Steam utilizando Árvores Binárias de Busca (ABB) e Árvores AVL

## 📋 Sobre o Projeto

Este projeto implementa um sistema completo de análise de dados sobre jogos da plataforma Steam, utilizando estruturas de dados avançadas (ABB e AVL) para organizar, buscar e analisar informações sobre popularidade, crescimento e tendências do mercado de games.

O trabalho está alinhado ao **ODS 8 - Trabalho Decente e Crescimento Econômico**, analisando o mercado de jogos digitais como um setor econômico relevante, que gera empregos e movimenta bilhões de dólares globalmente.

## 🎯 Objetivos Alcançados

✅ **Escolha do Dataset**: Dataset do Kaggle sobre estatísticas de jogadores do Steam  
✅ **Modelagem dos Dados**: Implementação completa de ABB e AVL com a classe `Game`  
✅ **Operações nas Árvores**: Inserção, busca, remoção com contadores de desempenho  
✅ **Análises Exploratórias**: 5 perguntas exploratórias respondidas usando as árvores  
✅ **Comparação de Desempenho**: Métricas detalhadas de ABB vs AVL  
✅ **Interface Interativa**: Sistema de menu completo para todas as operações  

## 📊 Dataset Utilizado

**Fonte**: [Steam Charts - Kaggle](https://www.kaggle.com/)  
**Conteúdo**: Estatísticas mensais de jogadores do Steam  
**Campos principais**:
- `Month`: Mês de referência (ex: Sep-25)
- `Avg. Players`: Média de jogadores simultâneos
- `Gain`: Ganho/perda de jogadores em relação ao mês anterior
- `% Gain`: Percentual de crescimento/decrescimento
- `Peak Players`: Pico máximo de jogadores simultâneos
- `Game Name`: Nome do jogo
- `Steam App ID`: Identificador único do jogo na plataforma

**Justificativa**: Este dataset permite analisar o mercado de games sob a perspectiva econômica (ODS 8), identificando tendências de crescimento, jogos de maior impacto comercial, distribuição de mercado e estabilidade da base de jogadores.

## 🏗️ Estrutura do Projeto

```
AVLSteam/
│
├── src/
│   ├── App.java                    # Aplicação principal com menu interativo
│   ├── Game.java                   # Classe modelo dos dados
│   ├── ABB.java                    # Implementação da Árvore Binária de Busca
│   ├── AVL.java                    # Implementação da Árvore AVL
│   ├── Node.java                   # Nó genérico para ABB
│   ├── NoAVL.java                  # Nó específico para AVL
│   ├── CSVReader.java              # Leitor e processador do arquivo CSV
│   ├── DataAnalyzer.java           # Análises exploratórias (5 perguntas)
│   ├── PerformanceAnalysis.java    # Comparação de desempenho ABB vs AVL
│   ├── Aluno.java                  # Informações dos integrantes
│   ├── steamcharts.csv             # Dataset
│   └── briefing.md                 # Briefing do projeto
│
├── bin/                            # Arquivos compilados (.class)
├── lib/                            # Dependências (se necessário)
└── README.md                       # Este arquivo
```

## 🔧 Modelagem dos Dados

### Estrutura de Nó

Cada nó das árvores armazena um objeto `Game` com os seguintes atributos:

```java
public class Game implements Comparable<Game> {
    private String month;           // Mês de referência
    private double avgPlayers;      // Média de jogadores (CHAVE DE ORDENAÇÃO)
    private double gain;            // Ganho absoluto de jogadores
    private double gainPercent;     // Percentual de crescimento
    private int peakPlayers;        // Pico de jogadores
    private String name;            // Nome do jogo
    private int steamAppid;         // ID único do Steam
}
```

**Critério de Comparação**: A chave de ordenação das árvores é a **média de jogadores** (`avgPlayers`), implementada no método `compareTo()`.

### Árvore Binária de Busca (ABB)

- **Características**:
  - Inserção simples sem balanceamento
  - Pode degenerar para lista ligada no pior caso
  - Busca, inserção e remoção: O(log n) médio, O(n) pior caso
  
- **Implementação**: Classe `ABB<E>` genérica com suporte a qualquer tipo `Comparable`

### Árvore AVL

- **Características**:
  - Auto-balanceamento após cada inserção/remoção
  - Mantém fator de balanceamento entre -1 e 1
  - Rotações simples e duplas para manter altura balanceada
  - Busca, inserção e remoção: O(log n) garantido
  
- **Implementação**: Classe `AVL` com métodos de rotação e atualização de alturas

## 🔍 Operações Implementadas

### 1. Inserção
- **ABB**: Inserção recursiva sem balanceamento
- **AVL**: Inserção com verificação de fator de balanceamento e rotações

### 2. Busca
- **ABB**: Busca binária padrão
- **AVL**: Busca binária otimizada pela altura balanceada

### 3. Remoção
- **ABB**: Remoção com 3 casos (folha, 1 filho, 2 filhos)
- **AVL**: Remoção com rebalanceamento subsequente

### 4. Percursos
- Em Ordem (In-Order)
- Em Nível (Level-Order / BFS)

## 📈 Análises Exploratórias (5 Perguntas)

Todas as análises utilizam as estruturas de árvores ABB e AVL, percorrendo-as para coletar e processar dados:

### 1️⃣ Jogos com Alta Performance
**Pergunta**: Quantos jogos têm média de jogadores acima de um determinado valor?  
**Método**: `analyzeHighPerformanceGames()`  
**Estrutura usada**: ABB (percurso em ordem)  
**Análise**: Identifica jogos de grande sucesso comercial e calcula engajamento total

### 2️⃣ Análise de Crescimento
**Pergunta**: Qual o crescimento percentual médio dos jogos?  
**Método**: `analyzeGrowthTrends()`  
**Estrutura usada**: AVL (percurso em ordem)  
**Análise**: Avalia tendências de crescimento/declínio do mercado

### 3️⃣ Picos Excepcionais
**Pergunta**: Quais jogos atingiram picos excepcionais de jogadores?  
**Método**: `analyzePeakPerformance()`  
**Estrutura usada**: ABB (busca recursiva)  
**Análise**: Identifica eventos especiais e viralizações

### 4️⃣ Distribuição de Mercado
**Pergunta**: Como está distribuída a popularidade dos jogos?  
**Método**: `analyzeMarketDistribution()`  
**Estrutura usada**: AVL (coleta balanceada)  
**Análise**: Avalia concentração vs diversidade de mercado (Curva de Pareto)

### 5️⃣ Estabilidade da Base de Jogadores
**Pergunta**: Quais jogos têm maior estabilidade (menor variação)?  
**Método**: `analyzePlayerStability()`  
**Estrutura usada**: ABB (agrupamento por nome)  
**Análise**: Identifica jogos com base leal vs volátil

## ⚡ Comparação de Desempenho: ABB vs AVL

O sistema implementa análises detalhadas de desempenho com as seguintes métricas:

### Métricas Coletadas

| Operação | ABB | AVL |
|----------|-----|-----|
| **Inserção** | Tempo (ms) | Tempo (ms) + Rotações |
| **Busca** | Tempo médio (3 exec.) | Tempo médio (3 exec.) |
| **Remoção** | Tempo (ms) | Tempo (ms) + Rotações |
| **Comparações** | Estimativa | Estimativa |

### Resultados Esperados

- **Inserção**: AVL é mais lenta devido às rotações, mas garante balanceamento
- **Busca**: AVL é consistentemente mais rápida (altura garantida O(log n))
- **Remoção**: AVL mantém desempenho previsível mesmo com grandes datasets

### Conclusões

✅ **AVL é superior para**:
- Datasets grandes (>1000 elementos)
- Operações de busca frequentes
- Quando previsibilidade de desempenho é crítica

✅ **ABB é suficiente para**:
- Datasets pequenos (<100 elementos)
- Dados já ordenados ou semi-ordenados
- Quando simplicidade de implementação é prioridade

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 11 ou superior
- VS Code (opcional, mas recomendado)
- Extensão Java Extension Pack (se usar VS Code)

### Compilação e Execução

1. **Clone o repositório** (se aplicável):
```bash
git clone <url-do-repositorio>
cd AVLSteam
```

2. **Compile todos os arquivos**:
```bash
javac -d bin src/*.java
```

3. **Execute o programa**:
```bash
java -cp bin App
```

### Menu Principal

```
════════════════ MENU PRINCIPAL ════════════════
1. Construir Árvores (ABB e AVL)
2. Inserir Novos Dados
3. Buscar Dados
4. Remover Dados
5. Realizar Análises Exploratórias (5 Perguntas)
6. Comparar Desempenho ABB vs AVL
7. Exibir Dados das Árvores
8. Visualizar Estatísticas do Dataset
0. Sair
═══════════════════════════════════════════════
```

### Fluxo Recomendado

1. Execute a opção **1** para construir as árvores
2. Execute a opção **5** para ver as análises exploratórias
3. Execute a opção **6** para comparar o desempenho
4. Experimente inserções, buscas e remoções (opções 2-4)
5. Visualize as árvores (opção 7)

## 📝 Casos de Teste

### Teste 1: Inserção de 10 Jogos
- Constrói árvores com 10 jogos de exemplo
- Verifica integridade estrutural
- Compara tempos de inserção

### Teste 2: Busca por Intervalo
- Busca jogos com média entre 5.000 e 15.000 jogadores
- Valida resultados em ambas as estruturas

### Teste 3: Remoção e Rebalanceamento
- Remove um jogo específico
- Verifica se AVL rebalanceia corretamente
- Confirma que ABB mantém estrutura válida

### Teste 4: Análises Completas
- Executa todas as 5 perguntas exploratórias
- Valida que os percursos funcionam corretamente
- Confirma que estatísticas são calculadas

## 📊 Relação com o ODS 8

**ODS 8 - Trabalho Decente e Crescimento Econômico**

Este projeto analisa o mercado de jogos digitais, que:

- 💼 **Gera milhões de empregos** (desenvolvedores, designers, streamers, e-sports)
- 💰 **Movimenta US$ 200+ bilhões** globalmente por ano
- 📈 **Apresenta crescimento consistente** (8-12% ao ano)
- 🌍 **É acessível globalmente** através de plataformas como Steam
- 🎓 **Incentiva inovação tecnológica** e desenvolvimento de habilidades

### Insights Econômicos do Projeto

1. **Concentração de Mercado**: Identifica se poucos jogos dominam vs mercado diversificado
2. **Tendências de Crescimento**: Mostra setores em expansão (oportunidades de trabalho)
3. **Estabilidade**: Games estáveis representam empregos sustentáveis
4. **Picos de Popularidade**: Indicam eventos que movimentam economia (atualizações, torneios)
5. **Distribuição Temporal**: Permite prever demandas sazonais de trabalho

## 👥 Integrantes do Projeto

Ana Luiza - 10297891
Arthur Torres - 10434401
Gabriel Barbosa - 10434547
Lucas Osório - 10434481

## 📚 Referências

- **Dataset**: [Steam Charts - Kaggle](https://www.kaggle.com/)
- **ODS 8**: [Objetivos de Desenvolvimento Sustentável - ONU](https://brasil.un.org/pt-br/sdgs/8)
- **Estruturas de Dados**: CORMEN, T. et al. *Introduction to Algorithms*. 3ª ed. MIT Press, 2009.
- **Árvores AVL**: ADELSON-VELSKY, G.; LANDIS, E. M. *An algorithm for the organization of information*. Soviet Mathematics Doklady, 1962.
- **Mercado de Games**: NEWZOO. *Global Games Market Report*. 2024.
- **Steam Platform**: [Steam Database](https://steamdb.info/) - Estatísticas da plataforma

## 📄 Licença

Este é um projeto acadêmico desenvolvido para fins educacionais.

## 🤝 Contribuições

Este projeto foi desenvolvido como atividade acadêmica. Sugestões e melhorias são bem-vindas através de issues ou pull requests.

---

**Estruturas de Dados II** | **Universidade Presbiteriana Mackenzie** | **2025**