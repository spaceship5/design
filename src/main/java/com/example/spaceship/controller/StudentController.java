package com.example.spaceship.controller;

import com.example.spaceship.component.RequestComponent;
import com.example.spaceship.entity.*;
import com.example.spaceship.repository.CourseRepository;
import com.example.spaceship.repository.ElectiveRepository;
import com.example.spaceship.repository.StudentRepository;
import com.example.spaceship.service.CourseResourceService;
import com.example.spaceship.service.CourseService;
import com.example.spaceship.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ElectiveRepository electiveRepository;
    @Autowired
    private TestService testService;
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseResourceService courseResourceService;
    @GetMapping("courses")
    public Map getCourses(){
        return Map.of("courses",courseService.listCourse());
    }
    @GetMapping("coursesBySid")
    public Map getCourseBySid(){
        return Map.of("courses",courseService.listCourseBySid(requestComponent.getUid()));

    }
    @GetMapping("course/{cid}")
    public Map getCourseByCid(@PathVariable Integer cid){
        Elective elective = courseService.findElective(cid, requestComponent.getUid());
        List<Resource> resources = courseResourceService.listResource(cid);
        List<Question> questions = courseResourceService.listQuestion(cid);
        Integer numberAnswerQuestion = courseResourceService.numberAnswerQuestion(cid, requestComponent.getUid());
        Integer numberLearnedResource = courseResourceService.numberLearnedResource(cid, requestComponent.getUid());
        if(elective!=null)
        return Map.of("course",courseService.findCourseByCid(cid),"elective",elective,"resources",resources,"questions",questions,"numberAnswerQuestion",numberAnswerQuestion,"numberLearnedResource",numberLearnedResource);
        else
            return Map.of("course",courseService.findCourseByCid(cid),"resources",resources,"questions",questions);
    }
    @PostMapping("elective")
    public Map postElective(@RequestBody Map<String,String> data){
        courseService.addElective(Integer.valueOf(data.get("cid")),requestComponent.getUid());
        return Map.of("success",true);
    }
    @GetMapping("course/{cid}/resource/{rid}")
    public  Map getResource(@PathVariable Integer cid, @PathVariable Integer rid){
        Resource resource = courseResourceService.findResource(rid);
        LearnedResource learnedResource = courseResourceService.findLearnedResource(rid, requestComponent.getUid());
        if(learnedResource!=null)
            return Map.of("resource",resource,"learnedResource",learnedResource);
        else
        return Map.of("resource",resource);
    }
    @GetMapping("course/{cid}/question/{qid}")
    public  Map getQuestion(@PathVariable Integer cid, @PathVariable Integer qid){
        Question question = courseResourceService.findQuestion(qid);
        AnswerQuestion answerQuestion = courseResourceService.findAnswerQuestion(qid, requestComponent.getUid());
        if(answerQuestion!=null)
        return Map.of("question",question,"answerQuestion",answerQuestion);
       else  return Map.of("question",question);
    }
    @PostMapping("answerQuestion")
    public Map postAnswerQuestion(@RequestBody Map<String,String> data){
        AnswerQuestion answerQuestion = courseResourceService.addAnswerQuestion(Integer.valueOf(data.get("qid")),requestComponent.getUid(), data.get("answer"));
        return Map.of("answerQuestion",answerQuestion);
    }
    @GetMapping("question/{qid}/answerQuestion")
    public Map getAnswerQuestion(@PathVariable Integer qid){
        return  Map.of("answerQuestion",courseResourceService.findAnswerQuestion(qid,requestComponent.getUid()));
    }
    @PostMapping("learnedResource")
    public  Map postLearnedResource(@RequestBody Map<String,String> data){
        LearnedResource learnedResource = courseResourceService.addLearnedResource(requestComponent.getUid(), Integer.valueOf(data.get("rid")));
        return Map.of("learnedResource",learnedResource);
    }

    //以下为试题管理 学生部分，用于试题请求和试题提交请求
    @GetMapping("course/{cid}/students")
    public  Map getStudents(@PathVariable Integer cid){
        List<Student> students=electiveRepository.findStudent(cid) ;
        return Map.of("student",students);
    }
    @GetMapping("course/{cid}/tests") //试题列表
    public Map getTests(@PathVariable Integer cid) {
        List<Test> tests=testService.findTestsById(cid);
        return Map.of("tests",tests);
    }
    @GetMapping("tests/{tid}/test")  //单个试题
    public Map getTest(@PathVariable Integer tid) {
        Test test=testService.findTestById(tid);
        return Map.of("test",test);
    }
    @PostMapping("answerTestQuestion")     //
    public Map postAnswerTestQuestion(@RequestBody Map<String,String> data){
        AnswerTestQuestion answerTestQuestion=new AnswerTestQuestion();
        answerTestQuestion.setAnswer(data.get("answer"));
        testService.addAnswerTestQuestion(answerTestQuestion,Integer.valueOf(data.get("id")));
        return Map.of("answerTestQuestion",answerTestQuestion);

    }
    @PostMapping("answerGrades")
    public Map postAnswerGrades(@RequestBody Map<String,Integer[]> data){

        return Map.of();
    }
}
