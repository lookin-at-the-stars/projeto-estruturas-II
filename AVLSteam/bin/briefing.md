# 📊 Projeto: Ciência de Dados usando Árvores ABB e AVL

## 🧠 Aplicação 2 (Apl2)
**Atividade em grupo (mínimo 3 e máximo 4 alunos)**

---

## 🏗️ Contextualização

O Brasil, assim como outros países, está comprometido com os **Objetivos de Desenvolvimento Sustentável (ODS)** — uma agenda global que busca resolver desafios como:
- Erradicação da pobreza
- Saúde e bem-estar
- Educação de qualidade
- Igualdade de gênero, entre outros

O projeto visa **utilizar dados públicos (Kaggle)** para realizar uma **análise exploratória de dados**, aplicando **conceitos de Árvores Binárias de Busca (ABB)** e **Árvores AVL**.

---

## 🎯 Objetivos do Projeto

1. Escolher um dataset do Kaggle alinhado a um ODS.
2. Modelar e estruturar os dados em uma ABB e uma AVL (com ilustração).
3. Implementar operações sobre as árvores (inserção, busca, remoção e análise).
4. Comparar o desempenho entre ABB e AVL.
5. Criar gráficos para ilustrar os dados e resultados.
6. Refletir sobre desafios e aprendizados.
7. Elaborar um relatório completo com todos os resultados e conclusões.

---

## 🪜 Etapas do Projeto

