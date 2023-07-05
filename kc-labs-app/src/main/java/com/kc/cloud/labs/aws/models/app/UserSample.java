package com.kc.cloud.labs.aws.models.app;

public class UserSample {
    private Integer id;

    private String name;

    private String salary;

    private Boolean active;

    private Integer age;

    public UserSample() {
    }

    public UserSample(Integer id, String name, String salary, Boolean active, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.active = active;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserSample{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
