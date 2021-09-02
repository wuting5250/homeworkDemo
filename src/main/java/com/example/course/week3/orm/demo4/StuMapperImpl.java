package com.example.course.week3.orm.demo4;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 * // Student is an object used for business logic.
 * // HighSchoolStudent is an entity representing a sql table.
 * // We can use StuMapper to convert the Student object to a
 * // HighSchoolStudent entity for saving in SQL.
 * HighSchoolStudent hsStudent = stuM.convert(student, HighSchoolStudent.class);
 * CollegeStudent cStudent = stuM.convert(student, CollegeStudent.class);
 *
 */
@NoArgsConstructor

@Component
@com.example.course.week3.orm.demo4.ioc.Component
public class StuMapperImpl implements StuMapper {
    @Override
    public Object convert(Student stu, Class<?> target) {
        if (stu == null) {
            return null;
        }

        Object tObject;
        try {
            // You have a class, but you don't know anything about the class.
            // You use reflection to find out information about the class.
            // Because we want to create an object of the target class, we use
            // reflection to get the constructor, and then we use the constructor
            // to create the instance (object) of the target class.
            Constructor ct = target.getConstructor();
            // the newInstance method on the Constructor creates the object.
            tObject = ct.newInstance();
            // We assume that the target class contains a getter method for name.
            // We use reflection to get the setName method of the target class
            // so that we can run the method on the tObject.
            Method setName = target.getMethod("setName", String.class);
            setName.invoke(tObject, stu.getName());
            Method setId = target.getMethod("setId", String.class);
            setId.invoke(tObject, stu.getId());
            return tObject;
        } catch (Throwable e) {
            System.err.println(e);
            return null;
        }
    }
}
