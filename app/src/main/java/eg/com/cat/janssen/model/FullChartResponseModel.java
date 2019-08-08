package eg.com.cat.janssen.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullChartResponseModel {


    /**
     * data : [{"id":1,"name":"QAT","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0},"cities":[{"id":1,"name":"Doha","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]},{"id":2,"name":"KSA","active":"1","count":6,"months":{"1":0,"2":0,"3":6,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0},"cities":[{"id":2,"name":"Jeddah","active":"1","count":2,"months":{"1":0,"2":0,"3":2,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}},{"id":3,"name":"Riyadh","active":"1","count":4,"months":{"1":0,"2":0,"3":4,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}},{"id":4,"name":"Asir","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]},{"id":3,"name":"KWT","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}},{"id":4,"name":"UAE","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]
     * state : 1
     */

    private int state;
    private List<DataBean> data;
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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
         * name : QAT
         * active : 1
         * count : 0
         * months : {"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}
         * cities : [{"id":1,"name":"Doha","active":"1","count":0,"months":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}}]
         */

        private int id;
        private String name;
        private String active;
        private int count;
        private MonthsBean months;
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

        public List<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(List<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class MonthsBean {
            /**
             * 1 : 0
             * 2 : 0
             * 3 : 0
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

        public static class CitiesBean {
            /**
             * id : 1
             * name : Doha
             * active : 1
             * count : 0
             * months : {"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"10":0,"11":0,"12":0}
             */

            private int id;
            private String name;
            private String active;
            private int count;
            private MonthsBeanX months;

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

            public MonthsBeanX getMonths() {
                return months;
            }

            public void setMonths(MonthsBeanX months) {
                this.months = months;
            }

            public static class MonthsBeanX {
                /**
                 * 1 : 0
                 * 2 : 0
                 * 3 : 0
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
