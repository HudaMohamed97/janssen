package eg.com.cat.janssen.model;

public class DeletePatientResponseModel {


    /**
     * data : Patien Deleted!
     * state : 1
     */

    private String data;
    private int state;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
