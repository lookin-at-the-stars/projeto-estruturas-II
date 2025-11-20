import java.util.*;

/**
 * Classe para realizar análises exploratórias sobre os dados de jogos do Steam
 * Alinhado ao ODS 8 - Trabalho Decente e Crescimento Econômico
 * Analisa o mercado de games e seu impacto econômico
 * 
 * IMPORTANTE: Todas as 5 perguntas exploratórias utilizam as estruturas
 * de árvores ABB e AVL, percorrendo-as para realizar as análises
 */
public class DataAnalyzer {
    
    private List<Game> allGames;
    private ABB<Game> abbTree;
    private AVL avlTree;
    
    public DataAnalyzer(List<Game> games) {
        this.allGames = games;
        this.abbTree = null;
        this.avlTree = null;
    }
    
    public void setABBTree(ABB<Game> abb) {
        this.abbTree = abb;
    }
    
    public void setAVLTree(AVL avl) {
        this.avlTree = avl;
    }
    
    /**
     * PERGUNTA 1: Quantos jogos têm média de jogadores acima de um determinado valor?
     * Análise: Identifica jogos de grande sucesso comercial
     * EXPLORA: Percorre a ABB em ordem para coletar dados
     */
    public void analyzeHighPerformanceGames(double threshold) {
        System.out.println("\n========================================");
        System.out.println("PERGUNTA 1: Jogos com Alta Performance");
        System.out.println("========================================");
        System.out.printf("Analisando jogos com média acima de %.2f jogadores\n", threshold);
        System.out.println("Usando ABB para percorrer os dados...\n");
        
        // Coletar dados percorrendo a ABB em ordem
        List<Game> highPerformers = new ArrayList<>();
        double totalRevenue = 0;
        
        if (abbTree != null && abbTree.getRaiz() != null) {
            collectHighPerformers(abbTree.getRaiz(), threshold, highPerformers);
            
            for (Game g : highPerformers) {
                totalRevenue += g.getAvgPlayers();
            }
        } else {
            System.out.println("AVISO: ABB não construída, usando lista direta.");
            for (Game game : allGames) {
                if (game.getAvgPlayers() > threshold) {
                    highPerformers.add(game);
                    totalRevenue += game.getAvgPlayers();
                }
            }
        }
        
        System.out.println("Total de jogos acima do limiar: " + highPerformers.size());
        System.out.printf("Percentual: %.2f%%\n", (highPerformers.size() * 100.0 / allGames.size()));
        System.out.printf("Estimativa de engajamento total: %.2f\n", totalRevenue);
        
        // Top 5 jogos com nomes únicos
        highPerformers.sort((g1, g2) -> Double.compare(g2.getAvgPlayers(), g1.getAvgPlayers()));
        
        System.out.println("\n📝 NOTA: O dataset contém múltiplos registros do mesmo jogo");
        System.out.println("(um para cada mês). Mostrando apenas jogos com nomes únicos:\n");
        
        System.out.println("Top 5 jogos mais populares (nomes únicos):");
        Set<String> uniqueNames = new HashSet<>();
        int count = 0;
        for (Game g : highPerformers) {
            if (!uniqueNames.contains(g.getName())) {
                uniqueNames.add(g.getName());
                count++;
                System.out.printf("%d. %s - %.2f jogadores (Pico: %d) - Mês: %s\n", 
                                count, g.getName(), g.getAvgPlayers(), g.getPeakPlayers(), g.getMonth());
                if (count >= 5) break;
            }
        }
        System.out.println("========================================\n");
    }
    
    // Método auxiliar para percorrer ABB e coletar jogos acima do threshold
    private void collectHighPerformers(Node<Game> node, double threshold, List<Game> result) {
        if (node != null) {
            collectHighPerformers(node.getFilhoEsq(), threshold, result);
            
            Game game = node.getValue();
            if (game.getAvgPlayers() > threshold) {
                result.add(game);
            }
            
            collectHighPerformers(node.getFilhoDir(), threshold, result);
        }
    }
    
