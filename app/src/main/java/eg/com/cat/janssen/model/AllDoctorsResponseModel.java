package eg.com.cat.janssen.model;

import java.util.List;

public class AllDoctorsResponseModel {


    /**
     * data : [{"id":8,"country_id":"2","city_id":"3","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 10:00:37","updated_at":"2019-03-19 10:00:37","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}},{"id":9,"country_id":"2","city_id":"3","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 10:00:48","updated_at":"2019-03-19 10:00:48","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}},{"id":10,"country_id":"2","city_id":"3","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:26:21","updated_at":"2019-03-19 11:26:21","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}},{"id":11,"country_id":"2","city_id":"3","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:26:36","updated_at":"2019-03-19 11:26:36","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}},{"id":12,"country_id":"2","city_id":"3","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:26:39","updated_at":"2019-03-19 11:26:39","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}},{"id":13,"country_id":"2","city_id":"2","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:50:26","updated_at":"2019-03-19 11:50:26","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":2,"name":"Jeddah","active":"1","country_id":"2","created_at":"2019-03-10 10:00:21","updated_at":"2019-03-10 10:00:21"}},{"id":14,"country_id":"2","city_id":"2","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:50:34","updated_at":"2019-03-19 11:50:34","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":2,"name":"Jeddah","active":"1","country_id":"2","created_at":"2019-03-10 10:00:21","updated_at":"2019-03-10 10:00:21"}},{"id":15,"country_id":"2","city_id":"2","doctor_id":"1","user_id":"1","hospital_name":"rawda","business_sector":"GOV","date_of_recruitment":"03/03/2019","patient_profile":"Bio-Naive","created_at":"2019-03-19 11:50:36","updated_at":"2019-03-19 11:50:36","doctor":{"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"},"country":{"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"},"city":{"id":2,"name":"Jeddah","active":"1","country_id":"2","created_at":"2019-03-10 10:00:21","updated_at":"2019-03-10 10:00:21"}}]
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
         * id : 8
         * country_id : 2
         * city_id : 3
         * doctor_id : 1
         * user_id : 1
         * hospital_name : rawda
         * business_sector : GOV
         * date_of_recruitment : 03/03/2019
         * patient_profile : Bio-Naive
         * created_at : 2019-03-19 10:00:37
         * updated_at : 2019-03-19 10:00:37
         * doctor : {"id":1,"name":"Ahmed Adel","user_id":"1","created_at":"2019-03-04 20:50:08","updated_at":"2019-03-04 20:50:08"}
         * country : {"id":2,"name":"KSA","active":"1","created_at":"2019-03-01 02:24:35","updated_at":"2019-03-10 09:58:59"}
         * city : {"id":3,"name":"Riyadh","active":"1","country_id":"2","created_at":"2019-03-10 10:00:33","updated_at":"2019-03-10 10:00:33"}
         */

        private int id;
        private String country_id;
        private String city_id;
        private String doctor_id;
        private String user_id;
        private String hospital_name;
        private String business_sector;
        private String date_of_recruitment;
        private String patient_profile;
        private String created_at;
        private String updated_at;
        private DoctorBean doctor;
        private CountryBean country;
        private CityBean city;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getPatient_profile() {
            return patient_profile;
        }

        public void setPatient_profile(String patient_profile) {
            this.patient_profile = patient_profile;
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

        public DoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorBean doctor) {
            this.doctor = doctor;
        }

        public CountryBean getCountry() {
            return country;
        }

        public void setCountry(CountryBean country) {
            this.country = country;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class DoctorBean {
            /**
             * id : 1
             * name : Ahmed Adel
             * user_id : 1
             * created_at : 2019-03-04 20:50:08
             * updated_at : 2019-03-04 20:50:08
             */

            private int id;
            private String name;
            private String user_id;
            private String created_at;
            private String updated_at;

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
        }

        public static class CountryBean {
            /**
             * id : 2
             * name : KSA
             * active : 1
             * created_at : 2019-03-01 02:24:35
             * updated_at : 2019-03-10 09:58:59
             */

            private int id;
            private String name;
            private String active;
            private String created_at;
            private String updated_at;

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

            public String getActive() {
                return active;
            }

            public void setActive(String active) {
                this.active = active;
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
        }

        public static class CityBean {
            /**
             * id : 3
             * name : Riyadh
             * active : 1
             * country_id : 2
             * created_at : 2019-03-10 10:00:33
             * updated_at : 2019-03-10 10:00:33
             */

            private int id;
            private String name;
            private String active;
            private String country_id;
            private String created_at;
            private String updated_at;

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

            public String getActive() {
                return active;
            }

            public void setActive(String active) {
                this.active = active;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
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
        }
    }
}
