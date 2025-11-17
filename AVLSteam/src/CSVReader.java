import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    /**
     * Lê o arquivo CSV e retorna uma lista de objetos Game
     * @param filePath Caminho do arquivo CSV
     * @return Lista de jogos
     */
    public static List<Game> readGamesFromCSV(String filePath) {
        List<Game> games = new ArrayList<>();
        String line;
        boolean isFirstLine = true;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Pular o cabeçalho
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // Dividir a linha por vírgula
                String[] values = line.split(",");
                
                // Verificar se a linha tem todos os campos necessários
                if (values.length >= 7) {
                    try {
                        String month = values[0].trim();
                        double avgPlayers = parseDouble(values[1]);
                        double gain = parseDouble(values[2]);
                        double gainPercent = parseDouble(values[3]);
                        int peakPlayers = parseInt(values[4]);
                        String name = values[5].trim();
                        int steamAppid = parseInt(values[6]);
                        
                        Game game = new Game(month, avgPlayers, gain, gainPercent, 
                                           peakPlayers, name, steamAppid);
                        games.add(game);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao processar linha: " + line);
                        // Continua processando as outras linhas
                    }
                }
            }
            
            System.out.println("Total de jogos carregados: " + games.size());
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Converte string para double, tratando valores vazios
     */
    private static double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
    
    /**
     * Converte string para int, tratando valores vazios
     */
    private static int parseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value.trim());
    }
    
    /**
     * Lê uma amostra do CSV (primeiros N registros)
     * @param filePath Caminho do arquivo CSV
     * @param sampleSize Número de registros a ler
     * @return Lista de jogos
     */
    public static List<Game> readSample(String filePath, int sampleSize) {
        List<Game> allGames = readGamesFromCSV(filePath);
        
        if (allGames.size() <= sampleSize) {
            return allGames;
        }
        
        // Retorna uma subamostra
        return allGames.subList(0, sampleSize);
    }
    
    /**
     * Exibe estatísticas básicas do dataset
     */
    public static void displayDatasetStats(List<Game> games) {
        if (games.isEmpty()) {
            System.out.println("Dataset vazio!");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("ESTATÍSTICAS DO DATASET");
        System.out.println("========================================");
        System.out.println("Total de registros: " + games.size());
        
        // Calcular média de jogadores
        double totalAvgPlayers = 0;
        int maxPeak = 0;
        String mostPopularGame = "";
        double highestAvg = 0;
        
        for (Game game : games) {
            totalAvgPlayers += game.getAvgPlayers();
            
            if (game.getPeakPlayers() > maxPeak) {
                maxPeak = game.getPeakPlayers();
            }
            
            if (game.getAvgPlayers() > highestAvg) {
                highestAvg = game.getAvgPlayers();
                mostPopularGame = game.getName();
            }
        }
        
        double avgPlayersOverall = totalAvgPlayers / games.size();
        
        System.out.printf("Média geral de jogadores: %.2f\n", avgPlayersOverall);
        System.out.println("Pico máximo de jogadores: " + maxPeak);
        System.out.println("Jogo com maior média: " + mostPopularGame);
        System.out.printf("Maior média de jogadores: %.2f\n", highestAvg);
        System.out.println("========================================\n");
    }
}
