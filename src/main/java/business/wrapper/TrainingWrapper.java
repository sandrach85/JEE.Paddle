package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrainingWrapper {
    
    private Calendar dateIni;
    
    private Calendar dateEnd;
    
    private CourtState court;
    
    private UserWrapper trainer;
    
    private List<UserWrapper> users;

    public TrainingWrapper() {
        this.users = new ArrayList<UserWrapper>();
    }
    
    public TrainingWrapper(Calendar dateIni, Calendar dateEnd, CourtState court, UserWrapper trainer) {
        super();
        this.dateIni = dateIni;
        this.dateEnd = dateEnd;
        this.court = court;
        this.trainer = trainer;
        this.users = new ArrayList<UserWrapper>();
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

    public CourtState getCourt() {
        return court;
    }

    public void setCourt(CourtState court) {
        this.court = court;
    }

    public UserWrapper getTrainer() {
        return trainer;
    }

    public void setTrainer(UserWrapper trainer) {
        this.trainer = trainer;
    }

    public List<UserWrapper> getUsers() {
        return users;
    }

    public void setUsers(List<UserWrapper> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        String dateIniString = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(dateIni.getTime());
        String dateEndString = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(dateEnd.getTime());
        return "TrainingWrapper [dateIni=" + dateIniString + ", dateEnd=" + dateEndString + ", court=" + court + ", trainer=" + trainer + ", users="
                + users + "]";
    }
    
    
}
