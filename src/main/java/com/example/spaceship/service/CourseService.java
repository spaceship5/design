package com.example.spaceship.service;

import com.example.spaceship.entity.Course;
import com.example.spaceship.entity.Elective;
import com.example.spaceship.entity.Student;
import com.example.spaceship.repository.CourseRepository;
import com.example.spaceship.repository.ElectiveRepository;
import com.example.spaceship.repository.StudentRepository;
import com.example.spaceship.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private ElectiveRepository electiveRepository;
    public Course addCourse(Course course,Integer tid){
            course.setTeacher(teacherRepository.findById(tid).orElse(null));
            courseRepository.saveAndFlush(course);
            courseRepository.refresh(course);
            return  course;
    }
    public Boolean deleteCourse(Integer cid){
        courseRepository.deleteById(cid);
        return true;
    }
    public Course updateCourse(Integer cid, Map<String,String> course){
        Course course1 = courseRepository.findById(cid).orElse(null);
        course1.setDetail(course.get("detail"));
        course1.setName(course.get("name"));
        course1.setResourcePercentage(Integer.valueOf(course.get("resourcePercentage")));
        course1.setHomeworkPercentage(Integer.valueOf(course.get("homeworkPercentage")));
        course1.setQuestionPercentage(Integer.valueOf(course.get("questionPercentage")));
        course1.setTestPercentage(Integer.valueOf(course.get("testPercentage")));
        return course1;
    }
    public List<Course> listCourseByTid(Integer tid){
        return courseRepository.findByTid(tid);
    }
    public  List<Course> listCourseBySid(Integer sid){
        return electiveRepository.find(sid);
    }
    public List<Course> listCourse(){
        return courseRepository.findAll();
    }
    public Elective addElective(Integer cid,Integer sid){
        Elective elective1 = electiveRepository.find(cid, sid);
        if(elective1==null) {
            Elective elective = new Elective();
            Student student = studentRepository.findById(sid).orElse(null);
            Course course = courseRepository.findById(cid).orElse(null);
            elective.setCourse(course);
            elective.setStudent(student);
            electiveRepository.save(elective);
            return elective;
        }
        return  elective1;
    }
    public Elective findElective(Integer cid,Integer sid){
        return electiveRepository.find(cid,sid);
    }
//    public Elective updateElectiveGrade(Integer eid){
//
//    }

    public Boolean deleteElective(Integer cid,Integer sid){
        Elective elective = electiveRepository.find(cid, sid);
        electiveRepository.delete(elective);
        return  true;
    }
    public Course findCourseByCid(Integer cid){
        return  courseRepository.findById(cid).orElse(null);
    }

    public List<Course> findCourseByTid(Integer tid){
        return  courseRepository.findByTid(tid);
    }
}
