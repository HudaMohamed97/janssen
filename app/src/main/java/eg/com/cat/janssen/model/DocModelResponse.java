package eg.com.cat.janssen.model;

import java.util.List;

public class DocModelResponse {


    /**
     * data : [{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08","country_id":"2","city_id":"1","hospital_name":"rawda","business_sector":"test","date_of_recruitment":"03/03/2019","number_of_patients":2},{"id":2,"name":"Ahmed khaled","user_id":null,"created_at":"2019-03-04 20:50:27","updated_at":"2019-03-04 20:50:27","country_id":null,"city_id":null,"hospital_name":null,"business_sector":null,"date_of_recruitment":null,"number_of_patients":0},{"id":3,"name":"Ahmed samy","user_id":null,"created_at":"2019-03-04 20:52:38","updated_at":"2019-03-04 20:52:38","country_id":null,"city_id":null,"hospital_name":null,"business_sector":null,"date_of_recruitment":null,"number_of_patients":0},{"id":4,"name":"Ahmed fady","user_id":null,"created_at":"2019-03-04 20:53:00","updated_at":"2019-03-04 20:53:00","country_id":null,"city_id":null,"hospital_name":null,"business_sector":null,"date_of_recruitment":null,"number_of_patients":0},{"id":5,"name":"Ahmed shady","user_id":"1","created_at":"2019-03-04 20:53:16","updated_at":"2019-03-04 20:53:16","country_id":"2","city_id":"1","hospital_name":"rawda","business_sector":"test","date_of_recruitment":"03/03/2019","number_of_patients":1}]
     * state : 1
     */

    private int state;
    private List<DataBean> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCountry_id() {
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
}
