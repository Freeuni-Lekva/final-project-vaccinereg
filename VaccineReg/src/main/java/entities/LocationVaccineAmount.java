package entities;

public class LocationVaccineAmount {
    private Long id;
    private Long vaccineCenterId;
    private String vaccineName;
    private Integer amount;

    public LocationVaccineAmount(Long id, Long vaccineCenterId, String vaccineName, Integer amount) {
        this.id = id;
        this.vaccineCenterId = vaccineCenterId;
        this.vaccineName = vaccineName;
        this.amount = amount;
    }

    public LocationVaccineAmount(Long vaccineCenterId, String vaccineName, Integer amount) {
        this.vaccineCenterId = vaccineCenterId;
        this.vaccineName = vaccineName;
        this.amount = amount;
    }

    public LocationVaccineAmount() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVaccineCenterId(Long vaccineCenterId) {
        this.vaccineCenterId = vaccineCenterId;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getVaccineCenterId() {
        return vaccineCenterId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof LocationVaccineAmount)) return false;

        LocationVaccineAmount l = (LocationVaccineAmount) o;
        return (this.id.equals(l.getId()) && this.vaccineCenterId.equals(l.getVaccineCenterId()) && this.vaccineName.equals(l.getVaccineName()) && this.amount.equals(l.getAmount()));
    }
}
