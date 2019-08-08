package eg.com.cat.janssen.model;

import java.util.List;

public class UserModel {

    /**
     * id : 1
     * name : Ahmed Mahmoud
     * username : admin
     * email : adesouky@cat.com.eg
     * phone : 43745053
     * image_profile : null
     * address : ‎مكتب 114 - الدور الاول - اويسس مول - طريق الملك عبدالله-جده‎
     * player_id : null
     * created_by : null
     * created_at : null
     * country_id : 2
     * photo : nophoto
     * cities : [{"id":3,"name":"Riyadh"},{"id":4,"name":"Jeddah"},{"id":5,"name":"Abha"}]
     */

    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Object image_profile;
    private String address;
    private Object player_id;
    private Object created_by;
    private Object created_at;
    private String country_id;
    private String photo;
    private Boolean type;
    private List<CitiesBean> cities;

    public Boolean getAdmin() {
        return type;
    }

    public void setAdmin(Boolean admin) {
        type = admin;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean {
        /**
         * id : 3
         * name : Riyadh
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
