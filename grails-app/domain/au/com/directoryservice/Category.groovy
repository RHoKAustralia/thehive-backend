package au.com.directoryservice

class Category extends Model {

    String name
    String iconPath
    List<String> keywords

    static constraints = {
        iconPath nullable: true
    }

    static hasMany = ['keywords']

    static transients = ['services']

    List<ServiceProvider> getServices() {
        return ServiceProvider.findAllByCategory(this)
    }
}
