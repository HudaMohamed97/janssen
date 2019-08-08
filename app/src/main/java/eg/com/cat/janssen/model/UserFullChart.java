package eg.com.cat.janssen.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserFullChart {


    /**
     * data : {"id":1,"name":"Ahmed Mahmoud","username":"admin","email":"adesouky@cat.com.eg","phone":"01111111111","image_profile":null,"address":"adress","employee_id":"125690","player_id":null,"created_by":null,"created_at":null,"country_id":"2","cities":[{"id":2,"name":"Jeddah","active":"1","count":2,"months":{"1":0,"2":0,"3":2,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}},{"id":4,"name":"Asir","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]}
     * state : 1
     * totalCount : 5
     */

    private DataBean data;
    private int state;
    private int totalCount;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : Ahmed Mahmoud
         * username : admin
         * email : adesouky@cat.com.eg
         * phone : 01111111111
         * image_profile : null
         * address : adress
         * employee_id : 125690
         * player_id : null
         * created_by : null
         * created_at : null
         * country_id : 2
         * cities : [{"id":2,"name":"Jeddah","active":"1","count":2,"months":{"1":0,"2":0,"3":2,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}},{"id":4,"name":"Asir","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]
         */

        private int id;
        private String name;
        private String username;
        private String email;
        private String phone;
        private Object image_profile;
        private String address;
        private String employee_id;
        private Object player_id;
        private Object created_by;
        private Object created_at;
        private String country_id;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getImage_profile() {
            return image_profile;
        }

        public void setImage_profile(Object image_profile) {
            this.image_profile = image_profile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmployee_id() {
            return employee_id;
        }

        public void setEmployee_id(String employee_id) {
            this.employee_id = employee_id;
        }

        public Object getPlayer_id() {
            return player_id;
        }

        public void setPlayer_id(Object player_id) {
            this.player_id = player_id;
        }

        public Object getCreated_by() {
            return created_by;
        }

        public void setCreated_by(Object created_by) {
            this.created_by = created_by;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public List<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(List<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            /**
             * id : 2
             * name : Jeddah
             * active : 1
             * count : 2
             * months : {"1":0,"2":0,"3":2,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}
             */

            private int id;
            private String name;
            private String active;
            private int count;
            private MonthsBean months;

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

            public MonthsBean getMonths() {
                return months;
            }

            public void setMonths(MonthsBean months) {
                this.months = months;
            }

            public static class MonthsBean {
                /**
                 * 1 : 0
                 * 2 : 0
                 * 3 : 2
                 * 4 : 0
                 * 5 : 0
                 * 6 : 0
                 * 7 : 0
                 * 8 : 0
                 * 9 : 0
                 * 10 : 0
                 * 11 : 0
                 * 12 : 0
                 */

                @SerializedName("1")
                private int _$1;
                @SerializedName("2")
                private int _$2;
                @SerializedName("3")
                private int _$3;
                @SerializedName("4")
                private int _$4;
                @SerializedName("5")
                private int _$5;
                @SerializedName("6")
                private int _$6;
                @SerializedName("7")
                private int _$7;
                @SerializedName("8")
                private int _$8;
                @SerializedName("9")
                private int _$9;
                @SerializedName("10")
                private int _$10;
                @SerializedName("11")
                private int _$11;
                @SerializedName("12")
                private int _$12;

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public int get_$2() {
                    return _$2;
                }

                public void set_$2(int _$2) {
                    this._$2 = _$2;
                }

                public int get_$3() {
                    return _$3;
                }

                public void set_$3(int _$3) {
                    this._$3 = _$3;
                }

                public int get_$4() {
                    return _$4;
                }

                public void set_$4(int _$4) {
                    this._$4 = _$4;
                }

                public int get_$5() {
                    return _$5;
                }

                public void set_$5(int _$5) {
                    this._$5 = _$5;
                }

                public int get_$6() {
                    return _$6;
                }

                public void set_$6(int _$6) {
                    this._$6 = _$6;
                }

                public int get_$7() {
                    return _$7;
                }

                public void set_$7(int _$7) {
                    this._$7 = _$7;
                }

                public int get_$8() {
                    return _$8;
                }

                public void set_$8(int _$8) {
                    this._$8 = _$8;
                }

                public int get_$9() {
                    return _$9;
                }

                public void set_$9(int _$9) {
                    this._$9 = _$9;
                }

                public int get_$10() {
                    return _$10;
                }

                public void set_$10(int _$10) {
                    this._$10 = _$10;
                }

                public int get_$11() {
                    return _$11;
                }

                public void set_$11(int _$11) {
                    this._$11 = _$11;
                }

                public int get_$12() {
                    return _$12;
                }

                public void set_$12(int _$12) {
                    this._$12 = _$12;
                }
            }
        }
    }
}
