class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/api/$controller/$action?/$id?(.$format)?" {

        }

        "/"(controller: "serviceProvider", action: "index")
        "500"(view: '/error')
    }
}
