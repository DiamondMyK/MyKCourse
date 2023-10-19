package service;

import model.Course;
import java.util.List;
import java.util.Map;

public interface CourseService {

    public boolean insertCourse(Course course);

    public Boolean removeCourse(String cid);

    public Course getById(String cid);

    public List<Course> selectCourses(Map<String, Object> map);

    public boolean modifyCourse(Course course);

    public boolean configCourse(Course course);

}
