import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.state.StudentCourseOfferingState;
import org.junit.Test;

import java.util.HashMap;

public class HashMapTest {

    @Test
    public void test1() {
        HashMap<Integer, CourseOffering> map = new HashMap<>(2);
        map.put(StudentCourseOfferingState.SELECTED.getState(), new CourseOffering());
        map.put(StudentCourseOfferingState.SELECTED.getState(), new CourseOffering());
        map.put(StudentCourseOfferingState.SELECTED.getState(), new CourseOffering());
        Object data = map;
        System.out.println(data);
    }

}
