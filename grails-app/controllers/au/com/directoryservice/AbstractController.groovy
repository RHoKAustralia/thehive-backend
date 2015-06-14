package au.com.directoryservice

//import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional

/**
 * Abstract CRUD class with security restrictions on object manipulation operations.
 *
 * @param < DOMAIN >    The Domain Class corresponding to the implementing CRUD controller
 */
abstract class AbstractController<DOMAIN> extends RestfulController<DOMAIN> {

    AbstractController(Class<DOMAIN> resource) {
        this(resource, false)
    }

    AbstractController(Class<DOMAIN> resource, boolean readOnly) {
        super(resource, readOnly)
    }

    @Override
    def index(Integer max) {

        if (params.id) {
            super.show()
            return
        }

        log.info("Resource name: ${resourceName}")

        params.max = Math.min(max ?: 10, 100)
        List<DOMAIN> resources = listAllResources(params)
        respond resources, model: [("${resourceName}Count".toString()): countResources(), ("${resourceName}List".toString()): resources]
    }

    @Override
    //@Secured("ROLE_ADMIN")
    def create() {
        super.create()
    }

    @Override
    @Transactional
    //@Secured("ROLE_ADMIN")
    def save() {
        super.save()
    }

    @Override
    //@Secured("ROLE_ADMIN")
    def edit() {
        super.edit()
    }

    @Override
    @Transactional
    //@Secured("ROLE_ADMIN")
    def patch() {
        update()
    }

    @Override
    @Transactional
    //@Secured("ROLE_ADMIN")
    def update() {
        super.update()
    }

    @Override
    @Transactional
    //@Secured("ROLE_ADMIN")
    def delete() {
        super.delete()
    }
}
