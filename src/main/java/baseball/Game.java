package baseball;

record QueryResult(boolean solved, int balls, int strikes) {}

public class Game {

    private String answer;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QueryResult query(String numbers) {
        return new QueryResult(false, 0, 0);
    }
}