    /**
     * PERGUNTA 2: Qual o crescimento percentual médio dos jogos em um período?
     * Análise: Avalia o crescimento do mercado de games
     * EXPLORA: Percorre a AVL em ordem para calcular estatísticas
     */
    public void analyzeGrowthTrends() {
        System.out.println("\n========================================");
        System.out.println("PERGUNTA 2: Análise de Crescimento");
        System.out.println("========================================");
        System.out.println("Usando AVL para percorrer os dados...\n");
        
        GrowthStats stats = new GrowthStats();
        
        if (avlTree != null && avlTree.getRaiz() != null) {
            analyzeGrowthAVL(avlTree.getRaiz(), stats);
        } else {
            System.out.println("AVISO: AVL não construída, usando lista direta.");
            for (Game game : allGames) {
                processGrowthData(game, stats);
            }
        }
        
        double avgGrowth = stats.totalGrowth / stats.totalGames;
        
        System.out.printf("Crescimento médio: %.2f%%\n", avgGrowth * 100);
        System.out.printf("Jogos em crescimento: %d (%.2f%%)\n", 
                        stats.positiveGrowth, (stats.positiveGrowth * 100.0 / stats.totalGames));
        System.out.printf("Jogos em declínio: %d (%.2f%%)\n", 
                        stats.negativeGrowth, (stats.negativeGrowth * 100.0 / stats.totalGames));
        System.out.printf("\nMaior crescimento: %.2f%% - %s\n", stats.maxGrowth * 100, stats.fastestGrowing);
        System.out.printf("Maior declínio: %.2f%% - %s\n", stats.minGrowth * 100, stats.fastestDeclining);
        System.out.println("========================================\n");
    }
    
    // Classe auxiliar para estatísticas de crescimento
    private static class GrowthStats {
        double totalGrowth = 0;
        int positiveGrowth = 0;
        int negativeGrowth = 0;
        double maxGrowth = Double.MIN_VALUE;
        double minGrowth = Double.MAX_VALUE;
        String fastestGrowing = "";
        String fastestDeclining = "";
        int totalGames = 0;
    }
    
    // Percorre AVL para analisar crescimento
    private void analyzeGrowthAVL(NoAVL node, GrowthStats stats) {
        if (node != null) {
            analyzeGrowthAVL(node.getEsq(), stats);
            
            Game game = (Game) node.getDado();
            processGrowthData(game, stats);
            
            analyzeGrowthAVL(node.getDir(), stats);
        }
    }
    
    private void processGrowthData(Game game, GrowthStats stats) {
        double growth = game.getGainPercent();
        stats.totalGrowth += growth;
        stats.totalGames++;
        
        if (growth > 0) stats.positiveGrowth++;
        if (growth < 0) stats.negativeGrowth++;
        
        if (growth > stats.maxGrowth) {
            stats.maxGrowth = growth;
            stats.fastestGrowing = game.getName() + " (" + game.getMonth() + ")";
        }
        
        if (growth < stats.minGrowth) {
            stats.minGrowth = growth;
            stats.fastestDeclining = game.getName() + " (" + game.getMonth() + ")";
        }
    }
    
    /**
     * PERGUNTA 3: Quais jogos atingiram picos excepcionais de jogadores?
     * Análise: Identifica eventos especiais e viralizações
     * EXPLORA: Usa busca na ABB para encontrar jogos com picos altos
     */
    public void analyzePeakPerformance(int peakThreshold) {
        System.out.println("\n========================================");
        System.out.println("PERGUNTA 3: Análise de Picos de Jogadores");
        System.out.println("========================================");
        System.out.printf("Analisando jogos com pico acima de %d jogadores\n", peakThreshold);
        System.out.println("Explorando ABB para buscar picos excepcionais...\n");
        
        List<Game> exceptionalPeaks = new ArrayList<>();
        
        if (abbTree != null && abbTree.getRaiz() != null) {
            collectExceptionalPeaks(abbTree.getRaiz(), peakThreshold, exceptionalPeaks);
        } else {
            System.out.println("AVISO: ABB não construída, usando lista direta.");
            for (Game game : allGames) {
                if (game.getPeakPlayers() > peakThreshold) {
                    exceptionalPeaks.add(game);
                }
            }
        }
        
        exceptionalPeaks.sort((g1, g2) -> Integer.compare(g2.getPeakPlayers(), g1.getPeakPlayers()));
        
        System.out.println("Total de registros com picos excepcionais: " + exceptionalPeaks.size());
        
        System.out.println("\n📝 NOTA: O dataset contém múltiplos registros do mesmo jogo");
        System.out.println("(um para cada mês). Mostrando apenas jogos com nomes únicos:");
        System.out.println("Isso permite ver a DIVERSIDADE de jogos que atingiram picos altos.\n");
        
        System.out.println("Top 10 maiores picos (jogos únicos):");
        Set<String> uniqueNames = new HashSet<>();
        int count = 0;
        for (Game g : exceptionalPeaks) {
            if (!uniqueNames.contains(g.getName())) {
                uniqueNames.add(g.getName());
                count++;
                double peakRatio = g.getPeakPlayers() / g.getAvgPlayers();
                System.out.printf("%d. %s - Pico: %d jogadores (%.1fx a média) - %s\n", 
                                count, g.getName(), g.getPeakPlayers(), peakRatio, g.getMonth());
                if (count >= 10) break;
            }
        }
        System.out.println("========================================\n");
    }
    
