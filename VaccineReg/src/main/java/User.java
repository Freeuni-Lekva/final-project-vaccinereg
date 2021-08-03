public class User {
    private final long privateNum;
    private final String name;
    private final String lastName;
    private final String gender;
    private final int age;
    private final String email;
    private final String password;
    private boolean isAdmin;
    // TODO: registration id

    public User(long privateNum, String name,
                String lastName, String gender,
                int age, String email,
                String password, boolean isAdmin)
    {
        this.privateNum = privateNum;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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
}
