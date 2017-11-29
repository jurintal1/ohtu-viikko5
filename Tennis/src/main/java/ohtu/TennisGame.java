package ohtu;

public class TennisGame {

    private int player1ScoreNum = 0;
    private int player2ScoreNum = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1ScoreNum += 1;
        } else {
            player2ScoreNum += 1;
        }
    }

    public String getScore() {
        String score;
        if (player1ScoreNum == player2ScoreNum) {
            score = equalScore();
        } else if (player1ScoreNum >= 4 || player2ScoreNum >= 4) {
            score = advantageAndWin();
        } else {
            score = score();
        }
        return score;
    }

    public String equalScore() {
        if (player1ScoreNum >= 4) {
            return "Deuce";
        }
        return scoreIntToString(player1ScoreNum) + "-All";
    }

    public String advantageAndWin() {
        String score;
        int minusResult = player1ScoreNum - player2ScoreNum;
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    public String score() {
        String score = "";
        String playerScore1 = scoreIntToString(player1ScoreNum);
        String playerScore2 = scoreIntToString(player2ScoreNum);
        return playerScore1 + "-" + playerScore2;
    }

    public String scoreIntToString(int scoreNum) {
        String playerScore = "";
        switch (scoreNum) {
            case 0:
                playerScore += "Love";
                break;
            case 1:
                playerScore += "Fifteen";
                break;
            case 2:
                playerScore += "Thirty";
                break;
            case 3:
                playerScore += "Forty";
                break;
        }
        return playerScore;
    }
}
