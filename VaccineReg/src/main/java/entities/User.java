package entities;

public class User {
    private long privateNum;
    private String name;
    private String lastName;
    private String gender;
    private int age;
    private String email;
    private String password;
    private Boolean isAdmin;
    private Long reservationId;

    public User(long privateNum, String name, String lastName, String gender, int age, String email, String password, Boolean isAdmin, Long reservationId) {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.reservationId = reservationId;
    }

    public User(long privateNum, String name, String lastName, String gender, int age, String email, String password) {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = false;
    }

    public User() {

    }

    public void setPrivateNum(Long privateNum) {
        this.privateNum = privateNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public long getPrivateNum() {
        return privateNum;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Long getReservationId() {
        return reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return (this.privateNum == user.getPrivateNum() && this.age == user.getAge() && this.isAdmin.equals(user.getAdmin()) &&
                this.name.equals(user.getName()) && this.lastName.equals(user.getLastName()) && this.gender.equals(user.getGender()) &&
                this.email.equals(user.getEmail()) && this.password.equals(user.getPassword()));
    }
}
