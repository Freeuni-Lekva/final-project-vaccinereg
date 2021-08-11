package entities;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private LocalDateTime startTimeOne;
    private LocalDateTime startTimeTwo;
    private Long location_vaccine_amount_id;

    public Reservation(Long id, LocalDateTime startTimeOne, LocalDateTime startTimeTwo, Long location_vaccine_amount_id) {
        this.id = id;
        this.startTimeOne = startTimeOne;
        this.startTimeTwo = startTimeTwo;
        this.location_vaccine_amount_id = location_vaccine_amount_id;
    }

    public Reservation(LocalDateTime startTimeOne, LocalDateTime startTimeTwo, Long location_vaccine_amount_id) {
        this.startTimeOne = startTimeOne;
        this.startTimeTwo = startTimeTwo;
        this.location_vaccine_amount_id = location_vaccine_amount_id;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTimeOne() {
        return startTimeOne;
    }

    public LocalDateTime getStartTimeTwo() {
        return startTimeTwo;
    }

    public Long getLocation_vaccine_amount_id() {
        return location_vaccine_amount_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTimeOne(LocalDateTime startTimeOne) {
        this.startTimeOne = startTimeOne;
    }

    public void setStartTimeTwo(LocalDateTime startTimeTwo) {
        this.startTimeTwo = startTimeTwo;
    }

    public void setLocation_vaccine_amount_id(Long location_vaccine_amount_id) {
        this.location_vaccine_amount_id = location_vaccine_amount_id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        Reservation r = (Reservation) o;
        return (this.id.equals(r.getId()) && this.startTimeOne.equals(r.getStartTimeOne()) && this.startTimeTwo.equals(r.getStartTimeTwo()) && this.location_vaccine_amount_id.equals(r.getLocation_vaccine_amount_id()));
    }
}