    // Percorre ABB coletando jogos com picos excepcionais
    private void collectExceptionalPeaks(Node<Game> node, int threshold, List<Game> result) {
        if (node != null) {
            collectExceptionalPeaks(node.getFilhoEsq(), threshold, result);
            
            Game game = node.getValue();
            if (game.getPeakPlayers() > threshold) {
                result.add(game);
            }
            
            collectExceptionalPeaks(node.getFilhoDir(), threshold, result);
        }
    }
    
    /**
     * PERGUNTA 4: Como está distribuída a popularidade dos jogos?
     * Análise: Avalia concentração de mercado vs diversidade
     * EXPLORA: Percorre AVL (balanceada) para calcular distribuição
     */
    public void analyzeMarketDistribution() {
        System.out.println("\n========================================");
        System.out.println("PERGUNTA 4: Distribuição de Mercado");
        System.out.println("========================================");
        System.out.println("Usando AVL balanceada para coletar dados de mercado...\n");
        
        List<Game> allGamesFromTree = new ArrayList<>();
        
        // Coletar todos os jogos da AVL
        if (avlTree != null && avlTree.getRaiz() != null) {
            collectAllGamesAVL(avlTree.getRaiz(), allGamesFromTree);
        } else {
            System.out.println("AVISO: AVL não construída, usando lista direta.");
            allGamesFromTree = new ArrayList<>(allGames);
        }
        
        // Calcular total de jogadores
        double totalPlayers = 0;
        for (Game game : allGamesFromTree) {
            totalPlayers += game.getAvgPlayers();
        }
        
        // Ordenar por popularidade
        allGamesFromTree.sort((g1, g2) -> Double.compare(g2.getAvgPlayers(), g1.getAvgPlayers()));
        
        // Calcular percentuais acumulados
        double top10Percent = 0;
        double top25Percent = 0;
        double top50Percent = 0;
        
        int top10Index = (int)(allGamesFromTree.size() * 0.1);
        int top25Index = (int)(allGamesFromTree.size() * 0.25);
        int top50Index = (int)(allGamesFromTree.size() * 0.5);
        
        for (int i = 0; i < allGamesFromTree.size(); i++) {
            double players = allGamesFromTree.get(i).getAvgPlayers();
            
            if (i < top10Index) top10Percent += players;
            if (i < top25Index) top25Percent += players;
            if (i < top50Index) top50Percent += players;
        }
        
        System.out.printf("Total de jogadores analisados: %.2f\n\n", totalPlayers);
        System.out.printf("Top 10%% dos jogos representam: %.2f%% dos jogadores\n", 
                        (top10Percent / totalPlayers) * 100);
        System.out.printf("Top 25%% dos jogos representam: %.2f%% dos jogadores\n", 
                        (top25Percent / totalPlayers) * 100);
        System.out.printf("Top 50%% dos jogos representam: %.2f%% dos jogadores\n", 
                        (top50Percent / totalPlayers) * 100);
        
        System.out.println("\nInterpretação:");
        if ((top10Percent / totalPlayers) > 0.5) {
            System.out.println("- Mercado altamente concentrado em poucos jogos");
        } else {
            System.out.println("- Mercado com boa distribuição entre os jogos");
        }
        System.out.println("========================================\n");
    }
    
    // Coleta todos os jogos da AVL em ordem
    private void collectAllGamesAVL(NoAVL node, List<Game> result) {
        if (node != null) {
            collectAllGamesAVL(node.getEsq(), result);
            result.add((Game) node.getDado());
            collectAllGamesAVL(node.getDir(), result);
        }
    }
    
