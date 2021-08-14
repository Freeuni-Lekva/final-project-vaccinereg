package entities;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private LocalDateTime vaccinationTime;
    private LocalDateTime reservationTime;
    private Long location_vaccine_amount_id;
    private Long user_id;

    public Reservation(Long id, LocalDateTime vaccinationTime, LocalDateTime reservationTime, Long location_vaccine_amount_id, Long user_id) {
        this.id = id;
        this.vaccinationTime = vaccinationTime;
        this.reservationTime = reservationTime;
        this.location_vaccine_amount_id = location_vaccine_amount_id;
        this.user_id = user_id;
    }

    public Reservation(LocalDateTime vaccinationTime, LocalDateTime reservationTime, Long location_vaccine_amount_id, Long user_id) {
        this.vaccinationTime = vaccinationTime;
        this.reservationTime = reservationTime;
        this.location_vaccine_amount_id = location_vaccine_amount_id;
        this.user_id = user_id;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getVaccinationTime() {
        return vaccinationTime;
    }

    public LocalDateTime getReservationTime() {
        return this.reservationTime;
    }

    public Long getLocation_vaccine_amount_id() {
        return location_vaccine_amount_id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVaccinationTime(LocalDateTime vaccinationTime) {
        this.vaccinationTime = vaccinationTime;
    }

    public void setLocation_vaccine_amount_id(Long location_vaccine_amount_id) {
        this.location_vaccine_amount_id = location_vaccine_amount_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        Reservation r = (Reservation) o;
        return (this.id.equals(r.getId())
                && this.vaccinationTime.equals(r.getVaccinationTime())
                && this.location_vaccine_amount_id.equals(r.getLocation_vaccine_amount_id()))
                && this.user_id.equals(r.user_id);
    }


}
