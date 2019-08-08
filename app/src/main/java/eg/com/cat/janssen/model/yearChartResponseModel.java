package eg.com.cat.janssen.model;

import com.google.gson.annotations.SerializedName;

public class yearChartResponseModel {


    /**
     * data : {"2016":0,"2017":0,"2018":0,"2019":3,"2020":0,"2021":0,"2022":0,"2023":0,"2024":0,"2025":0}
     * state : 1
     */

    private DataBean data;
    private int state;

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

    public static class DataBean {
        /**
         * 2016 : 0
         * 2017 : 0
         * 2018 : 0
         * 2019 : 3
         * 2020 : 0
         * 2021 : 0
         * 2022 : 0
         * 2023 : 0
         * 2024 : 0
         * 2025 : 0
         */

        @SerializedName("2016")
        private int _$2016;
        @SerializedName("2017")
        private int _$2017;
        @SerializedName("2018")
        private int _$2018;
        @SerializedName("2019")
        private int _$2019;
        @SerializedName("2020")
        private int _$2020;
        @SerializedName("2021")
        private int _$2021;
        @SerializedName("2022")
        private int _$2022;
        @SerializedName("2023")
        private int _$2023;
        @SerializedName("2024")
        private int _$2024;
        @SerializedName("2025")
        private int _$2025;

        public int get_$2016() {
            return _$2016;
        }

        public void set_$2016(int _$2016) {
            this._$2016 = _$2016;
        }

        public int get_$2017() {
            return _$2017;
        }

        public void set_$2017(int _$2017) {
            this._$2017 = _$2017;
        }

        public int get_$2018() {
            return _$2018;
        }

        public void set_$2018(int _$2018) {
            this._$2018 = _$2018;
        }

        public int get_$2019() {
            return _$2019;
        }

        public void set_$2019(int _$2019) {
            this._$2019 = _$2019;
        }

        public int get_$2020() {
            return _$2020;
        }

        public void set_$2020(int _$2020) {
            this._$2020 = _$2020;
        }

        public int get_$2021() {
            return _$2021;
        }

        public void set_$2021(int _$2021) {
            this._$2021 = _$2021;
        }

        public int get_$2022() {
            return _$2022;
        }

        public void set_$2022(int _$2022) {
            this._$2022 = _$2022;
        }

        public int get_$2023() {
            return _$2023;
        }

        public void set_$2023(int _$2023) {
            this._$2023 = _$2023;
        }

        public int get_$2024() {
            return _$2024;
        }

        public void set_$2024(int _$2024) {
            this._$2024 = _$2024;
        }

        public int get_$2025() {
            return _$2025;
        }

        public void set_$2025(int _$2025) {
            this._$2025 = _$2025;
        }
    }
}
