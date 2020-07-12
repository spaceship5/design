package com.example.spaceship.service;

import com.example.spaceship.entity.*;
import com.example.spaceship.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class CourseResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;
    public Resource addResource(Resource resource,Integer cid){
        Course course = courseRepository.findById(cid).orElse(null);
        resource.setCourse(course);
        resourceRepository.save(resource);
        return  resource;
    }
    public Question addQuestion(Question question, Integer cid){
        question.setCourse(courseRepository.findById(cid).orElse(null));
        questionRepository.save(question);
        return question;
    }
    public Resource updateResource(Integer id,String name,String content,String type,String url){
        Resource resource = resourceRepository.findById(id).orElse(null);
        resource.setContent(content);
        resource.setName(name);
        resource.setUrl(url);
        resource.setType(type);
        return  resource;
    }
    public Question updateQuestion(Integer id,String title,String detail){
        Question question = questionRepository.findById(id).orElse(null);
        question.setDetail(detail);
        question.setTitle(title);
        return question;
    }
    public Resource findResource(Integer rid){
        Resource resource = resourceRepository.findById(rid).orElse(null);
        return  resource;
    }
    public Question findQuestion(Integer qid){
        Question question = questionRepository.findById(qid).orElse(null);
        return question;
    }
    public List<Resource> listResource(Integer cid){
        return resourceRepository.list(cid);
    }
    public List<Question> listQuestion(Integer cid){
        return questionRepository.list(cid);
    }
    public Boolean deleteResource(Integer id){
        resourceRepository.deleteById(id);
        return true;
    }
    public Boolean deleteQuestion(Integer id){
        questionRepository.deleteById(id);
        return true;
    }

    public AnswerQuestion addAnswerQuestion(Integer qid,Integer sid,String ans) {
        AnswerQuestion answerQuestion = new AnswerQuestion();
        answerQuestion.setAnswer(ans);
        answerQuestion.setQuestion(questionRepository.findById(qid).orElse(null));
        answerQuestion.setStudent(studentRepository.findById(sid).orElse(null));
        answerQuestionRepository.save(answerQuestion);
        return answerQuestion;
    }

    public AnswerQuestion findAnswerQuestion(Integer qid, Integer uid) {
        return answerQuestionRepository.find(qid,uid);
    }
}
