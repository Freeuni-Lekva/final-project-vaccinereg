package entities;

public class VaccineCenter {
    private Long id;
    private String regionName;
    private String cityName;
    private String districtName;
    private Integer peopleLimitPerVaccineAtSameTime;
    private String centerName;

    public VaccineCenter(Long id, String regionName, String cityName, String districtName , Integer peopleLimitPerVaccineAtSameTime , String centerName) {
        this.id = id;
        this.regionName = regionName;
        this.cityName = cityName;
        this.districtName = districtName;
        this.peopleLimitPerVaccineAtSameTime = peopleLimitPerVaccineAtSameTime;
        this.centerName = centerName;
    }

    public VaccineCenter(String regionName, String cityName, String districtName , Integer peopleLimitPerVaccineAtSameTime , String centerName) {
        this.regionName = regionName;
        this.cityName = cityName;
        this.districtName = districtName;
        this.peopleLimitPerVaccineAtSameTime = peopleLimitPerVaccineAtSameTime;
        this.centerName = centerName;
    }

    public VaccineCenter() {

    }

    public Long getId() {
        return id;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public Integer getPeopleLimitPerVaccineAtSameTime() {
        return peopleLimitPerVaccineAtSameTime;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setPeopleLimitPerVaccineAtSameTime(Integer peopleLimitPerVaccineAtSameTime) {
        this.peopleLimitPerVaccineAtSameTime = peopleLimitPerVaccineAtSameTime;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        VaccineCenter v = (VaccineCenter) o;
        return (this.id.equals(v.getId()) && this.regionName.equals(v.getRegionName()) && this.cityName.equals(v.getCityName()) && this.districtName.equals(v.getDistrictName()) && this.peopleLimitPerVaccineAtSameTime.equals(v.getPeopleLimitPerVaccineAtSameTime()) && this.centerName.equals(v.getCenterName()));
    }
}
