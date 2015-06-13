package au.com.directoryservice

import org.joda.time.LocalTime

class HoursOfOperation extends Model {

    Location location

    List<Day> days = []
    LocalTime startTime
    LocalTime endTime

    static constraints = {
    }

    static hasMany = [days: Day]
}
