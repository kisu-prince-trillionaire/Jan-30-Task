package com.demo.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.bean.Student;
import com.demo.config.HibernateUtils;

public class StudentDao {

	public void insertStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			String hql = "INSERT INTO Student (firstName, lastName, email) "
					+ "SELECT firstName, lastName, email FROM Student";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			String hql = "UPDATE Student set firstName = :firstName " + "WHERE id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("firstName", student.getFirstName());
			query.setParameter("studentId", 1);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			Student student = session.get(Student.class, id);
			if (student != null) {
				String hql = "DELETE FROM Student " + "WHERE id = :studentId";
				Query query = session.createQuery(hql);
				query.setParameter("studentId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Student getStudent(int id) {

		Transaction transaction = null;
		Student student = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			String hql = " FROM Student S WHERE S.id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("studentId", id);
			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {
				student = (Student) results.get(0);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public List<Student> getStudents() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		}
	}
}
