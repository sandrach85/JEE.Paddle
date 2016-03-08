package data.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

    private User trainer;
    
    @ManyToMany
    private List<User> users;
    
    @ManyToOne
    @JoinColumn
    private Reserve reserve;

	public Training(int id, Calendar dateIni, Calendar dateEnd, Court court) {
		super();
		this.id = id;
		this.dateIni = dateIni;
		this.dateEnd = dateEnd;
		this.court = court;
		createReserves(dateIni, dateEnd, court);
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
		return trainer;
	}

	public void setUser(User user) {
		this.trainer = user;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
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
		if (reserve == null) {
			if (other.reserve != null)
				return false;
		} else if (!reserve.equals(other.reserve))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", dateIni=" + dateIni + ", dateEnd=" + dateEnd + ", court=" + court + ", user="
				+ trainer + ", reserve=" + reserve + "]";
	}

	public void createReserves(Calendar dateI, Calendar dateE, Court court){
		long dateMilis = dateI.getTimeInMillis();
		long weekAdd = 1000*60*60*24*7;
		Calendar dateAdd=dateI;
		
		for (int i=0; i<daysBetweenDates(dateI,dateE);i++){
			dateAdd.setTimeInMillis(dateMilis+(weekAdd*i));
			reserve= new Reserve(court, trainer, dateAdd);
		}
	}
	
	public int daysBetweenDates(Calendar dateI, Calendar dateE){
		long milisec = dateE.getTimeInMillis()-dateI.getTimeInMillis();
		int days = (int) milisec/1000/60/60/24;
		return days;
	}
    
}
