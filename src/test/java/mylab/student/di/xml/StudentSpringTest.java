package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// JUnit5와 Spring Test 연결
@ExtendWith(SpringExtension.class)
// 테스트에서 읽을 XML 설정 파일
@ContextConfiguration(locations = "classpath:mylab-student-di.xml")
public class StudentSpringTest {

    @Autowired
    Course course;

    @Autowired
    GradeService gradeService;

    @Test
    void courseBean() {
        // 강좌 객체와 학생 목록 주입 검증
        assertNotNull(course);
        assertNotNull(course.getStudents());

        // 강좌 정보 검증
        assertEquals("C001", course.getCourseId());
        assertEquals("Spring Framework", course.getCourseName());

        // 학생 수와 목록 순서 검증
        assertEquals(2, course.getStudents().size());

        Student student1 = course.getStudents().get(0);
        assertEquals("S001", student1.getId());
        assertEquals("배상아", student1.getName());
        assertEquals(95, student1.getScore());

        Student student2 = course.getStudents().get(1);
        assertEquals("S002", student2.getId());
        assertEquals("홍길동", student2.getName());
        assertEquals(85, student2.getScore());

        // 평균 점수 검증: 배상아(95점), 홍길동(85점) → (95 + 85) / 2 = 90점
        assertEquals(90.0, course.getAverageScore(), 0.001);
    }

    @Test
    void gradeServiceBean() {
        // 성적 서비스와 강좌 주입 검증
        assertNotNull(gradeService);
        assertNotNull(gradeService.getCourse());

        // 테스트에 주입된 강좌와 동일한 객체인지 검증
        assertSame(course, gradeService.getCourse());

        // 학생별 등급 검증
        assertEquals("A", gradeService.calculateGrade("S001"));
        assertEquals("B", gradeService.calculateGrade("S002"));

        // 존재하지 않는 학생 조회 검증
        assertEquals(
                "학생을 찾을 수 없습니다.",
                gradeService.calculateGrade("S999"));
    }

    @Test
    void highScoreStudents() {
        // 90점 이상인 학생 조회
        List<Student> students =
                gradeService.getHighScoreStudents(90);

        assertEquals(1, students.size());
        assertEquals("배상아", students.get(0).getName());

        // 기준 점수와 같은 학생도 포함되는지 검증
        assertEquals(2,
                gradeService.getHighScoreStudents(85).size());

        // 기준을 충족하는 학생이 없으면 빈 목록 반환
        assertTrue(
                gradeService.getHighScoreStudents(100).isEmpty());
    }
}