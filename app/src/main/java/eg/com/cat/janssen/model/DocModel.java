package eg.com.cat.janssen.model;


import java.util.Observable;

public class DocModel extends Observable {

    /**
     * id : 1
     * name : Ahmed Adel
     * user_id : 1
     * created_at : 2019-03-04 20:50:08
     * updated_at : 2019-03-04 20:50:08
     * country_id : 2
     * city_id : 1
     * hospital_name : rawda
     * business_sector : test
     * date_of_recruitment : 03/03/2019
     * number_of_patients : 2
     */

    private static DocModel instance = null;
    private int id;
    private String name;
    private String user_id;
    private String created_at;
    private String updated_at;
    private String country_id;
    private String city_id;
    private String hospital_name;
    private String business_sector;
    private String date_of_recruitment;
    private int number_of_patients;

    public static DocModel getInstance() {
        if (instance == null)
            instance = new DocModel();
        return instance;
    }

    public int getId() {
        setChanged();
        return id;
    }

    public void setId(int id) {
        setChanged();

        this.id = id;
    }

    public String getName() {
        setChanged();

        return name;
    }

    public void setName(String name) {
        setChanged();

        this.name = name;
    }

    public String getUser_id() {
        setChanged();
        return user_id;
    }

    public void setUser_id(String user_id) {
        setChanged();
        this.user_id = user_id;
    }

    public String getCreated_at() {
        setChanged();
        return created_at;
    }

    public void setCreated_at(String created_at) {
        setChanged();
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        setChanged();

        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        setChanged();
        this.updated_at = updated_at;
    }

    public String getCountry_id() {
        setChanged();
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getHospital_name() {
        setChanged();

        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getBusiness_sector() {
        return business_sector;
    }

    public void setBusiness_sector(String business_sector) {
        this.business_sector = business_sector;
    }

    public String getDate_of_recruitment() {
        setChanged();
        return date_of_recruitment;
    }

    public void setDate_of_recruitment(String date_of_recruitment) {
        this.date_of_recruitment = date_of_recruitment;
    }

    public int getNumber_of_patients() {
        return number_of_patients;
    }

    public void setNumber_of_patients(int number_of_patients) {
        this.number_of_patients = number_of_patients;
    }
}