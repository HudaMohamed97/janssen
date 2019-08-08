package eg.com.cat.janssen.model;

public class LoginResponseModel {


    /**
     * data : {"id":1,"name":"Ahmed Mahmoud","username":"admin","email":"adesouky@cat.com.eg","phone":"43745053","image_profile":null,"address":"\u200eمكتب 114 - الدور الاول - اويسس مول - طريق الملك عبدالله-جده\u200e","player_id":null,"created_by":null,"created_at":null,"country_id":"2","photo":"nophoto","cities":[{"id":3,"name":"Riyadh"},{"id":4,"name":"Jeddah"},{"id":5,"name":"Abha"}]}
     * state : 1
     */

    private UserModel data;
    private String state;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
