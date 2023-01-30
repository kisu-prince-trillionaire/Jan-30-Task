package com.demo.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	private int id;
	private String question;
	@OneToMany(mappedBy= "question")
	private List<Answer> answers;

	public Question(int id, String question, List<Answer> answers) {
		super();
		this.id = id;
		this.question = question;
		this.answers = answers;
	}

	public Question() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", answers=" + answers + "]";
	}

}
