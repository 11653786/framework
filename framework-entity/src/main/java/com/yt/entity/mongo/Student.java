package com.yt.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by user on 2015/9/24.
 */
@Document(collection ="student")
public class Student {
    @Id
    private int _id;
    @Field(value = "name")
    private String name;
    @Field(value = "age")
    private int age;
    @Field(value = "sex")
    private String sex;



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public enum StudentSex{
        woman("0"),man("1");
        private String sex;

        StudentSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
