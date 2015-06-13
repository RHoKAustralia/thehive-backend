package au.com.directoryservice

class ServiceProvider extends Model {

    String name
    Category category
    String description
    String imagePath
    String website

    static constraints = {
        imagePath nullable: true
        website nullable: true
    }

    static transients = ['locations']

    List<Location> getLocations() {
        return Location.findAllByServiceProvider(this)
    }
}
