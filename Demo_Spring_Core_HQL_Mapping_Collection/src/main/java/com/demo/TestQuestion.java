package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.bean.Answer;
import com.demo.bean.Question;

public class TestQuestion {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		Configuration cfg = new Configuration();
		cfg.configure("hibernate_cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		System.out.println(factory);
		
		Question q1 = new Question();
		q1.setId(4);
		q1.setQuestion("B for ?");
		
		Answer ans1 = new Answer();
		ans1.setId(51);
		ans1.setAnswer("Ball");
		ans1.setQuestion(q1);
		
		Answer ans2 = new Answer();
		ans2.setId(51);
		ans2.setAnswer("Bat");
		ans2.setQuestion(q1);
		
		Answer ans3 = new Answer();
		ans3.setId(52);
		ans3.setAnswer("Basket");
		ans3.setQuestion(q1);
		
		List<Answer> list =new ArrayList<Answer>();
		list.add(ans1);
		list.add(ans2);
		list.add(ans3);
		
		q1.setAnswers(list);
		
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(q1);
        session.evict(ans1);
        session.evict(ans2);
        session.evict(ans3);
        session.getTransaction().commit();
        session.close();
	}

}
