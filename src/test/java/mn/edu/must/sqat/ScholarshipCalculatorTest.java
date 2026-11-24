package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void letterGradeReturnsAForHighScore() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gpaAveragesTwoGrades() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void scholarshipAwardsFullRateForHighGpaAndCredits() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipAwardsSeventyPercentForEligibleStudent() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipAwardsFiftyPercentForEligibleStudent() {
        assertEquals(50, calculator.scholarship(3.2, 9, false));
    }

    @Test
    void letterGradeReturnsBForScoreInEighties() {
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void letterGradeReturnsCForScoreInSeventies() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void letterGradeReturnsDForScoreInSixties() {
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void letterGradeReturnsFForLowScore() {
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void letterGradeRejectsNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    void letterGradeRejectsScoreAboveOneHundred() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @Test
    void gpaRejectsNullScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaRejectsEmptyScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void gpaUsesCGradePoints() {
        assertEquals(2.0, calculator.gpa(new double[]{75}));
    }

    @Test
    void gpaUsesDGradePoints() {
        assertEquals(1.0, calculator.gpa(new double[]{65}));
    }

    @Test
    void gpaUsesFGradePoints() {
        assertEquals(0.0, calculator.gpa(new double[]{50}));
    }

    @Test
    void scholarshipRejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipRejectsGpaAboveFour() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipRejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.9, -1, false));
    }

    @Test
    void scholarshipAwardsNothingForViolation() {
        assertEquals(0, calculator.scholarship(4.0, 20, true));
    }

    @Test
    void scholarshipFallsToSeventyWhenFullAwardCreditsAreInsufficient() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipFallsToSeventyWhenFullAwardGpaIsInsufficient() {
        assertEquals(70, calculator.scholarship(3.7, 16, false));
    }

    @Test
    void scholarshipFallsToFiftyWhenSeventyAwardCreditsAreInsufficient() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipFallsToFiftyWhenSeventyAwardGpaIsInsufficient() {
        assertEquals(50, calculator.scholarship(3.2, 12, false));
    }

    @Test
    void scholarshipAwardsFiftyAtMinimumGpa() {
        assertEquals(50, calculator.scholarship(3.0, 0, false));
    }

    @Test
    void scholarshipAwardsNothingBelowMinimumGpa() {
        assertEquals(0, calculator.scholarship(2.9, 15, false));
    }
}
