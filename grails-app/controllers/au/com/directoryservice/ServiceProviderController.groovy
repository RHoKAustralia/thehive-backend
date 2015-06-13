package au.com.directoryservice

import grails.converters.JSON
import grails.rest.RestfulController

class ServiceProviderController extends RestfulController<ServiceProvider> {

    static responseFormats = ['json', 'xml']

    ServiceProviderController() {
        super(ServiceProvider)
    }

    def bycategory() {
        respond(Category.get(params.id)?.services)
    }

    def search() {

        ServiceProvider.findAll("from ServiceProvider s where (s.name like :term or s.category.name like :term or :term in (s.category))")


        String hql = "select s from Section s where s.deleted = false and s.module = :m"
        def conditionalParams = [m: Module.get(params.m)]

        if (params.q) {
            hql += " and s.title like :q"
            conditionalParams.put("q", "%${params.q}%")
        }

        respond(ServiceProvider.executeQuery(hql, conditionalParams, params))


    }
}
