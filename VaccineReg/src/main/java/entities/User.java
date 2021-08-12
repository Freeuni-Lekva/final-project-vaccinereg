package entities;

import java.time.LocalDate;

public class User {
    private long privateNum;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String password;
    private boolean isAdmin;
    private Long reservationId;

    public User(long privateNum, String name, String lastName, String gender, LocalDate birthDate, String email, String password, Boolean isAdmin, Long reservationId) {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.reservationId = reservationId;
    }

    public User(long privateNum, String name, String lastName, String gender, LocalDate birthDate, String email, String password, Long reservationId) {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.reservationId = reservationId;

        this.isAdmin = false;
    }

    public User(long privateNum, String name, String lastName, String gender, LocalDate birthDate, String email, String password) {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;

        this.isAdmin = false;
        this.reservationId = null;
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

    public void setAge(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public int getAge() {
        if(birthDate == null) return -1;

        LocalDate now= LocalDate.now();
        int diff = now.getYear() - birthDate.getYear();
        if (birthDate.getMonth().compareTo(now.getMonth()) > 0  ||
                (birthDate.getMonth().compareTo(now.getMonth()) == 0 && birthDate.getDayOfMonth() > now.getDayOfMonth())) {
            diff--;
        }
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return (this.privateNum == user.getPrivateNum() && this.birthDate.equals(user.getBirthDate()) && this.isAdmin == user.getAdmin() &&
                this.name.equals(user.getName()) && this.lastName.equals(user.getLastName()) && this.gender.equals(user.getGender()) &&
                this.email.equals(user.getEmail()) && this.password.equals(user.getPassword()));
    }
}
