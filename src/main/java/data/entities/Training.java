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
    
    @ManyToOne
    @JoinColumn
    private Reserve reserve;

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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", dateIni=" + dateIni + ", dateEnd=" + dateEnd + ", court=" + court + ", user="
				+ user + ", reserve=" + reserve + "]";
	}


	public void createReserves(Calendar dateI, Calendar dateE, Court court){
		int yearI = dateI.get(Calendar.YEAR);
		int monthI = dateI.get(Calendar.MONTH); 
		int day = dateI.get(Calendar.DAY_OF_MONTH);
		int week = dateI.get(Calendar.WEEK_OF_YEAR);
		int hour = dateI.get(Calendar.HOUR_OF_DAY);
		int minutes = dateI.get(Calendar.MINUTE);
		int yearE = dateE.get(Calendar.YEAR);
		int monthE = dateE.get(Calendar.MONTH);
		int dayE = dateE.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		Calendar dateAux;
		
		for (int i=0; i<daysBetweenDates(dateI,dateE);i++){
			//desplazar el mes
			dateAux.set(yearI, monthI, day+(i*6), hour, minutes);
			//poner trainer como user
			reserve= new Reserve(court, user., dateAux);
		}
		
		
	}
	
	private int daysBetweenDates(Calendar dateI, Calendar dateE){
		long milisec = dateE.getTimeInMillis()-dateI.getTimeInMillis();
		int days = (int) milisec/1000/60/60/24;
		return days;
	}
    
}
