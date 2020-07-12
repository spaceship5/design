package com.example.spaceship.controller;

import com.example.spaceship.component.RequestComponent;
import com.example.spaceship.entity.AnswerQuestion;
import com.example.spaceship.entity.Course;
import com.example.spaceship.entity.Question;
import com.example.spaceship.entity.Resource;
import com.example.spaceship.repository.CourseRepository;
import com.example.spaceship.service.CourseResourceService;
import com.example.spaceship.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teacher/")
@Slf4j
public class TeacherController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private  RequestComponent requestComponent;
    @Autowired
    private CourseResourceService courseResourceService;
    @PostMapping("course")
    public Map postCourse(@RequestBody Map<String,String> course){
        Course course1 = new Course();
        course1.setDetail(course.get("detail"));
        course1.setName(course.get("name"));
        course1.setResourcePercentage(Integer.valueOf(course.get("resourcePercentage")));
        course1.setHomeworkPercentage(Integer.valueOf(course.get("homeworkPercentage")));
        course1.setQuestionPercentage(Integer.valueOf(course.get("questionPercentage")));
        course1.setTestPercentage(Integer.valueOf(course.get("testPercentage")));
        course1 = courseService.addCourse(course1, requestComponent.getUid());
        return Map.of("course",course1);
    }
    @PatchMapping("course")
    public Map patchCourse(@RequestBody Map<String,String> course){
        Course course1 = courseService.updateCourse(Integer.valueOf(course.get("id")), course);
        return Map.of("course",course1);
    }
    @DeleteMapping("course/{cid}")
    public Map deleteCourse(@PathVariable Integer cid){
        courseService.deleteCourse(cid);
        return Map.of("success",true);
    }
    @GetMapping("courses")
    public Map getCourses(){
        return Map.of("courses",courseService.findCourseByTid(requestComponent.getUid()));
    }
    @PostMapping("resource")
    public Map postResource(@RequestBody Map<String,String> data){
        Resource resource = new Resource();
        resource.setName(data.get("name"));
        resource.setContent(data.get("content"));
        resource.setType(data.get("type"));
        resource.setUrl(data.get("url"));
        Resource resource1 = courseResourceService.addResource(resource, Integer.valueOf(data.get("id")));
        log.debug("添加");
        return Map.of("resource",resource1);
    }
    @PatchMapping("resource")
    public Map patchResource(@RequestBody Map<String,String> data){
        Resource resource1 = courseResourceService.updateResource(Integer.valueOf(data.get("id")),data.get( "name"),data.get("content"),data.get("type"),data.get("url"));
        return Map.of("resource",resource1);
    }
    @DeleteMapping("resource/{rid}")
    public Map deleteResource(@PathVariable Integer rid){
        Boolean success = courseResourceService.deleteResource(rid);
        return Map.of("success",success);
    }
    @PostMapping("question")
    public Map postQuestion(@RequestBody Map<String,String> data){
        Question question = new Question();
        question.setTitle(data.get("title"));
        question.setDetail(data.get("detail"));
        Question question1 = courseResourceService.addQuestion(question, Integer.valueOf(data.get("id")));
        return Map.of("question",question1);
    }
    @PatchMapping("question")
    public Map patchQuestion(@RequestBody Map<String,String> data){
        Question question = courseResourceService.updateQuestion(Integer.valueOf(data.get("id")), data.get("title"), data.get("detail"));
        return Map.of("question",question);
    }
    @DeleteMapping("question/{qid}")
    public Map deleteQuestion(@PathVariable Integer qid){
        Boolean success = courseResourceService.deleteQuestion(qid);
        return Map.of("success",success);
    }
    @GetMapping("/question/{qid}/answerQuestions")
    public Map getAnswerQuestions(@PathVariable Integer qid){
        List<AnswerQuestion> answerQuestions = courseResourceService.listAnswerQuestion(qid);
        return Map.of("answerQuestions",answerQuestions);
    }
    @PatchMapping("answerQuestion")
    public Map patchAnswerQuestion(@RequestBody Map<String,String> data){
        AnswerQuestion answerQuestion = courseResourceService.updateAnswerQuestion(Float.valueOf(data.get("grade")), Integer.valueOf(data.get("id")));
        return Map.of("answerQuestion",answerQuestion);
    }
    @DeleteMapping("answerQuestion/{id}")
    public Map deleteAnswerQuestion(@PathVariable Integer id){
        courseResourceService.deleteAnswerQuestion(id);
        return Map.of("success",true);
    }
}