### 1. Escolha do Dataset
- Acesse o site [Kaggle](https://www.kaggle.com/).
- Escolha um **dataset relacionado a um ODS** (Saúde, Educação, Igualdade de Gênero, etc.).
- Se o dataset tiver **mais de 200k linhas**, use **amostragem estratificada** e documente o critério.
- **Justifique** a escolha e explique o alinhamento com o ODS.

---

### 2. Modelagem dos Dados
- Planeje como **organizar os dados** (geralmente em formato CSV) nas árvores.
- Cada nó deve conter:
  - Uma **chave** (ex: ano, região, indicador)
  - **Valores associados** (ex: estatísticas, taxas, índices)
- Implemente:
  - Uma **ABB** (Árvore Binária de Busca)
  - Uma **AVL** (Árvore Binária Balanceada)
- Inclua **diagramas/ilustrações** mostrando a estrutura e inserção dos dados.

---

### 3. Operações de Análise de Dados

Implemente as seguintes operações:

#### 🔹 Inserção
Inserir novos dados (nós) na árvore.

#### 🔹 Busca
Buscar por chaves específicas ou por intervalos de valores.

#### 🔹 Remoção
Remover dados específicos solicitados pelo usuário.

#### 🔹 Análise Estatística
Criar funções que respondam perguntas exploratórias como:
- "Quantos eventos ocorreram entre 2015 e 2020?"
- "Qual o maior valor registrado em um determinado período?"

> 💡 **Dica:** Formule pelo menos **5 perguntas exploratórias** com base no dataset.

---

### 4. Comparação de Desempenho

#### Métricas por operação
Para cada operação (inserção, busca, remoção), registre:
- Número de comparações realizadas
- Número de rotações (apenas AVL)
- Tempo médio de execução (para busca, em 3 execuções)

#### Procedimento
- Execute as mesmas operações nas duas árvores (ABB e AVL)
- Use o **mesmo conjunto de dados** e a **mesma ordem**
- Se houver aleatoriedade, **fixe a seed** para reprodutibilidade

#### Apresentação dos resultados
- Utilize **gráficos e tabelas** com legendas explicativas para comparar desempenho

---

### 5. Documentação e Relatório Final

O relatório deve conter:

1. **Introdução**
   - Tema, contexto e importância dos ODS
2. **Descrição do Dataset**
   - Fonte, conteúdo e relação com o ODS
3. **Modelagem dos Dados**
   - Estrutura das árvores com diagramas
4. **Implementação**
   - Explicação das operações e suas funções
5. **Perguntas Exploratórias (mínimo 5)**
   - Questões e respostas baseadas na análise
6. **Comparação ABB × AVL**
   - Gráficos e discussão de desempenho
7. **Conclusões**
   - Eficácia das árvores, limitações e aprendizados
8. **Reflexão Final (individual)**
   - Aprendizado e desafios de cada integrante
9. **Referências**
   - Todas as fontes e links usados

---

## 💡 Exemplos de Perguntas Exploratórias

| ODS | Pergunta | Tipo de Análise |
|-----|-----------|----------------|
| **ODS 3 – Saúde e Bem-Estar** | Quais regiões apresentam maior incidência de doenças evitáveis nos últimos 5 anos? | Comparação entre regiões e faixas etárias |
| **ODS 4 – Educação de Qualidade** | Há relação entre evasão escolar e infraestrutura das escolas? | Correlação entre variáveis |
| **ODS 5 – Igualdade de Gênero** | Quais setores têm maior desequilíbrio de gênero em cargos de liderança? | Análise temporal e setorial |
| **ODS 6 – Água Potável e Saneamento** | Onde o acesso ao saneamento é mais limitado e como isso afeta a qualidade de vida? | Cruzamento de dados regionais |
| **ODS 13 – Ação Climática** | Quais regiões são mais suscetíveis a desastres naturais? | Análise de emissões e vulnerabilidade |

---

## 📦 Entregas e Apresentação

### 📅 Prazos
- **Entrega dos arquivos:** até **24/11 às 13h**
- **Apresentação:** dia **24/11**, no horário da aula

### 🧾 O que entregar
1. Código-fonte (`*.java`) — versões ABB e AVL, compactadas em `.zip`
2. Dataset utilizado (`.csv`)
3. Relatório final (`.docx`)
4. Vídeo de apresentação (`.mp4`, de 10 a 15 minutos)

> ⚠️ Apenas **um aluno** deve fazer o envio no Moodle.

### 🎤 Apresentação
- Todos os membros devem participar.
- A ausência de algum integrante implica **nota zero individual**.

---

## 🧮 Critérios de Avaliação

| Item de Avaliação | Pontuação Máxima |
|--------------------|------------------|
| Escolha do dataset alinhado aos ODS | 10 |
| Modelagem e Organização dos Dados (ABB e AVL) | 20 |
| Implementação das Operações sobre a Árvore | 20 |
| Comparação de Desempenho entre ABB e AVL | 15 |
| Testes e Validação | 10 |
| Relatório Final | 20 |
| Reflexão Final | 5 |
| **Total** | **100 pontos** |

---

## ⚠️ Penalidades

| Erro | Penalidade |
|------|-------------|
| Projeto copiado | Nota 0 |
| Programa não compila | Nota 0 |
| Ausência de referências | -1,0 |
| Arquivos incorretos | -1,0 |
| Falta de seções obrigatórias | -1,0 por seção |
| Código mal documentado | -1,0 |
| ABB ou AVL incorretas | -2,0 |
| Projeto fora da proposta (ODS) | -3,0 |
| Testes ausentes | -2,0 |
| Falta de gráficos/análises | -2,0 |
| Relatório com menos de 3 páginas | -1,5 |
| Atraso na entrega | -1,5 por dia |

---

## 🧾 Estrutura do Relatório (Template)

1. **Capa**
   - Título do Projeto  
   - Nome completo dos integrantes (ordem alfabética)  
   - Universidade, Disciplina e Professor

2. **Sumário**

3. **Introdução**
   - Breve apresentação e relevância dos ODS

4. **Escolha do Dataset**
   - Descrição, justificativa, link e fonte

5. **Modelagem dos Dados em Árvores**
   - Organização, chaves e diagramas ilustrativos

6. **Implementação das Operações**
   - Explicação das operações e possíveis trechos de código

7. **Comparação ABB × AVL**
   - Métricas, gráficos e discussão dos resultados

8. **Testes e Resultados**
   - Entradas, saídas e validações

9. **Gráficos e Análises de Resultados**

10. **Conclusões**
    - Síntese das descobertas e limitações

11. **Reflexão Final (individual)**
    - Aprendizados e desafios de cada integrante

12. **Referências**
    - Links, artigos, livros e dataset

---

## 📚 Referências Sugeridas

- AWARI. *Tudo sobre Ciência de Dados: o que é, como funciona e qual sua importância.* Fevereiro, 2022.  
  [https://awari.com.br/tudo-sobre-ciencia-de-dados/](https://awari.com.br/tudo-sobre-ciencia-de-dados/)  
  (Consultado em 22/10/2024)

---

## ✅ Resumo do que deve ser feito

1. Escolher um **dataset do Kaggle** relacionado a um **ODS**.  
2. Modelar os dados em **ABB** e **AVL**.  
3. Implementar **inserção, busca, remoção e análise estatística**.  
4. Comparar o **desempenho** das duas estruturas.  
5. Criar **gráficos** e responder **pelo menos 5 perguntas exploratórias**.  
6. Elaborar **relatório completo + vídeo de apresentação**.  
7. Garantir a **participação de todos os integrantes** na entrega e na apresentação.

---
