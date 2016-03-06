package data.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Training {
    
    @Id
    @GeneratedValue
    private int id;
    
    private Calendar dateIni;
    
    private Calendar dateEnd;
    
    @ManyToOne
    @JoinColumn
    private Court court;
    
    @ManyToOne
    @JoinColumn
    private User user;

    public Training(int id, Calendar dateIni, Calendar dateEnd, Court court) {
        super();
        this.id = id;
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
        this.court = court;
    }

    public Calendar getDateIni() {
        return dateIni;
    }

    public void setDateIni(Calendar dateIni) {
        this.dateIni = dateIni;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Training other = (Training) obj;
        if (court == null) {
            if (other.court != null)
                return false;
        } else if (!court.equals(other.court))
            return false;
        if (dateEnd == null) {
            if (other.dateEnd != null)
                return false;
        } else if (!dateEnd.equals(other.dateEnd))
            return false;
        if (dateIni == null) {
            if (other.dateIni != null)
                return false;
        } else if (!dateIni.equals(other.dateIni))
            return false;
        if (id != other.id)
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Training [id=" + id + ", dateIni=" + dateIni + ", dateEnd=" + dateEnd + ", court=" + court + ", user=" + user + "]";
    }


    
}
