package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Student;
import com.example.mvcdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {
    // 注入StudentRepository，这样我们可以在服务中使用它来访问数据库
    private final StudentRepository studentRepository;

    @Autowired//这是一个自动装配的注解，Spring容器会自动注入一个StudentRepository实例到StudentService中。
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }



    public List<Student> findByEmailLike(String email){
        return studentRepository.findByEmailLike("%" + email + "%");
    }

    public void addStudents(){// 添加学生信息
        Student maria = new Student("Mary",
                "mary@gmail.com");
        Student alex = new Student("Alex",
                "alex@gmail.com");
        Student dean = new Student("Dean",
                "dean@yahoo.com");
        Student a = new Student("a",
                "a@yahoo.com");
        studentRepository.saveAll(List.of(maria, alex, dean,a));
        //添加学生信息的方法。这个方法创建了四个Student对象，
        // 并将它们添加到数据库中。
        // 它使用studentRepository的saveAll()方法来批量保存学生数据。
    }



    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student s = studentRepository.findById(studentId).
                orElseThrow(()-> new IllegalStateException("Student ID not exists"));
        if(name!=null && name.length()>0 && !name.equals(s.getName())){
            s.setName(name);
        }
        if(email!=null && email.length()>0 && !email.equals(s.getEmail())){
            s.setEmail(email);
        }
    }
}
