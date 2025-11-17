import java.util.*;

/**
 * Sistema de Análise de Dados do Steam
 * Projeto: Ciência de Dados usando Árvores ABB e AVL
 * ODS 8: Trabalho Decente e Crescimento Econômico
 * 
 * Este sistema analisa dados de jogos do Steam para entender
 * o mercado de games como setor econômico importante
 */
public class App {
    
    private static Scanner scanner = new Scanner(System.in);
    private static List<Game> allGames = new ArrayList<>();
    private static ABB<Game> abbTree = new ABB<>();
    private static AVL avlTree = new AVL();
    private static DataAnalyzer analyzer;
    private static boolean treesBuilt = false;
    
    public static void main(String[] args) throws Exception {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE ANÁLISE DE DADOS DO STEAM               ║");
        System.out.println("║  Estruturas de Dados II - ABB e AVL                  ║");
        System.out.println("║  ODS 8: Trabalho Decente e Crescimento Econômico    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
        
        // Carregar dados do CSV
        loadData();
        
        if (allGames.isEmpty()) {
            System.out.println("Erro: Não foi possível carregar os dados do CSV.");
            System.out.println("Certifique-se que o arquivo steamcharts.csv está no diretório src/");
            return;
        }
        
        // Menu principal
        boolean exit = false;
        while (!exit) {
            printMenu();
            int option = readOption();
            
            switch (option) {
                case 1:
                    buildTrees();
                    break;
                case 2:
                    insertData();
                    break;
                case 3:
                    searchData();
                    break;
                case 4:
                    removeData();
                    break;
                case 5:
                    performAnalyses();
                    break;
                case 6:
                    comparePerformance();
                    break;
                case 7:
                    displayTreeData();
                    break;
                case 8:
                    viewDatasetStats();
                    break;
                case 0:
                    exit = true;
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    private static void loadData() {
        System.out.println("Carregando dados do arquivo steamcharts.csv...");
        
        // Tentar diferentes caminhos
        String[] possiblePaths = {
            "src/steamcharts.csv",
            "steamcharts.csv",
            "./src/steamcharts.csv",
            "../src/steamcharts.csv"
        };
        
        for (String path : possiblePaths) {
            allGames = CSVReader.readGamesFromCSV(path);
            if (!allGames.isEmpty()) {
                System.out.println("Dados carregados com sucesso de: " + path);
                analyzer = new DataAnalyzer(allGames);
                return;
            }
        }
        
        System.out.println("Aviso: Arquivo CSV não encontrado. Usando dados de exemplo.");
        // Criar alguns dados de exemplo caso o CSV não seja encontrado
        createSampleData();
    }
    
    private static void createSampleData() {
        allGames.add(new Game("Sep-25", 7805.25, 883.12, 0.1276, 13254, "Counter-Strike", 10));
        allGames.add(new Game("Aug-25", 6922.13, -449.35, -0.061, 12168, "Counter-Strike", 10));
        allGames.add(new Game("Jul-25", 7371.48, -833.5, -0.1016, 13951, "Counter-Strike", 10));
        allGames.add(new Game("Sep-25", 25000.0, 2000.0, 0.087, 45000, "Dota 2", 570));
        allGames.add(new Game("Aug-25", 23000.0, -1500.0, -0.061, 42000, "Dota 2", 570));
        allGames.add(new Game("Sep-25", 15000.0, 500.0, 0.034, 28000, "PUBG", 578080));
        allGames.add(new Game("Aug-25", 14500.0, -200.0, -0.014, 27500, "PUBG", 578080));
        allGames.add(new Game("Sep-25", 8500.0, 300.0, 0.037, 16000, "Team Fortress 2", 440));
        allGames.add(new Game("Aug-25", 8200.0, -100.0, -0.012, 15800, "Team Fortress 2", 440));
        allGames.add(new Game("Sep-25", 5000.0, 200.0, 0.042, 9500, "Rust", 252490));
        
        System.out.println("Dados de exemplo criados: " + allGames.size() + " registros");
        analyzer = new DataAnalyzer(allGames);
    }
    
    private static void printMenu() {
        System.out.println("\n════════════════ MENU PRINCIPAL ════════════════");
        System.out.println("1. Construir Árvores (ABB e AVL)");
        System.out.println("2. Inserir Novos Dados");
        System.out.println("3. Buscar Dados");
        System.out.println("4. Remover Dados");
        System.out.println("5. Realizar Análises Exploratórias (5 Perguntas)");
        System.out.println("6. Comparar Desempenho ABB vs AVL");
        System.out.println("7. Exibir Dados das Árvores");
        System.out.println("8. Visualizar Estatísticas do Dataset");
        System.out.println("0. Sair");
        System.out.println("═══════════════════════════════════════════════");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int readOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar buffer
            return -1;
        } finally {
            scanner.nextLine(); // Limpar buffer
        }
    }
    
    private static void buildTrees() {
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("CONSTRUINDO ÁRVORES COM OS DADOS");
        System.out.println("═══════════════════════════════════════════════");
        
        // Reinicializar árvores
        abbTree = new ABB<>();
        avlTree = new AVL();
        
        System.out.println("Inserindo " + allGames.size() + " registros...\n");
        
        long startABB = System.nanoTime();
        for (Game game : allGames) {
            abbTree.inserir(game);
        }
        long endABB = System.nanoTime();
        
        long startAVL = System.nanoTime();
        for (Game game : allGames) {
            avlTree.insereAVL(game);
        }
        long endAVL = System.nanoTime();
        
        treesBuilt = true;
        
        System.out.println("✓ ABB construída com sucesso!");
        System.out.printf("  Tempo de inserção: %.3f ms\n", (endABB - startABB) / 1_000_000.0);
        
        System.out.println("\n✓ AVL construída com sucesso!");
        System.out.printf("  Tempo de inserção: %.3f ms\n", (endAVL - startAVL) / 1_000_000.0);
        
        System.out.println("\nÁrvores prontas para uso!");
        
        // Atualizar analyzer
        analyzer.setABBTree(abbTree);
        analyzer.setAVLTree(avlTree);
    }
    
    private static void insertData() {
        if (!treesBuilt) {
            System.out.println("\nAviso: Construa as árvores primeiro (opção 1).");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("INSERIR NOVO JOGO");
        System.out.println("═══════════════════════════════════════════════");
        
        try {
            System.out.print("Nome do jogo: ");
            String name = scanner.nextLine();
            
            System.out.print("Mês (ex: Sep-25): ");
            String month = scanner.nextLine();
            
            System.out.print("Média de jogadores: ");
            double avgPlayers = scanner.nextDouble();
            
            System.out.print("Ganho de jogadores: ");
            double gain = scanner.nextDouble();
            
            System.out.print("Percentual de ganho: ");
            double gainPercent = scanner.nextDouble();
            
            System.out.print("Pico de jogadores: ");
            int peakPlayers = scanner.nextInt();
            
            System.out.print("Steam App ID: ");
            int appId = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            Game newGame = new Game(month, avgPlayers, gain, gainPercent, peakPlayers, name, appId);
            
            // Inserir nas árvores
            long startABB = System.nanoTime();
            abbTree.inserir(newGame);
            long endABB = System.nanoTime();
            
            long startAVL = System.nanoTime();
            avlTree.insereAVL(newGame);
            long endAVL = System.nanoTime();
            
            allGames.add(newGame);
            
            System.out.println("\n✓ Jogo inserido com sucesso!");
            System.out.printf("  Tempo ABB: %.3f µs\n", (endABB - startABB) / 1000.0);
            System.out.printf("  Tempo AVL: %.3f µs\n", (endAVL - startAVL) / 1000.0);
            
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida!");
            scanner.nextLine(); // Limpar buffer
        }
    }
    
    private static void searchData() {
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("BUSCAR JOGOS");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("1. Buscar por nome");
        System.out.println("2. Buscar por intervalo de média de jogadores");
        System.out.print("Escolha: ");
        
        int option = readOption();
        
        switch (option) {
            case 1:
                System.out.print("Digite o nome (ou parte): ");
                String name = scanner.nextLine();
                List<Game> byName = analyzer.searchGamesByName(name);
                displaySearchResults(byName);
                break;
                
            case 2:
                try {
                    System.out.print("Média mínima: ");
                    double min = scanner.nextDouble();
                    System.out.print("Média máxima: ");
                    double max = scanner.nextDouble();
                    scanner.nextLine();
                    
                    List<Game> inRange = analyzer.searchGamesInRange(min, max);
                    displaySearchResults(inRange);
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Valores inválidos!");
                    scanner.nextLine();
                }
                break;
                
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void displaySearchResults(List<Game> results) {
        System.out.println("\n─────────────────────────────────────────────");
        System.out.println("RESULTADOS DA BUSCA: " + results.size() + " jogo(s) encontrado(s)");
        System.out.println("─────────────────────────────────────────────");
        
        if (results.isEmpty()) {
            System.out.println("Nenhum jogo encontrado.");
        } else {
            for (int i = 0; i < Math.min(20, results.size()); i++) {
                Game g = results.get(i);
                System.out.printf("%d. %s\n", i+1, g);
            }
            
            if (results.size() > 20) {
                System.out.println("\n... e mais " + (results.size() - 20) + " resultados.");
            }
        }
    }
    
    private static void removeData() {
        if (!treesBuilt) {
            System.out.println("\nAviso: Construa as árvores primeiro (opção 1).");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("REMOVER JOGO");
        System.out.println("═══════════════════════════════════════════════");
        
        System.out.print("Digite a média de jogadores do jogo a remover: ");
        try {
            double avgPlayers = scanner.nextDouble();
            scanner.nextLine();
            
            // Buscar jogo com essa média
            Game toRemove = null;
            for (Game g : allGames) {
                if (Math.abs(g.getAvgPlayers() - avgPlayers) < 0.01) {
                    toRemove = g;
                    break;
                }
            }
            
            if (toRemove == null) {
                System.out.println("Jogo não encontrado com essa média!");
                return;
            }
            
            System.out.println("Jogo encontrado: " + toRemove);
            System.out.print("Confirma remoção? (S/N): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("S")) {
                long startABB = System.nanoTime();
                boolean removedABB = abbTree.eliminar(toRemove);
                long endABB = System.nanoTime();
                
                long startAVL = System.nanoTime();
                boolean removedAVL = avlTree.removeAVL(toRemove);
                long endAVL = System.nanoTime();
                
                if (removedABB && removedAVL) {
                    allGames.remove(toRemove);
                    System.out.println("\n✓ Jogo removido com sucesso!");
                    System.out.printf("  Tempo ABB: %.3f µs\n", (endABB - startABB) / 1000.0);
                    System.out.printf("  Tempo AVL: %.3f µs\n", (endAVL - startAVL) / 1000.0);
                } else {
                    System.out.println("Erro ao remover jogo!");
                }
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido!");
            scanner.nextLine();
        }
    }
    
    private static void performAnalyses() {
        System.out.println("\nIniciando análises exploratórias...\n");
        analyzer.performAllAnalyses();
        
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    private static void comparePerformance() {
        if (!treesBuilt) {
            System.out.println("\nAviso: Construa as árvores primeiro (opção 1).");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("COMPARAÇÃO DE DESEMPENHO: ABB vs AVL");
        System.out.println("═══════════════════════════════════════════════\n");
        
        // Teste de inserção
        System.out.println("Teste 1: INSERÇÃO");
        PerformanceAnalysis.Metrics abbInsert = PerformanceAnalysis.analyzeABBInsertion(allGames);
        PerformanceAnalysis.Metrics avlInsert = PerformanceAnalysis.analyzeAVLInsertion(allGames);
        PerformanceAnalysis.comparePerformance(abbInsert, avlInsert, "INSERÇÃO");
        
        // Teste de busca
        System.out.println("\nTeste 2: BUSCA");
        List<Game> searchSample = allGames.subList(0, Math.min(100, allGames.size()));
        PerformanceAnalysis.Metrics abbSearch = PerformanceAnalysis.analyzeABBSearch(abbTree, searchSample);
        PerformanceAnalysis.Metrics avlSearch = PerformanceAnalysis.analyzeAVLSearch(avlTree, searchSample);
        PerformanceAnalysis.comparePerformance(abbSearch, avlSearch, "BUSCA");
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("CONCLUSÃO:");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("A AVL mantém balanceamento garantindo O(log n)");
        System.out.println("para todas as operações, enquanto a ABB pode");
        System.out.println("degenerar para O(n) no pior caso.");
        System.out.println("═══════════════════════════════════════════════\n");
        
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    private static void displayTreeData() {
        if (!treesBuilt) {
            System.out.println("\nAviso: Construa as árvores primeiro (opção 1).");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("VISUALIZAÇÃO DAS ÁRVORES");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("1. Exibir ABB (em ordem)");
        System.out.println("2. Exibir AVL (em ordem)");
        System.out.println("3. Exibir ABB (em nível)");
        System.out.println("4. Exibir AVL (em nível)");
        System.out.print("Escolha: ");
        
        int option = readOption();
        
        System.out.println("\n─────────────────────────────────────────────");
        switch (option) {
            case 1:
                System.out.println("ABB - Percurso Em Ordem:");
                abbTree.emOrdem();
                System.out.println();
                break;
            case 2:
                System.out.println("AVL - Percurso Em Ordem:");
                System.out.println(avlTree.emOrdemString());
                break;
            case 3:
                System.out.println("ABB - Percurso Em Nível:");
                abbTree.emNivel();
                System.out.println();
                break;
            case 4:
                System.out.println("AVL - Percurso Em Nível:");
                System.out.println(avlTree.emNivelString());
                break;
            default:
                System.out.println("Opção inválida!");
        }
        System.out.println("─────────────────────────────────────────────");
    }
    
    private static void viewDatasetStats() {
        System.out.println();
        CSVReader.displayDatasetStats(allGames);
        
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
