package com.example.course.week3.orm.demo4;


import com.example.course.week3.orm.demo4.ioc.Autowired;
import com.example.course.week3.orm.demo4.ioc.Component;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Data // Don't know why this annotation is not creating getters and setters
@NoArgsConstructor
class HighSchoolStudent {
    public HighSchoolStudent() {}
    private String id;
    private String name;
    private Double grade;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "HighSchoolStudent{id: " + id + ", name: " + name + ", grade: " + grade + "}";
    }
}


@Component
@EnableAspectJAutoProxy
class Starter {

    @Autowired
    private static StuMapperImpl stuM;

    public static void main(String[] args) throws Exception {
        Container.start();
        Student highStu = new Student();
        highStu.setId("1");
        highStu.setName("Student One");
        System.out.println("This is the ordinary student: " + highStu);

        HighSchoolStudent stu = (HighSchoolStudent) stuM.convert(highStu, HighSchoolStudent.class);
        stu.setGrade(2.1);
        System.out.println("This is the high school student: " + stu);
    }
}
