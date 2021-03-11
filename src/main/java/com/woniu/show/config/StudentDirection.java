package com.woniu.show.config;

public enum StudentDirection {

    JavaDevelopment("0", "java开发"),
    TestDevelopment("1", "测试开发");


    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    StudentDirection(String value, String description) {
        this.description = description;
        this.value = value;
    }

//    public static void main(String[] args) {
//        System.out.println(StudentDirection.JavaDevelopment.value);
//    }
}
