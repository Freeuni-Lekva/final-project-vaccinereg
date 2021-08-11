package entities;

public class VaccineCenter {
    private Long id;
    private String regionName;
    private String cityName;
    private String districtName;

    public VaccineCenter(Long id, String regionName, String cityName, String districtName) {
        this.id = id;
        this.regionName = regionName;
        this.cityName = cityName;
        this.districtName = districtName;
    }

    public VaccineCenter(String regionName, String cityName, String districtName) {
        this.regionName = regionName;
        this.cityName = cityName;
        this.districtName = districtName;
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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        VaccineCenter v = (VaccineCenter) o;
        return (this.id.equals(v.getId()) && this.regionName.equals(v.getRegionName()) && this.cityName.equals(v.getCityName()) && this.districtName.equals(v.getDistrictName()));
    }
}
