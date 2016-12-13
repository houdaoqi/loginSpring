package loginSpring.po;

import java.io.Serializable;

/**
 * Created by lenovo on 10/27/2016.
 */
public class User implements Serializable{

    private long id;

    private String userName;

    private String userPassword;

    public User(){

    }

    public User(long id){
        this.id = id;
    }

    public User(String userName, String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(long ID, String userName, String userPassword){
        this.id = ID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public final String getUserName() {return userName;}

    public final void setUserName(String userName) {this.userName = userName;}

    public final String getUserPassword(){return userPassword;}

    public final void setUserPassword(String userPassword){this.userPassword = userPassword;}

    public final long getId(){return id;}

    public final void setId(long id){this.id = id;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());

        result = prime * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userPassword == null) {
            if (other.userPassword != null)
                return false;
        } else if (!userPassword.equals(other.userPassword))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + userName + "]";
    }
}
