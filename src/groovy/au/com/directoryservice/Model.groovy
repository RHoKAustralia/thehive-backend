package au.com.directoryservice

import org.joda.time.DateTime

abstract class Model {

    DateTime dateCreated
    DateTime lastUpdated
    Boolean enabled = true
    App app
}
