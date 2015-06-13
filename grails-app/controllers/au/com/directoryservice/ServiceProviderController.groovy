package au.com.directoryservice

import grails.converters.JSON

class ServiceProviderController extends AbstractController<ServiceProvider> {

    static responseFormats = ['json', 'xml']

    ServiceProviderController() {
        super(ServiceProvider)
    }

    def bycategory() {
        respond(Category.get(params.id)?.services)
    }

    def search() {

        if(!params.q) {
            respond([error: "Please provide 'q' term"])
            return
        }

        List<ServiceProvider> serviceProviders = ServiceProvider.findAll(
                "from ServiceProvider s where (s.name like :term or s.category.name like :term)",
                [term: "%${params.q}%"])


        // TODO Implement search for each keyword in each cateogory
        /*
        I.e. category.each -> keyword.each -> if keyword ilike :term, then add.
         Then list.unique
         */

        // TODO make sure search is case insensitive (ilike)
        // TODO implement aroundme API (geo based)
        // TODO implement "whatson today" api (steal code from roomservice)

        // TODO make sure apis are all documented (index, show, bycateogry, search, whatson, and around me)

        respond(serviceProviders)
    }
}
