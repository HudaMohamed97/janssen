package eg.com.cat.janssen.model;

import java.util.List;

public class CityChartResponseModel {
    /**
     * data : [{"id":1,"name":"Doha","active":"1","count":3},{"id":2,"name":"Jeddah","active":"1","count":0},{"id":3,"name":"Riyadh","active":"1","count":0},{"id":4,"name":"Asir","active":"1","count":0}]
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
         * name : Doha
         * active : 1
         * count : 3
         */

        private int id;
        private String name;
        private String active;
        private int count;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
