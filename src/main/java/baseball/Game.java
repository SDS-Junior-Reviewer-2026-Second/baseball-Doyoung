package baseball;

import java.text.NumberFormat;

class QueryResult {

    public boolean solved;
    public int balls;
    public int strikes;

    public QueryResult(boolean solved, int balls, int strikes) {
        this.solved = solved;
        this.balls = balls;
        this.strikes = strikes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        QueryResult other = (QueryResult) object;

        return (
            this.solved == other.solved &&
            this.balls == other.balls &&
            this.strikes == other.strikes
        );
    }

    @Override
    public String toString() {
        return (
            "QueryResult(solved=" +
            solved +
            ", balls=" +
            balls +
            ", strikes=" +
            strikes +
            ")"
        );
    }
}

public class Game {

    private int[] answer = { 0, 0, 0 };

    public void setAnswer(String answer) {
        this.answer = parseAnswer(answer);
    }

    public QueryResult query(String numbers) {
        QueryResult result = new QueryResult(false, 0, 0);
        int[] parsed = parseAnswer(numbers);

        for (int i = 0; i < 3; i++) {
            if (answer[i] == parsed[i]) {
                result.strikes++;
            }

            for (int j = 0; j < 3; j++) {
                if (answer[i] == parsed[j] && i != j) {
                    result.balls++;
                }
            }
        }

        if (result.strikes == 3) {
            result.solved = true;
        }

        return result;
    }

    private int[] parseAnswer(String answer) {
        int[] result = { 0, 0, 0 };
        int parsed;

        try {
            parsed = Integer.parseInt(answer);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("illegal answer: " + answer);
        }

        result[2] = parsed % 10;
        result[1] = (parsed / 10) % 10;
        result[0] = parsed / 100;

        if (
            result[0] == result[1] ||
            result[1] == result[2] ||
            result[2] == result[0]
        ) {
            throw new IllegalArgumentException("illegal answer: " + answer);
        }

        for (int i = 0; i < 3; i++) {
            if (result[i] == 0) {
                throw new IllegalArgumentException("illegal answer: " + answer);
            }
        }

        return result;
    }
}
