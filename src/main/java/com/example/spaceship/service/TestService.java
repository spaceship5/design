package com.example.spaceship.service;

import com.example.spaceship.entity.AnswerTestQuestion;
import com.example.spaceship.entity.Test;
import com.example.spaceship.entity.TestQuestion;
import com.example.spaceship.repository.AnswerTestQuestionRepository;
import com.example.spaceship.repository.CourseRepository;
import com.example.spaceship.repository.TestQuestionRepository;
import com.example.spaceship.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TestQuestionRepository testQuestionRepository;
	@Autowired
	private AnswerTestQuestionRepository answerTestQuestionRepository;

	public Test addTest(Test test,Integer cid){
		test.setCourse(courseRepository.findById(cid).orElse(null));
		testRepository.save(test);
		return test;
	}
	public TestQuestion  addTestQuestion(TestQuestion testQuestion,Integer tid){
		testQuestion.setTest(testRepository.findById(tid).orElse(null));
		testQuestionRepository.save(testQuestion);
		return testQuestion;
	}
	public AnswerTestQuestion addAnswerTestQuestion(AnswerTestQuestion answerTestQuestion,Integer tqid){
		answerTestQuestion.setTestQuestion(testQuestionRepository.findTestQuestion(tqid));
		answerTestQuestionRepository.save(answerTestQuestion);
		return answerTestQuestion;
	}

	public Boolean deleteTest(Integer tid){
		testRepository.deleteById(tid);
		return true;
	}

	public Test findTestById(Integer tid){
		return 	testRepository.findById(tid).orElse(null);

	}
	public List<Test> findTestsById(Integer cid ){
		return testRepository.listTests(cid);
	}
	public List<TestQuestion>  findTestQuestionsById(Integer tid){
		return testRepository.listTestQuestions(tid);
	}
//	public Test findHomeWorksByCid(Integer cid){
//		return testRepository.findTest(cid);
//	}
	public List<AnswerTestQuestion> findAnswerTestQuestion(Integer tqid){
		return answerTestQuestionRepository.findAllATQ(tqid);
	}
}