    /**
     * PERGUNTA 5: Quais jogos têm maior estabilidade (menor variação)?
     * Análise: Identifica jogos com base de jogadores leal vs volátil
     * EXPLORA: Usa busca na ABB para agrupar jogos por nome
     */
    public void analyzePlayerStability() {
        System.out.println("\n========================================");
        System.out.println("PERGUNTA 5: Estabilidade da Base de Jogadores");
        System.out.println("========================================");
        System.out.println("Percorrendo ABB para agrupar dados temporais...\n");
        
        // Agrupar jogos por nome para analisar variação temporal
        Map<String, List<Game>> gamesByName = new HashMap<>();
        
        if (abbTree != null && abbTree.getRaiz() != null) {
            groupGamesByName(abbTree.getRaiz(), gamesByName);
        } else {
            System.out.println("AVISO: ABB não construída, usando lista direta.");
            for (Game game : allGames) {
                gamesByName.computeIfAbsent(game.getName(), k -> new ArrayList<>()).add(game);
            }
        }
        
        List<GameStabilityInfo> stabilityList = new ArrayList<>();
        
        for (Map.Entry<String, List<Game>> entry : gamesByName.entrySet()) {
            if (entry.getValue().size() > 1) { // Precisa de pelo menos 2 pontos de dados
                String gameName = entry.getKey();
                List<Game> gameHistory = entry.getValue();
                
                double avgPlayers = 0;
                double maxVariation = 0;
                
                for (Game g : gameHistory) {
                    avgPlayers += g.getAvgPlayers();
                }
                avgPlayers /= gameHistory.size();
                
                // Calcular variação máxima
                for (Game g : gameHistory) {
                    double variation = Math.abs((g.getAvgPlayers() - avgPlayers) / avgPlayers);
                    if (variation > maxVariation) {
                        maxVariation = variation;
                    }
                }
                
                stabilityList.add(new GameStabilityInfo(gameName, avgPlayers, maxVariation));
            }
        }
        
        // Ordenar por estabilidade (menor variação)
        stabilityList.sort((s1, s2) -> Double.compare(s1.variation, s2.variation));
        
        System.out.println("Jogos mais estáveis (menor variação):");
        for (int i = 0; i < Math.min(10, stabilityList.size()); i++) {
            GameStabilityInfo info = stabilityList.get(i);
            System.out.printf("%d. %s - Variação: %.2f%% - Média: %.2f jogadores\n", 
                            i+1, info.name, info.variation * 100, info.avgPlayers);
        }
        
        System.out.println("\nJogos mais voláteis (maior variação):");
        for (int i = stabilityList.size() - 1; i >= Math.max(0, stabilityList.size() - 10); i--) {
            GameStabilityInfo info = stabilityList.get(i);
            System.out.printf("%d. %s - Variação: %.2f%% - Média: %.2f jogadores\n", 
                            stabilityList.size() - i, info.name, info.variation * 100, info.avgPlayers);
        }
        System.out.println("========================================\n");
    }
    
    // Percorre ABB agrupando jogos por nome
    private void groupGamesByName(Node<Game> node, Map<String, List<Game>> gamesByName) {
        if (node != null) {
            groupGamesByName(node.getFilhoEsq(), gamesByName);
            
            Game game = node.getValue();
            gamesByName.computeIfAbsent(game.getName(), k -> new ArrayList<>()).add(game);
            
            groupGamesByName(node.getFilhoDir(), gamesByName);
        }
    }
    
    /**
     * Realiza todas as análises exploratórias
     */
    public void performAllAnalyses() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   ANÁLISES EXPLORATÓRIAS - STEAM GAMES   ║");
        System.out.println("║   Alinhado ao ODS 8: Crescimento          ║");
        System.out.println("║   Econômico e Trabalho Decente            ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        if (abbTree == null || avlTree == null) {
            System.out.println("\n⚠️  AVISO: Árvores não construídas!");
            System.out.println("Execute a opção 1 do menu primeiro para construir as árvores.\n");
            return;
        }
        
        System.out.println("\nUtilizando estruturas de dados ABB e AVL para análises...\n");
        
        analyzeHighPerformanceGames(5000.0);
        analyzeGrowthTrends();
        analyzePeakPerformance(10000);
        analyzeMarketDistribution();
        analyzePlayerStability();
    }
    
    /**
     * Busca jogos em um intervalo de média de jogadores
     */
    public List<Game> searchGamesInRange(double minAvg, double maxAvg) {
        List<Game> result = new ArrayList<>();
        
        for (Game game : allGames) {
            if (game.getAvgPlayers() >= minAvg && game.getAvgPlayers() <= maxAvg) {
                result.add(game);
            }
        }
        
        return result;
    }
    
    /**
     * Busca jogos por nome (busca parcial)
     */
    public List<Game> searchGamesByName(String namePattern) {
        List<Game> result = new ArrayList<>();
        String pattern = namePattern.toLowerCase();
        
        for (Game game : allGames) {
            if (game.getName().toLowerCase().contains(pattern)) {
                result.add(game);
            }
        }
        
        return result;
    }
    
    // Classe auxiliar para armazenar informações de estabilidade
    private static class GameStabilityInfo {
        String name;
        double avgPlayers;
        double variation;
        
        GameStabilityInfo(String name, double avgPlayers, double variation) {
            this.name = name;
            this.avgPlayers = avgPlayers;
            this.variation = variation;
        }
    }
}
