package au.com.directoryservice

class ServiceProviderController extends AbstractController<ServiceProvider> {

    static responseFormats = ['json', 'xml']

    ServiceProviderController() {
        super(ServiceProvider)
    }

    def bycategory() {
        respond(Category.get(params.id)?.services)
    }

    def search() {
        List<ServiceProvider> serviceProviders = ServiceProvider.findAll(
                "from ServiceProvider s where (s.name like :term or s.category.name like :term or :term in (s.category.keywords))",
                [term: "%${params.q}%"])

        respond(serviceProviders)
    }
}
