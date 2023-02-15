package com.elmiraantipina.spring.springboot.springboot_rest.dao;

import com.elmiraantipina.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{


    //БЕЗ ИСПОЛЬЗОВАНИЯ HIBERNATE

    //@Autowired// Dependency Injection для поля, зависимости с sessionFactory бина
    //private SessionFactory sessionFactory;//в Boot вместо сессии используется EntityManager -
    // это механизм по управлению связи класса Employee с таблицей employees из БД
    @Autowired
    private EntityManager entityManager;

    @Override
    // Убираем @Transactional и переносим ее в Service
    //@Transactional//чтобы весь цикл транзакции выполнял Spring, открывал и закрывал
    public List<Employee> getAllEmployees() {
        /*Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();*/

        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        /*Session session = entityManager.unwrap(Session.class);
        //Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);//сохраняем данные о новом работнике*/
        Employee newEmployee = entityManager.merge(employee);//сохраняем или изменяем данные о работнике
        employee.setId(newEmployee.getId());


    }

    @Override
    public Employee getEmployee(int id) {
        /*Session session = entityManager.unwrap(Session.class);
        //Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);//получаем работника из БД по id*/

        Employee employee = entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        /*Session session = entityManager.unwrap(Session.class);
        //Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee "+
                "where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();*/

        Query query =entityManager.createQuery("delete from Employee "+
                "where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
