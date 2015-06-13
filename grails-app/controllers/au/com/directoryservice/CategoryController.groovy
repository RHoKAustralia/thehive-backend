package au.com.directoryservice

import grails.rest.RestfulController

class CategoryController extends RestfulController<Category> {

    static responseFormats = ['json', 'xml']

    CategoryController() {
        super(Category)
    }
}
