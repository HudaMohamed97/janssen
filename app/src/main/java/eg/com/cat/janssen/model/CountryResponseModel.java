package eg.com.cat.janssen.model;

import java.util.List;

public class CountryResponseModel {


    /**
     * data : [{"id":1,"name":"Egypt","active":"1","created_at":null,"updated_at":null,"cities":[{"id":1,"name":"Cairo"}]},{"id":2,"name":"Saudi Arabia","active":"1","created_at":"2019-03-03 11:18:08","updated_at":"2019-03-03 11:18:08","cities":[{"id":3,"name":"Riyadh"},{"id":4,"name":"Jeddah"},{"id":5,"name":"Abha"}]}]
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
         * name : Egypt
         * active : 1
         * created_at : null
         * updated_at : null
         * cities : [{"id":1,"name":"Cairo"}]
         */

        private int id;
        private String name;
        private String active;
        private Object created_at;
        private Object updated_at;
        private List<CitiesBean> cities;

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

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }

        public List<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(List<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            /**
             * id : 1
             * name : Cairo
             */

            private int id;
            private String name;

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
        }
    }
}
