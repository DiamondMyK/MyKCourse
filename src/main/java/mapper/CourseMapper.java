package mapper;

import model.Course;
import java.util.List;
import java.util.Map;

public interface CourseMapper {

    public int insertCourse(Course course);

    public int deleteCourse(String cid);

    public Course getById(String cid);

    public List<Course> selectCourses(Map<String, Object> map);

    public int modifyCourse(Course course);

    public int configCourse(Course course);

}
