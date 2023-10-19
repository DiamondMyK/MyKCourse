package dao;

import mapper.CourseMapper;
import model.Course;
import util.DBUtil;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class CourseDao {

    public boolean insertCourse(Course course) {
        boolean result = false;
        SqlSession sqlSession = null;
        int mysql_affected_rows = 0;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            mysql_affected_rows = courseMapper.insertCourse(course);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }

    public Boolean deleteCourse(String cid) {
        boolean result = false;
        int mysql_affected_rows = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            mysql_affected_rows = courseMapper.deleteCourse(cid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }

    public Course getById(String cid) {
        boolean result = false;
        SqlSession sqlSession = null;
        Course course = null;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            course = courseMapper.getById(cid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }
        return course;
    }

    public List<Course> selectCourses(Map<String, Object> map) {
        List<Course> courseList = null;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            courseList = courseMapper.selectCourses(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        return courseList;
    }

    public boolean modifyCourse(Course course) {
        boolean result = false;
        int mysql_affected_rows = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            mysql_affected_rows = courseMapper.modifyCourse(course);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }

    public boolean configCourse(Course course) {
        boolean result = false;
        int mysql_affected_rows = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
            mysql_affected_rows = courseMapper.configCourse(course);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }
}
