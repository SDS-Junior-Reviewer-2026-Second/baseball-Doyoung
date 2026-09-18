package baseball;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private final String ANSWER = "123";

    private Game game = new Game();

    @BeforeEach
    public void setupAnswer() {
        game.setAnswer(ANSWER);
    }

    @Test
    public void 입력값이_없을_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.query("");
        });
    }

    @Test
    public void 입력값_자리수가_세자리가_아닐_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.query("12");
        });
    }

    @Test
    public void 입력값에_숫자_외의_문자가_입력될_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.query("Hello, world!");
        });
    }

    @Test
    public void 입력값에_중복된_숫자가_입력될_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.query("112");
        });
    }

    @Test
    public void 숫자_세개가_전부_일치_할_경우_3_strike() {
        QueryResult expected = new QueryResult(true, 0, 3);
        QueryResult actual = game.query(ANSWER);

        assertEquals(expected, actual);
    }

    @Test
    public void 숫자_세개가_전부_일치_하지_않을_경우_0_strike_0_ball() {
        QueryResult expected = new QueryResult(false, 0, 0);
        QueryResult actual = game.query("789");

        assertEquals(expected, actual);
    }

    @Test
    public void 스트라이크만_있을_경우_1_strike_0_ball() {
        QueryResult expected = new QueryResult(false, 0, 1);
        QueryResult actual = game.query("528");

        assertEquals(expected, actual);
    }

    @Test
    public void 볼만_있을_경우_0_strike_1_ball() {
        QueryResult expected = new QueryResult(false, 1, 0);
        QueryResult actual = game.query("516");

        assertEquals(expected, actual);
    }

    @Test
    public void 볼과_스트라이크가_함께_있을_경우_1_strike_1_ball() {
        QueryResult expected = new QueryResult(false, 1, 1);
        QueryResult actual = game.query("152");

        assertEquals(expected, actual);
    }
}
