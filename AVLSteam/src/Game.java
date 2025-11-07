public class Game implements Comparable<Game> {
    private String month;
    private double avgPlayers;
    private double gain;
    private double gainPercent;
    private int peakPlayers;
    private String name;
    private int steamAppid;

    public Game(String month, double avgPlayers, double gain, double gainPercent, 
                int peakPlayers, String name, int steamAppid) {
        this.month = month;
        this.avgPlayers = avgPlayers;
        this.gain = gain;
        this.gainPercent = gainPercent;
        this.peakPlayers = peakPlayers;
        this.name = name;
        this.steamAppid = steamAppid;
    }

    // Getters
    public String getMonth() {
        return month;
    }

    public double getAvgPlayers() {
        return avgPlayers;
    }

    public double getGain() {
        return gain;
    }

    public double getGainPercent() {
        return gainPercent;
    }

    public int getPeakPlayers() {
        return peakPlayers;
    }

    public String getName() {
        return name;
    }

    public int getSteamAppid() {
        return steamAppid;
    }

    // Setters
    public void setMonth(String month) {
        this.month = month;
    }

    public void setAvgPlayers(double avgPlayers) {
        this.avgPlayers = avgPlayers;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public void setGainPercent(double gainPercent) {
        this.gainPercent = gainPercent;
    }

    public void setPeakPlayers(int peakPlayers) {
        this.peakPlayers = peakPlayers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteamAppid(int steamAppid) {
        this.steamAppid = steamAppid;
    }

    @Override
    public int compareTo(Game other) {
        // Comparação primária por número médio de jogadores
        return Double.compare(this.avgPlayers, other.avgPlayers);
    }

    @Override
    public String toString() {
        return String.format("Game{name='%s', month='%s', avgPlayers=%.2f, peakPlayers=%d, appid=%d}", 
                             name, month, avgPlayers, peakPlayers, steamAppid);
    }

    // Métodos auxiliares para análise
    public int getYear() {
        String[] parts = month.split("-");
        return Integer.parseInt(parts[1]) + 2000;
    }

    public String getMonthName() {
        return month.split("-")[0];
    }
}
