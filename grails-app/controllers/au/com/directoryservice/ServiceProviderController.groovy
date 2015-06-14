package au.com.directoryservice

import org.joda.time.DateTime

class ServiceProviderController extends AbstractController<ServiceProvider> {

    static responseFormats = ['html', 'json', 'xml']

    ServiceProviderController() {
        super(ServiceProvider)
    }

    def bycategory() {
        List<ServiceProvider> serviceProviders = Category.get(params.id)?.services
        respond serviceProviders, model: [("${resourceName}Count".toString()): serviceProviders.size(), ("${resourceName}List".toString()): serviceProviders]
    }

    def search() {

        if (!params.q) {
            respond([error: "Please provide 'q' term"])
            return
        }

        List<ServiceProvider> serviceProviders = ServiceProvider.findAll(
                "from ServiceProvider s where (lower(s.name) like :term or lower(s.category.name) like :term)",
                [term: "%${params.q}%".toLowerCase()])

        Category.list().each { category ->
            category.keywords.each { keyword ->

                if (keyword.toLowerCase().contains(new String(params.q).toLowerCase())) {

                    category.services.each { service ->

                        if (!serviceProviders.contains(service)) {
                            serviceProviders.add(service)
                        }
                    }
                }
            }
        }

        serviceProviders = serviceProviders.unique()

        respond(serviceProviders)

        // TODO Back application with MySQL
    }

    def whatsontoday() {
        Day today = Day.valueOf(DateTime.now().dayOfWeek().asText)

        log.info(today)

        List<ServiceProvider> serviceProviders = []

        HoursOfOperation.list().each { hoursOfOperation ->

            if (hoursOfOperation.days.contains(today)) {
                serviceProviders.add(hoursOfOperation.location.serviceProvider)
            }
        }

        respond(serviceProviders)
    }

    def aroundme() {
        // TODO implement aroundme API (geo based)
    }
}
