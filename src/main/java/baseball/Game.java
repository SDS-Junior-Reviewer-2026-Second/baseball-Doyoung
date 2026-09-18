package baseball;

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

    private final int ANSWER_SIZE = 3;
    private int[] answer = new int[ANSWER_SIZE];

    public void setAnswer(String answer) {
        this.answer = parseAnswer(answer);
    }

    public QueryResult query(String numbers) {
        QueryResult result = new QueryResult(false, 0, 0);
        int[] parsed = parseAnswer(numbers);

        if (hasDuplicate(parsed) || hasZero(parsed)) {
            throw new IllegalArgumentException("illegal answer: " + numbers);
        }

        makeResult(result, parsed);
        return result;
    }

    private void makeResult(QueryResult result, int[] parsed) {
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
    }

    private int[] parseAnswer(String answer) {
        int[] result = new int[ANSWER_SIZE];
        int number;

        try {
            number = Integer.parseInt(answer);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("illegal answer: " + answer);
        }

        result[2] = number % 10;
        result[1] = (number / 10) % 10;
        result[0] = number / 100;

        return result;
    }

    private boolean hasDuplicate(int[] numbers) {
        return (
            numbers[0] == numbers[1] ||
            numbers[1] == numbers[2] ||
            numbers[2] == numbers[0]
        );
    }

    private boolean hasZero(int[] numbers) {
        for (int i = 0; i < 3; i++) {
            if (numbers[i] == 0) {
                return true;
            }
        }

        return false;
    }
}
