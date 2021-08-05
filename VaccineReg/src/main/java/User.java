import java.util.Objects;

public class User {
    private final long privateNum;
    private final String name;
    private final String lastName;
    private final String gender;
    private final int age;
    private final String email;
    private final String password;
    private boolean isAdmin;
    private int vaccinationCount;
    // TODO: registration id

    public User(long privateNum, String name,
                String lastName, String gender,
                int age, String email,
                String password)
    {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;

        this.isAdmin = false;
        this.vaccinationCount = 0;
    }


    public User(long privateNum, String name,
                String lastName, String gender,
                int age, String email,
                String password, boolean isAdmin,
                int vaccinationCount)
    {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.vaccinationCount = vaccinationCount;
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

    /**
     * @return "m" for male, "f" for female
     */
    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getVaccinationCount() {
        return vaccinationCount;
    }

    public void setVaccinationCount(int vaccination_count) {
        this.vaccinationCount = vaccination_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return privateNum == user.privateNum && age == user.age && isAdmin == user.isAdmin &&
                name.equals(user.name) && lastName.equals(user.lastName) && gender.equals(user.gender) &&
                email.equals(user.email) && password.equals(user.password) && vaccinationCount == user.vaccinationCount;
    }
}
