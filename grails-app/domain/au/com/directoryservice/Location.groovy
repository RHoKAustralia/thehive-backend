package au.com.directoryservice

class Location extends Model {

    ServiceProvider serviceProvider

    String name
    String shopNumber
    String streetNumber
    String streetName
    String suburb
    String postcode

    String latitude
    String longitude

    String website

    static constraints = {
        shopNumber nullable: true
        streetNumber nullable: true
        postcode nullable: true
        website nullable: true
    }

    static transients = ['hoursOfOperation']

    List<HoursOfOperation> getHoursOfOperation() {
        return HoursOfOperation.findAllByLocation(this)
    }
}
