package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
