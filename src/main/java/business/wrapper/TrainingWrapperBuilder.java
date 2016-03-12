package business.wrapper;

import java.util.Calendar;

public class TrainingWrapperBuilder {

    private TrainingWrapper trainingWraper;

    public TrainingWrapperBuilder(UserWrapper user) {
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.set(date1.get(Calendar.YEAR), (date1.get(Calendar.MONTH) + 1), date1.get(Calendar.DATE), date1.get(Calendar.HOUR_OF_DAY),
                date1.get(Calendar.MINUTE));
        trainingWraper = new TrainingWrapper(date1, date2, new CourtState(), user);
    }

    public TrainingWrapperBuilder() {
        this(new UserWrapper());
    }

    public TrainingWrapperBuilder(Calendar date1, Calendar date2) {
        trainingWraper.setDateIni(date1);
        trainingWraper.setDateEnd(date2);
    }

    public TrainingWrapperBuilder(CourtState court) {
        trainingWraper.setCourt(court);
    }

    public TrainingWrapper build() {
        return trainingWraper;
    }

}
