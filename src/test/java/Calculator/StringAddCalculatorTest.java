package Calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StringAddCalculatorTest {
    @Test
    public void splitAndSum_null_또는_빈문자() {
        int result = StringAddCalculator.splitAndSum(null);
        assertThat(result).isEqualTo(0);
        result = StringAddCalculator.splitAndSum("");
        assertThat(result).isEqualTo(0);
    }
    @Test
    public void splitAndSum_숫자하나() throws Exception {
        int result = StringAddCalculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }
    @Test
    public void splitAndSum_쉼표구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }
    @Test
    public void splitAndSum_custom_구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }
    @Test
    public void splitAndSum_negative() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-1,2,3"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_숫자가아닌문자1() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("5,A,3"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_숫자가아닌문자2() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("//*\n1*B*3"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_WrongDelimiterDefined() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("/*\n1*5*3"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_JustNegativeNumber() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-5"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_JustLetter() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("A"))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("ABC"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void splitAndSum_JustDefaultDelimiter() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum(","))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum(":"))
                .isInstanceOf(RuntimeException.class);
    }
}
