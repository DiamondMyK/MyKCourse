package service;

import dao.CourseDao;
import model.Course;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDao();

    @Override
    public boolean insertCourse(Course course) {
        return courseDao.insertCourse(course);
    }

    @Override
    public Boolean removeCourse(String cid) {
        return courseDao.deleteCourse(cid);
    }

    @Override
    public Course getById(String cid) {
        return courseDao.getById(cid);
    }

    @Override
    public List<Course> selectCourses(Map<String, Object> map) {
        List<Course> courses = courseDao.selectCourses(map);
        for (Course cours : courses) {
            if (null != cours.getAuditone() && cours.getAuditone() == 0) {
                cours.setNameone("所有课程主讲教师");
            }
            if (null != cours.getAudittwo() && cours.getAudittwo() == -1) {
                cours.setNametwo("不需要");
            } else if (null != cours.getAudittwo() && cours.getAudittwo() == 0) {
                cours.setNametwo("所有主管教师");
            }
        }
        return courses;
    }

    @Override
    public boolean modifyCourse(Course course) {
        return courseDao.modifyCourse(course);
    }

    @Override
    public boolean configCourse(Course course) {
        return courseDao.configCourse(course);
    }
}
